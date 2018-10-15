package com.netty.dto;

public class AudioDTO {
	
	private int index;
	
	private Long id;
	
    private Long albumId;//专辑ID
    
    private String albumName;//专辑名称
    
    private int durantionSec;//播放时长，以秒为单位

    private String title;//音频名

    private String author;//作者名
    
    private String filepath;//文件路径
    
    private Long createTime;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public int getDurantionSec() {
		return durantionSec;
	}

	public void setDurantionSec(int durantionSec) {
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

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filePath) {
		this.filepath = filePath;
	}

	public Long getPostTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
    
	
    
}
