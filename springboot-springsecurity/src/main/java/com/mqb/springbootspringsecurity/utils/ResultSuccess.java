package com.mqb.springbootspringsecurity.utils;

public class ResultSuccess {

	private Integer code ;
	private String referer; // 发送请求的地址
	private String redirect; // 重定向的地址
	private String msg; //
	private Object data;

	public ResultSuccess() {
		super();
	}

	public ResultSuccess(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public ResultSuccess(String msg, Object data) {
		this.data = data;
	}

	public ResultSuccess(Integer code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public ResultSuccess(String msg) {
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getReferer() {
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
