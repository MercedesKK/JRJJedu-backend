package com.company.project.exception.business;

import com.company.project.core.ResultCode;
import com.company.project.core.ServiceException;

public class InvalidUserException extends ServiceException {

	private static final long serialVersionUID = 4367670873598595923L;

	private String userNo = null;

	public InvalidUserException(String userNo) {
		this.userNo = userNo;
	}
	public InvalidUserException() {
		this.userNo = "";
	}

	public ResultCode getCode() {
		return ResultCode.NOT_EXIST_USER_EXCEPTION;
	}

	public String getErrorMessage() {
		return String.format("用户名或密码错误",this.userNo);
	}


}
