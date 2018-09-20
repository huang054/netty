package com.netty.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


//音频表
@Entity(name ="audio")
@Table(name = "audio")
public class AudioModel {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 64)
	private String ticket;


	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createTime;//创建时间

	@Column
	@NotNull(message="请选择专辑")
    private Long albumId;//专辑ID

	@Column(columnDefinition="varchar(255)  NOT null")
    private String albumName;//专辑名称

	@Column(columnDefinition="bigint(20) DEFAULT 0")
	private Long durantionSec;//播放时长，以秒为单位

	@NotBlank(message="音频名不能为空")
    private String title;//音频名

	@NotBlank(message="请填写作者")
    private String author;//作者名
    
    private String authorId;//作者ID

	@Column(columnDefinition="bigint(20) DEFAULT 0")
    private Long readCount;//阅读数

	@Column(columnDefinition="bigint(20) DEFAULT 0")
    private Long praiseCount;//点赞数
    
    private String fileName;//文件名称
    
    private String filepath;//文件路径
    
    private Boolean isLocal;//文件是否存储在本地
    
    private String label;//标签
    
    private String remark;//备注
    
    private String fileSize;//文件大小

	@Column(columnDefinition="bigint(20) DEFAULT 0")
    private Long playCount;//播放次数
    
    private String md5;
    
    private Integer priority;//推荐
    
    
    
	public Boolean getIsLocal() {
		return isLocal;
	}

	public void setIsLocal(Boolean isLocal) {
		this.isLocal = isLocal;
	}


	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}


	@NotNull(message="节目标签不能为空")
	private Long tagId;//节目标签

	
	
	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public Long getPlayCount() {
		return playCount;
	}

	public void setPlayCount(Long playCount) {
		this.playCount = playCount;
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

	public Long getDurantionSec() {
		return durantionSec;
	}

	public void setDurantionSec(Long durantionSec) {
		this.durantionSec = durantionSec;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public Long getReadCount() {
		return readCount;
	}

	public void setReadCount(Long readCount) {
		this.readCount = readCount;
	}

	public Long getPraiseCount() {
		return praiseCount;
	}

	public void setPraiseCount(Long praiseCount) {
		this.praiseCount = praiseCount;
	}

	public Long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}


	public Boolean isLocal() {
		return isLocal;
	}

	public void setLocal(Boolean local) {
		isLocal = local;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}
}
