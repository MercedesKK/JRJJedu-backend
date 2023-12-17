package com.company.project.web;

import com.company.project.common.BaseController;
import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import com.company.project.core.ResultGenerator;
import com.company.project.model.User;
import com.company.project.service.UserService;
import com.company.project.utils.Logger;
import com.company.project.vo.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
@Api(tags = {"/user"}, description = "用户管理模块")
public class UserController extends BaseController {
    @Resource
    private UserService userService;

    @ApiOperation(value = "用户退出", notes = "用户退出")
    @RequestMapping(value = "/logout", method = {RequestMethod.POST})
    public Result logout(@RequestParam Long userId) {
        Logger.info(this,"/user/logout 用户退出接口入参 : " + userId);
        return userService.logout(userId);
    }

    @ApiOperation(value = "用户登录", notes = "用户登录")
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public Result login(@RequestBody LoginVo vo, HttpServletRequest request) {
        Logger.info(this, "/user/login 用户登录接口入参 :" + vo);
        return userService.login(vo);
    }

    @ApiOperation(value = "批量发送邮件", notes = "批量发送邮件")
    @RequestMapping(value = "/batchSendEmail", method = {RequestMethod.POST})
    public Result batchSendEmail(@RequestBody String ids) {
        return userService.batchSendEmail(ids);
    }

    @ApiOperation(value = "发送找回密码验证码", notes = "发送找回密码验证码")
    @RequestMapping(value = "/sendPasswordCode", method = {RequestMethod.POST})
    public Result sendPasswordCode(@RequestParam String phone) {
        return userService.sendPasswordCode(phone);
    }

    @ApiOperation(value = "发送登录验证码", notes = "发送登录验证码")
    @RequestMapping(value = "/sendLogCode", method = {RequestMethod.POST})
    public Result sendLogCode(@RequestParam String phone) {
        return userService.sendLogCode(phone);
    }

    /*@ApiOperation(value = "生成验证码", notes = "生成验证码")
    @RequestMapping(value = "/captcha", method = {RequestMethod.POST})
    public Result captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return userService.captcha();
    }*/

    @ApiOperation(value = "用户注册", notes = "用户注册")
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody User user) {
        return userService.add(user);
    }

    @ApiOperation(value = "根据token获取用户详情", notes = "根据token获取用户详情")
    @RequestMapping(value = "/detailByToken", method = {RequestMethod.POST})
    public Result detailByToken() {
        if (super.getUserId() == null){
            return ResultGenerator.genFailResult(ResultCode.NOT_LOGIN_EXCEPTION,"token不存在或token错误请重新登录");
        }else {
            return userService.detailByToken(Long.valueOf(super.getUserId()));
        }
    }

    @ApiOperation(value = "删除用户", notes = "删除用户")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam(value = "id", required = false) Long id) {
        return userService.delete(id);
    }

    @ApiOperation(value = "修改用户", notes = "修改用户")
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result updateUser(@RequestBody User user) {
        return  userService.updateUser(user);
    }

    @ApiOperation(value = "修改用户密码", notes = "修改用户密码")
    @RequestMapping(value = "/updatePassword", method = {RequestMethod.POST})
    public Result updatePassword(@RequestBody User user) {
        return  userService.updatePassword(user);
    }

    @ApiOperation(value = "获取用户单个详情", notes = "获取用户单个详情")
    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam(value = "id", required = false) Long id) {
        //根据id查询详情
        if (null == id){
            return ResultGenerator.genSuccessResult(new User());
        }else {
            User user = userService.findById(id);
            if (null == user){
                return ResultGenerator.genSuccessResult(new User());
            }else {
                user.setRole(user.getRoleId());
                //返回查询的单个详情
                return ResultGenerator.genSuccessResult(user);
            }
        }
    }

    @ApiOperation(value = "分页查询用户", notes = "分页查询用户")
    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) User user) {
        return userService.list(user);
    }

    @ApiOperation(value = "分页查询所有表和字段", notes = "分页查询所有表和字段")
    @RequestMapping(value = "/findAllColumns", method = {RequestMethod.POST})
    public Result findAllColumns() {
        return userService.findAllColumns();
    }

}
