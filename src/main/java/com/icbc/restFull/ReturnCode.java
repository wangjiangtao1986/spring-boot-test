package com.icbc.restFull;

/**
 * 错误枚举类
 * 处理结果枚举
 * @author Administrator
 *
 */
public enum ReturnCode {

	SUCCEES(00000, "请求成功"),
	
	PARAMETER_ERROR(10001, "请求参数错误"),
	
	LOGIC_ERROR(20001, "逻辑处理错误"),
	
	DATABASE_ERROR(30001, "数据库查询错误"),
	
	SYSTEM_ERROR(40001, "系统错误"),
	
	PRIV_ERROR(50001, "用户权限错误")
	
//	其他
	
	;

	public int code;
	public String message;

	private ReturnCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
