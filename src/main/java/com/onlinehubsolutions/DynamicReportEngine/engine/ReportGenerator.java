package com.onlinehubsolutions.DynamicReportEngine.engine;

import java.io.File;
import java.sql.Connection;
import java.util.Map;

public interface ReportGenerator {
	byte[] processorEngine(
			String reportFormat, 
			String reportTemplate, 
			String reportStyleFile, 
			String reportName);
	
	void dataEngine(
			String reportName, 
			String reportDataFile, 
			Map<String, String> map,
			Connection Conn);
	
	byte[] getBytesFromFile(File file) throws Exception;
}
