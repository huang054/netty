package com.netty.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Date;

@Entity(name="device")
// 构建数据库表实体
@Table(name = "device",uniqueConstraints = { @UniqueConstraint(columnNames = { "deviceId" }) } )
public class DeviceModel {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(length = 64)
	private String ticket;

	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createTime;//创建时间
    
	@NotNull(message="请输入deviceId")
    @Column(length = 64)
    private String deviceId;//设备ID，UUID
    
	
    @Column(length = 64)
    private String preUserId;//用户手机号
    
    @Column(length = 64)
    private String deviceName;//设备名
    
    @Column(length = 64)
    private String version;//固件版本
    
    private Boolean status;//当前状态,"0"未连接;"1"App已连接
    
    @NotNull(message="请输入userId")
    private String userId;//用户id
    
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getPreUserId() {
		return preUserId;
	}

	public void setPreUserId(String preUserId) {
		this.preUserId = preUserId;
	}

	
    
    
    
    
}
