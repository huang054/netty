package com.netty.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "report_status")
public class ReportStatusModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 64)
	private String ticket;

	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createTime;//创建时间
	
	@Column(length = 64)
	private String userId;
	
	@Column(length = 64)
	private String deviceId;
	
	private Long audioId;
	
	private Long albumId;
	
	private Integer audioStatus;
	
	private Integer audioPlayTime;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	public Integer getAudioStatus() {
		return audioStatus;
	}
	public void setAudioStatus(Integer audioStatus) {
		this.audioStatus = audioStatus;
	}
	public Integer getAudioPlayTime() {
		return audioPlayTime;
	}
	public void setAudioPlayTime(Integer audioPlayTime) {
		this.audioPlayTime = audioPlayTime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAudioId() {
		return audioId;
	}
	public void setAudioId(Long audioId) {
		this.audioId = audioId;
	}
	public Long getAlbumId() {
		return albumId;
	}
	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}
	
	
}
