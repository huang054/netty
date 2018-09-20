package com.netty.service;
import org.springframework.stereotype.Service;
import com.netty.model.ReportStatusModel;

@Service
public class ReportStatusService  extends BaseService<ReportStatusModel> {
	
	private static ReportStatusService self  ;
	
	private ReportStatusService() {
		self = this;
	}
	
	public static ReportStatusService getInstance() {
		return self;
	}
}
