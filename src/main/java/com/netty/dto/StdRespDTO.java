package com.netty.dto;

public class StdRespDTO<T> {
	
	public static final String  CODE_SUCCESS = "000";
	public static final String  MSG_SUCCESS  = "success";
	public static final String  CODE_FAILED  = "999";
	public static final String  MSG_FAILED   = "failed";
	
	private String    opCode 				;
	private String 	  msg 	=  CODE_SUCCESS;
	private T	   	  data;
	private String    opConn = "1";//默认是返回
	private String resCode;
	
	public String getOpCode() {
		return opCode;
	}


	public void setOpCode(String opCode) {
		this.opCode = opCode;
	}


	public StdRespDTO(String opCode) {
		this.opCode = opCode;
		
	}
	
	
	public StdRespDTO (boolean sucess){
		if(sucess) {
			this.resCode = CODE_SUCCESS;
			this.msg  = MSG_SUCCESS;
		}else {
			this.resCode = CODE_FAILED;
			this.msg  = MSG_FAILED;
			
		}
	}
	

	public String getOpConn() {
		return opConn;
	}

	public void setOpConn(String opConn) {
		this.opConn = opConn;
	}


	public StdRespDTO (boolean sucess,String msg){
		if(sucess) {
			this.resCode = CODE_SUCCESS;
		}else {
			this.resCode = CODE_FAILED;
		}
		this.msg = msg;
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


	public String getResCode() {
		return resCode;
	}


	public void setResCode(String resCode) {
		this.resCode = resCode;
	}
	


}
