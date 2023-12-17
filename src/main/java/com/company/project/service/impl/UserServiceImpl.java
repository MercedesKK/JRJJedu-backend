package com.company.project.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.UserMapper;
import com.company.project.model.Columns;
import com.company.project.model.Tables;
import com.company.project.model.User;
import com.company.project.service.SysMenuService;
import com.company.project.service.UserService;
import com.company.project.utils.*;
import com.company.project.vo.CaptchaVo;
import com.company.project.vo.LoginVo;
import com.company.project.vo.SysUserVo;
import com.company.project.vo.VerfiyCodeVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import com.wf.captcha.GifCaptcha;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.security.GeneralSecurityException;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private MagConfig magConfig;

    @Autowired
    EmailUtils emailUtils;

    @Value("${spring.datasource.username}")
    private String databaseName;

    //private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    @Override
    public Result logout(Long userId) {
        SysUserVo sysUserVo = null;
        String token=(String)redisService.get(userId+"USERID");
        try {
            sysUserVo = (SysUserVo)redisService.get(Constants.REDIS_KEY_LOGIN + token);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("redis异常");
        }

        redisService.delete(userId+"USERID");

        if (sysUserVo != null){
            redisService.delete(Constants.REDIS_KEY_LOGIN + token);

            return ResultGenerator.genSuccessResult();
        }

        return ResultGenerator.genFailResult(ResultCode.NOT_LOGIN_EXCEPTION,"用户未登录,请重新登录");
    }

    @Override
    public Result login(LoginVo vo) {

        //读写锁
        //只允许一个线程写入（其他线程既不能写入也不能读取）
        //没有写入时，多个线程允许同时读（提高性能）
        //readWriteLock.writeLock().lock();

        //声明存储对象
        SysUserVo sysUserVo = new SysUserVo();

        User user = new User();

        //根据用户名查询
        user = userMapper.findUserByUserName(vo.getUserName(),null);

        if (null == user){
            return ResultGenerator.genFailResult(ResultCode.USER_NOT_EXIST,"用户信息不存在[账号可能被停用或删除]");
        }

        if(!vo.getPassword().equals(user.getPassword())){
            return ResultGenerator.genFailResult(ResultCode.PASSWORD_ERROR,"密码输入错误，请重新输入");
        }

        //获取对应的模块
        List<Object> sysMenuList = new ArrayList<Object>();
        if (null != user.getRoleId()){
            sysMenuList = sysMenuService.selectMenuByRoleId(user.getRoleId());
        }

        //创建token
        String token= (String) redisService.get(user.getId() + "USERID");

        Boolean loginFlag = false;

        if(StringUtils.isNotBlank(token)){
            //说明已登陆，或直接断网
            redisService.delete(Constants.REDIS_KEY_LOGIN + token);
            redisService.delete(user.getId()+"USERID");
        }else{
            //true首次
            loginFlag=true;
        }
        token = TokenUtil.getToken();

        try {
            //存入用户相关信息
            sysUserVo.setUserId(user.getId());
            sysUserVo.setPhone(user.getPhone());
            sysUserVo.setEmail(user.getEmail());
            sysUserVo.setPassword(user.getPassword());
            sysUserVo.setToken(token);
            //设置该token的过期时间
            sysUserVo.setTokenExpireTime(System.currentTimeMillis() + magConfig.getExpireTime());
            sysUserVo.setUserName(user.getUserName());
            sysUserVo.setSysMenuList(sysMenuList);
            sysUserVo.setRoleId(user.getRoleId().toString());

            redisService.setWithExpire(Constants.REDIS_KEY_LOGIN + token, sysUserVo , 2505600000L);
            redisService.set(user.getId()+"USERID",token);
        }catch (Exception e){
            e.printStackTrace();
            Logger.info(this,"登录token存入redis产生异常："+e.getMessage());
            throw new RuntimeException("存入redis异常");
        }/*finally {
            if(readWriteLock.isWriteLocked()){
                readWriteLock.writeLock().unlock();
            }
        }*/
        return ResultGenerator.genSuccessResult(sysUserVo);
    }

    @Override
    public Result sendLogCode(String phone) {
        SmsUtils smsUtils = new SmsUtils();
        String verCode = RandomUtil.createRandom(true,4);
        smsUtils.sendSms(phone,"2020569",verCode);
        // 存入redis并设置过期时间为3分钟
        redisService.setWithExpire(Constants.REDIS_KEY_LOGIN + phone, new VerfiyCodeVo(verCode,System.currentTimeMillis() + Constants.verifyCodeForTempValidTime) , Constants.verifyCodeForTempValidTime);
        return ResultGenerator.genSuccessResult();
    }

    @Override
    public Result sendPasswordCode(String phone) {
        SmsUtils smsUtils = new SmsUtils();
        String verCode = RandomUtil.createRandom(true,4);
        smsUtils.sendSms(phone,"2020587",verCode);
        // 存入redis并设置过期时间为3分钟
        redisService.setWithExpire(Constants.REDIS_KEY_PASSWORD + verCode, new VerfiyCodeVo(verCode,System.currentTimeMillis() + Constants.verifyCodeForTempValidTime) , Constants.verifyCodeForTempValidTime);
        return ResultGenerator.genSuccessResult();
    }

    @Override
    public Result captcha() {
        GifCaptcha specCaptcha = new GifCaptcha(130, 48, 5);
        String verCode = specCaptcha.text().toLowerCase();
        System.out.print("登录验证码" + verCode);
        String verifyToken = TokenUtil.getToken();
        // 存入redis并设置过期时间为30秒
        redisService.setWithExpire(Constants.REDIS_KEY_VERFIY + verifyToken, new VerfiyCodeVo(verCode,System.currentTimeMillis() + Constants.verifyCodeForTempValidTime)  , Constants.verifyCodeForTempValidTime);
        CaptchaVo captchaVo = new CaptchaVo();
        captchaVo.setVerifyToken(verifyToken);
        captchaVo.setData(specCaptcha.toBase64());
        // 将key和base64返回给前端
        return ResultGenerator.genSuccessResult(captchaVo);
    }

    @Override
    public String randList() {
        return userMapper.randList();
    }

    @Override
    public List<User> findUserById(Long userId) {
        return userMapper.findUserById(userId);
    }

    @Override
    public Result delete(Long id) {

        User user = findByIdAndValidDelete(id);
        if (null == user){
            return ResultGenerator.genFailResult(ResultCode.USER_NULL_ERROR,"该记录不存在或者已删除");
        }

        if ("admin".equals(user.getUserName())){
            return ResultGenerator.genFailResult(ResultCode.ADMIN_DELETE_ERROR,"管理员权限不可删除");
        }

        //根据id逻辑删除
        user.setIsDelete(true);
        //逻辑删除用户
        update(user);
        return ResultGenerator.genSuccessResult();
    }

    @Override
    public Result detailByToken(Long userId) {
        User user = findById(userId);
        return ResultGenerator.genSuccessResult(user);
    }

    @Override
    public Result list(User user) {

        if (null == user){
            user = new User();
        }

        //调用PageHelper公共方法实现分页
        PageHelper.startPage(user.getPage() == null ? 0 : user.getPage(), user.getLimit() == null ? 10 : user.getLimit());
        //查询没有逻辑删除
        user.setIsDelete(false);
        //分页查询
        List<User> list = userMapper.list(user);
        if (null != list || list.size() > 0){
            for (User d:list) {
                d.setRole(d.getRoleId());
            }
        }
        PageInfo pageInfo = new PageInfo(list);
        //返回分页后的结果集
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @Override
    public Result findAllColumns() {

        Map<String, List<Columns>> tableMap = new HashMap<>();

        List<Tables> tableNameList = userMapper.selectAllTable(databaseName);
        if (null != tableNameList){
            if (tableNameList.size() > 0){
                for (Tables d:tableNameList) {
                    tableMap.put(d.getTableComment(),userMapper.selectAllColumnsByTableName(databaseName,d.getTableName()));
                }
            }
        }

        return ResultGenerator.genSuccessResult(tableMap);
    }

    @Override
    public Result batchSendEmail(String ids) {
        List<User> userList = userMapper.selectUserListById(ids);
        if (null != userList){
            if (userList.size() > 0){
                for (User user:userList) {
                    if (null != user.getEmail()){
                        try {
                            emailUtils.sendMail(user.getEmail(),user.getUserName(),user.getPassword());
                        } catch (GeneralSecurityException e) {
                            e.printStackTrace();
                        } catch (MessagingException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return ResultGenerator.genSuccessResult();
    }

    @Override
    public Result updatePassword(User user) {

        if (user.getCode() == null || "".equals(user.getCode())){
            return ResultGenerator.genFailResult("请输入验证码");
        }

        VerfiyCodeVo verfiyCodeVo = (VerfiyCodeVo) redisService.get(Constants.REDIS_KEY_PASSWORD + user.getCode());
        if (verfiyCodeVo == null){
            return ResultGenerator.genFailResult("验证码不存在或者已过期，请重新输入");
        }

        if (!user.getCode().equals(verfiyCodeVo.getCode())){
            return ResultGenerator.genFailResult("验证码输入错误");
        }

        //根据用户名查询是否存在
        User newUser = userMapper.findUserByUserName(user.getUserName(),null);

        if (null == newUser){
            return ResultGenerator.genFailResult("用户名不存在");
        }

        user.setUpdatedAt(new Date());
        userMapper.updatePassword(user);
        Result result= ResultGenerator.genSuccessResult();
        result.setData(user);
        return result;
    }

    @Override
    public Result updateUser(User user) {

        //根据用户名查询是否存在
        User newUser = userMapper.findUserByUserName(user.getUserName(),user.getId());

        //用户名不可重复
        if (null != newUser){
            return ResultGenerator.genFailResult(ResultCode.USER_ALREADY_EXIST,"用户名已存在，请登录");
        }

        User adminUser = userMapper.findUserByUserName(user.getUserName(),null);
        if (adminUser != null){
            if ("admin".equals(adminUser.getUserName())){
                if (null != user.getRoleId()){
                    if(user.getRoleId() != 0){
                        return ResultGenerator.genFailResult(ResultCode.ADMIN_ERROR,"管理员权限不可修改");
                    }
                }
            }
        }

        user.setUpdatedAt(new Date());
        update(user);
        Result result= ResultGenerator.genSuccessResult();
        result.setData(user);
        return result;
    }

    @Override
    public Result add(User user) {

        User newUser = userMapper.findUserByUserName(user.getUserName(),null);

        if (null != newUser){
            return ResultGenerator.genFailResult(ResultCode.USER_ALREADY_EXIST,"用户名已存在，请登录");
        }

        user.setCreatedAt(new Date());
        user.setIsDelete(false);
        if (user.getRoleId() == null){
            user.setRoleId(1L);
        }
        if (user.getStatus() == null){
            user.setStatus(1);
        }
        save(user);
        Result result= ResultGenerator.genSuccessResult();
        result.setData(user);
        return result;
    }

}
