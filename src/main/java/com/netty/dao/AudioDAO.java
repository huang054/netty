package com.netty.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.netty.model.AudioModel;

public interface AudioDAO extends JpaRepository<AudioModel, Long> {

}

