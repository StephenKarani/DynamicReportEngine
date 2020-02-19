package com.onlinehubsolutions.DynamicReportEngine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.onlinehubsolutions.DynamicReportEngine.connection.MySQLDatabaseConnection;
import com.onlinehubsolutions.DynamicReportEngine.engine.MySQLReportGenerator;
import com.onlinehubsolutions.DynamicReportEngine.model.ReportEngineModel;

@Repository("mySqlDao")
public class GenerateMySQLReports implements ReportEngineDao{
	
	private String reportId, param1, param2, param3, param4, param5, format;
    private HashMap<String, String> map = new HashMap<>();
    
	@Override
	public String generateReport(ReportEngineModel reportEngineModel) {
		MySQLReportGenerator reportGenerator = new MySQLReportGenerator();
		MySQLDatabaseConnection Conn = new MySQLDatabaseConnection();
		Connection conn = Conn.Connection();
        
        String reportName = "TEST_RPT";
		String reportDataFile = "test.xml";
		String reportFormat = format;
		String reportTemplate = "test_template.rtf";
		String reportStyleFile = "test_template.xsl";
		try {
            reportId = reportEngineModel.getId();
            param1 = reportEngineModel.getParam1();
            param2 = reportEngineModel.getParam2();
            param3 = reportEngineModel.getParam3();
            param4 = reportEngineModel.getParam4();
            param5 = reportEngineModel.getParam5();
            format = reportEngineModel.getFormat();
            
            map.put("param1", param1);
            map.put("param2", param2);
            map.put("param3", param3);
            map.put("param4", param4);
            map.put("param5", param5);
            
            reportName = "TEST_RPT";
			reportDataFile = "test.xml";
			reportFormat = format;
			reportTemplate = "test_template.rtf";
			reportStyleFile = "test_template.xsl";
			
			reportGenerator.dataEngine(reportName, reportDataFile, map, conn);
			reportGenerator.processorEngine(reportFormat, reportTemplate, reportStyleFile, reportName);
			
			map.clear(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
		return "Generated Successfully!!!";
	}

	@Override
	public byte[] generateReportFile(ReportEngineModel reportEngineModel) {
		MySQLReportGenerator reportGenerator = new MySQLReportGenerator();
		MySQLDatabaseConnection Conn = new MySQLDatabaseConnection();
		Connection conn = Conn.Connection();
		PreparedStatement pst = null;
		ResultSet rst = null;
		byte[] bytes = null;
        
        String reportName = "";
		String reportDataFile = "";
		String reportFormat = "";
		String reportTemplate = "";
		String reportStyleFile = "";
		String Query = "SELECT report_name, report_data_file, report_template, report_style_file "
				      + " FROM system_reports "
				      + "WHERE report_sht_description = ?";
		try {
            reportId = reportEngineModel.getId();
            param1 = reportEngineModel.getParam1();
            param2 = reportEngineModel.getParam2();
            param3 = reportEngineModel.getParam3();
            param4 = reportEngineModel.getParam4();
            param5 = reportEngineModel.getParam5();
            format = reportEngineModel.getFormat();
            
            map.put("param1", param1);
            map.put("param2", param2);
            map.put("param3", param3);
            map.put("param4", param4);
            map.put("param5", param5);
            
            pst = conn.prepareStatement(Query);
            pst.setString(1, reportId);
            rst = pst.executeQuery();
            
            while(rst.next()) { 
		        reportName = rst.getString("report_name");
				reportDataFile = rst.getString("report_data_file");
				reportFormat = format;
				reportTemplate = rst.getString("report_template");
				reportStyleFile = rst.getString("report_style_file");
            }
			
			reportGenerator.dataEngine(reportName, reportDataFile, map, conn);
			bytes = reportGenerator.processorEngine(reportFormat, reportTemplate, reportStyleFile, reportName);
			
			map.clear(); 
			
			rst.close();
			pst.close();
			conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	try {
				rst.close();
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
		return bytes;
	}

}