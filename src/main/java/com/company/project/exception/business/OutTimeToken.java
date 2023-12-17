package com.company.project.exception.business;

import com.company.project.core.ResultCode;
import com.company.project.core.ServiceException;

public class OutTimeToken extends ServiceException {
    private static final long serialVersionUID = -7051836414685780479L;

    public ResultCode getCode() {
        return ResultCode.OUT_TIME_TOKEN;
    }

    public String getErrorMessage() {
        //应该使用统一异常
        return "登录超时，请重新登录";
    }
}
