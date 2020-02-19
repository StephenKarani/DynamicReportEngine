package com.onlinehubsolutions.DynamicReportEngine.dao;

import com.onlinehubsolutions.DynamicReportEngine.model.ReportEngineModel;

public interface ReportEngineDao {
	String generateReport(ReportEngineModel reportEngineModel);
	byte[] generateReportFile(ReportEngineModel reportEngineModel);
}
