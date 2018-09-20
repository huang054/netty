package com.netty.common;

public class Constant {
	
	public static final String OP_CODE 		  		 = "opCode";
	
	public static final String OP_CODE_BIND    		 = "10001";//设备和用户绑定
	public static final String OP_CODE_TIME    		 = "10002";//获取系统时间
	public static final String OP_CODE_LOGIN   		 = "10003";//客户端登录
	
	public static final String OP_CODE_PLAY_LIST       = "10011";//播放音频
	public static final String OP_CODE_PLAY_PAUSE      = "10012";//暂停当前的
	public static final String OP_CODE_PLAY_GOON       = "10013";//继续当前的
	public static final String OP_CODE_PLAY_M  		  = "10014";//M键一键按下请求精选节目列表

	
	public static final String OP_CODE_STATUS  		  = "10021";//上报状态
	public static final String OP_CODE_Config  		  = "10031";//修改配置
	
	public static final String qiniuDomain 			  = "http://p81hvnvrf.bkt.clouddn.com/";
	public static final String apiServer   			  = "http://localhost:80";
	
	public static final int BIGEN_PLAY=1;    //播放返回得状态
	public static final int PAUSE_PLAY=2;
	public static final int END_PLAY=3;
	
	
}
