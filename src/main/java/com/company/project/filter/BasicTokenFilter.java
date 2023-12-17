package com.company.project.filter;

import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import com.company.project.core.ResultGenerator;
import com.company.project.exception.business.InvalidTokenException;
import com.company.project.exception.business.InvalidUserException;
import com.company.project.exception.business.NotLoginException;
import com.company.project.exception.business.OutTimeToken;
import com.company.project.utils.*;
import com.company.project.vo.SysUserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

@WebFilter(urlPatterns = "/**")
public class BasicTokenFilter implements Filter {

    @Resource
    private RedisService redisService;

    @Autowired
    private MatchUrlUtils matchUrlUtils;

    @Autowired
    private MagConfig magConfig;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        long curTime = System.currentTimeMillis();
        String url = httpRequest.getServletPath();
        Logger.debug(this, "url is " + url);

        String method = httpRequest.getMethod();
        try {
            if (!Constants.OPTIONS_METHOD.equals(method)) {
                //比较那些路径不再拦截
                if (!matchUrlUtils.checkUrls(url)) {
                    try {
                        doCheckToken(httpRequest, httpResponse, curTime, false);
                    } catch (OutTimeToken e) {
                        Logger.error(this, "token失效", e);
                        Result result = new Result();
                        result.setCode(ResultCode.OUT_TIME_TOKEN).setMessage("登录超时，请重新登录");
                        this.setResponseData(httpResponse, JsonBinderUtil.toJson(result));
                        return;
                    } catch (InvalidUserException e) {
                        Logger.error(this, ":用户不存在:", e);
                        Result result = new Result();
                        result.setCode(ResultCode.NOT_EXIST_USER_EXCEPTION).setMessage("用户不存在");
                        this.setResponseData(httpResponse, JsonBinderUtil.toJson(result));
                        return;
                    } catch (NotLoginException e) {
                        //Logger.error(this, ":用户未登录:", e);
                        Logger.error(this, ":token不存在或token错误请重新登录:", e);
                        Result result = new Result();
                        result.setCode(ResultCode.NOT_LOGIN_EXCEPTION).setMessage("token不存在或token错误请重新登录");
                        this.setResponseData(httpResponse, JsonBinderUtil.toJson(result));
                        return;
                    } catch (InvalidTokenException e) {
                        Logger.error(this, ":token不存在(请重新登录再访问):", e);
                        Result result = new Result();
                        result.setCode(ResultCode.NOT_EXIST_TOKEN_EXCEPTION).setMessage("请重新登录再访问");
                        this.setResponseData(httpResponse, JsonBinderUtil.toJson(result));
                        return;
                    } catch (Exception e) {
                        Logger.error(this, ":其他异常:", e);
                        this.setResponseData(httpResponse, JsonBinderUtil.toJson(ResultGenerator.genFailResult("未知异常")));
                        return;
                    }
                }
            }
            chain.doFilter(httpRequest, httpResponse);
        } catch (Exception e) {
            Logger.error(this, ":其他异常:", e);
        }
        return;
    }

    public void doCheckToken(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                             long curTime, boolean flag) throws Exception {
        String token = httpServletRequest.getHeader(Constants.TOKEN_NAME);
        Logger.info(this, ":toke is " + token + " , flag is " + flag);
        if (StringUtils.isBlank(token) && flag) {
            return;
        }
        if (StringUtils.isNotBlank(token)) {
            SysUserVo sysUserVo = (SysUserVo) redisService.get(Constants.REDIS_KEY_LOGIN + token);

            if (null != sysUserVo) {
                if (curTime > sysUserVo.getTokenExpireTime()) {
                    Logger.info(this, "token 失效--" + ":curTime is " + curTime + " expireTime is " + sysUserVo.getTokenExpireTime());
                    throw new OutTimeToken();
                } else {
                    //获取token过期时间 token续签， 当小于2505600秒时候访问接口，刷新token过期时间
                    Long expireTime = sysUserVo.getTokenExpireTime();
                    //Logger.info(this ,"token过期时间毫秒数"  +  expireTime + " 现在时间毫秒数 " + curTime);
                    //redisService.expire(ProjectConstant.REDIS_KEY_LOGIN + token);
                    //2505600等于29天 2419200000 = 28天 86400000 = 1天
                    if (BigDecimal.valueOf(expireTime).compareTo(BigDecimal.valueOf(System.currentTimeMillis()).subtract(new BigDecimal("86400000"))) == -1) {
                        Logger.info(this,"token续签" + token);
                        //如果再29天之内操作就会续签token
                        //重新设置过期时间，变换成毫秒
                        sysUserVo.setTokenExpireTime(System.currentTimeMillis() + magConfig.getExpireTime());
                        redisService.setWithExpire(Constants.REDIS_KEY_LOGIN + token, sysUserVo, magConfig.getExpireTime());
                    }
                }
            } else {
                throw new NotLoginException();
            }
        } else {
            throw new InvalidTokenException();
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void setResponseData(HttpServletResponse httpServletResponse, String result) throws IOException {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "*");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        out.print(result);
        out.flush();
        out.close();
    }

    @Override
    public void destroy() {
    }

}
