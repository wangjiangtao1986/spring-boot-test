package com.icbc.exception;

public class PrivException extends RuntimeException {

	private static final long serialVersionUID = -1104674993657790703L;

	/**
	 * 生成序列异常时
	 */
	public static final PrivException DB_GET_SEQ_NEXT_VALUE_ERROR = new PrivException(10040008, "序列生成超时");

	/**
	 * 具体异常码
	 */
	protected int code;

	/**
	 * 异常信息
	 */
	protected String msg;

	/**
	 * 
	 * @param message
	 */
	public PrivException(String message) {
		super(message);
	}

	public PrivException(int code, String msgFormat, Object... args) {
		super(String.format(msgFormat, args));
		this.code = code;
		this.msg = String.format(msgFormat, args);
	}

	public PrivException(String message, Throwable cause) {
		super(message, cause);
	}

	public PrivException(Throwable cause) {
		super(cause);
	}

	/**
	 * 实例化异常
	 * 
	 * @param msgFormat
	 * @param args
	 * @return
	 */
	public PrivException newInstance(String msgFormat, Object... args) {
		return new PrivException(this.code, msgFormat, args);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
