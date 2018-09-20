package com.netty.model;

import java.io.Serializable;

public class PlayMessage implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userId;
	
	private String bitName;

	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBitName() {
		return bitName;
	}

	public void setBitName(String bitName) {
		this.bitName = bitName;
	}

	@Override
	public String toString() {
		return "PlayMessage [userId=" + userId + ", bitName=" + bitName + "]";
	}
	
	

}
