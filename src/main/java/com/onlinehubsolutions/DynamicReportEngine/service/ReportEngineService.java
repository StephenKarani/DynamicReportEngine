package com.onlinehubsolutions.DynamicReportEngine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.onlinehubsolutions.DynamicReportEngine.dao.ReportEngineDao;
import com.onlinehubsolutions.DynamicReportEngine.model.ReportEngineModel;

@Service
public class ReportEngineService {
	private final ReportEngineDao reportEngineDao;
	
	@Autowired
	public ReportEngineService(@Qualifier("sqlDao") ReportEngineDao reportEngineDao) {
		this.reportEngineDao = reportEngineDao;
	}
	
	public String generateReport(ReportEngineModel reportEngineModel) {
		return this.reportEngineDao.generateReport(reportEngineModel);
	}
	
	public byte[] generateReportFile(ReportEngineModel reportEngineModel) {
		return this.reportEngineDao.generateReportFile(reportEngineModel);
	}
}
