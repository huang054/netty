package com.netty.service;


import org.springframework.stereotype.Service;

import com.netty.model.RecentAudioModel;


/**
 * Created by Administrator on 2018/5/11.
 */  
@Service
public class RecentAudioService extends BaseService<RecentAudioModel>{
	
private static RecentAudioService self  ;
	
	private RecentAudioService() {
		self = this;
	}
	
	public static RecentAudioService getInstance() {
		return self;
	}
}
