package com.netty.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.netty.model.DeviceModel;


@Repository
public interface DeviceDAO extends JpaRepository<DeviceModel, Long> {

	@Query("select u from device u where u.deviceId=:deviceId")
	public DeviceModel findByDeviceId(@Param("deviceId") String deviceId);
}
