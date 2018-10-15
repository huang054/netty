package com.netty.service;



import org.springframework.stereotype.Service;

import com.netty.model.RecentAlbumModel;

/**
 * Created by Administrator on 2018/5/11.
 */  
@Service
public class RecentAlbumService extends BaseService<RecentAlbumModel>{


private static RecentAlbumService self  ;
	
	private RecentAlbumService() {
		self = this;
	}
	
	public static RecentAlbumService getInstance() {
		return self;
	}
}
