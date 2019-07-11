package com.mqb.springbootspringsecurity.utils;

import java.io.Serializable;

public class ProjectError extends ResultError implements Serializable {

	/**
	 * 请求的响应结果
	 */
	private static final long serialVersionUID = 1L;

	private int code;// 自定义错误编码
	private int httpCode; // http状态码
	private String url; // 操作结果
	private String msg;// 提示消息

	public ProjectError() {
	}

	public static ProjectError failure(StatusCode StatusCode) {
		ProjectError ProjectError = new ProjectError();
		ProjectError.setStatusCode(StatusCode);
		return ProjectError;
	}

	public static ProjectError failure(StatusCode StatusCode, Object data) {
		ProjectError ProjectError = new ProjectError();
		ProjectError.setStatusCode(StatusCode);
		return ProjectError;
	}

	private void setStatusCode(StatusCode code) {
		this.code = code.code();
		this.httpCode = 500;
		this.msg = code.message();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
