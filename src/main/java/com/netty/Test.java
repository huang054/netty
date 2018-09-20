package com.netty;




import com.netty.common.AJpushUtils;




public class Test {
	public static void main(String[]a) {
//		RestTemplate restTemplate = new RestTemplate();
//		String nettyServerURL = "http://localhost:8080";
//		String userId = "aa";
//		String deviceId = "deviceIdfff";
//		String opCode= "10012";
//		AudioModel AudioModel = new AudioModel();
//		AudioModel.setFilepath("/aaaadasdf");
//		//String body = restTemplate.postForObject(nettyServerURL+"/remote/play/{userId}/{deviceId}/{opCode}", new Object[] {AudioModel},String.class,userId,deviceId,opCode);
//		//System.out.println(body);
//		//Iterable<AudioDTO > audioList = NetworkConfig.getRestTemplate().getForObject(Constant.apiServer+"/sysRecommend/recommendServer.do", Iterable.class);
//		//NetworkConfig.getRestTemplate().getForEntity(url, responseType)47.106.148.243
//		Map<String,Object> rs = restTemplate.postForObject("http://47.106.148.243:8085/remote/isOnline", new String[] {"deviceId","B"}, Map.class);
//		System.out.println(JSON.toJSONString(rs));
		
		AJpushUtils.push("18929567567","data","测试");
		
}
	
	
	
	


}
