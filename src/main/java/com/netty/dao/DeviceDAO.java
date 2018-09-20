package com.netty.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.netty.model.DeviceModel;


public interface DeviceDAO extends JpaRepository<DeviceModel, Long> {

}
