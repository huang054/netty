package com.netty.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.netty.model.RecentAudioModel;



/**
 * Created by Administrator on 2018/5/11.
 */ 
@Repository
public interface RecentAudioDAO extends JpaRepository<RecentAudioModel,Long> {
}
 