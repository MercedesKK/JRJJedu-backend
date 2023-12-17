package com.company.project.exception.business;

import com.company.project.core.ResultCode;
import com.company.project.core.ServiceException;

public class NotLoginException extends ServiceException {
    /**
	 *
	 */
	private static final long serialVersionUID = -6664597933525995502L;

    public ResultCode getCode() {
        return ResultCode.NOT_LOGIN_EXCEPTION;
    }

    public String getErrorMessage() {
        return String.format("用户未登录,请重新登录");
    }
}
