package com.netty.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.netty.model.ReportStatusModel;

public interface ReportStatusDAO extends JpaRepository<ReportStatusModel, Long> {

}
