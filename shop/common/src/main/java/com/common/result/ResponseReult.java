package com.common.result;

public class ResponseReult<T> {
	
	private int code;
	private String msg;
	private T data;
	private boolean isSuccess;
	
	/**
	 *  成功时候的调用
	 * */
	public static  <T> ResponseReult<T> success(T data){
		return new ResponseReult<T>(data, true, 200, "success");
	}
	private ResponseReult(T data, boolean isSuccess, int code, String msg) {
		this.data = data;
		this.isSuccess = isSuccess;
		this.code = code;
		this.msg = msg;
	}


	/**
	 *  失败时候的调用
	 * */
	public static  <T> ResponseReult<T> error(CodeMsg codeMsg){
		return new ResponseReult<T>(codeMsg, false);
	}
	private ResponseReult(CodeMsg codeMsg, boolean isSuccess) {
		if(codeMsg != null) {
			this.code = codeMsg.getCode();
			this.msg = codeMsg.getMsg();
		}
		this.isSuccess = isSuccess;
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
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
