package com.netty.dao;


import java.math.BigDecimal;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.netty.model.UserModel;


/**
 * Created by Administrator on 2018/4/28.
 */
@Repository
public interface UserDAO extends JpaRepository<UserModel, Long> {
 

}
