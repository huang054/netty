package com.netty.config;

import org.springframework.web.client.RestTemplate;


public class NetworkConfig {
	
	private static RestTemplate restTemplate = new RestTemplate();
	


	public static RestTemplate getRestTemplate() {
		return restTemplate;
	}




	
	
}
