package com.netty.service;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netty.dao.UserDAO;
import com.netty.model.UserModel;

import java.math.BigDecimal;
import java.util.Optional;

 
/**
 * Created by Administrator on 2018/4/28.
 */ 
@Service
public class UserService extends BaseService<UserModel>{


private static UserService self  ;
	
	private UserService() {
		self = this;
	}
	
	public static UserService getInstance() {
		return self;
	}


  
 

}
