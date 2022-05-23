package com.onlinehubsolutions.DynamicReportEngine.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;

import com.sun.java.util.collections.ArrayList;
import com.sun.java.util.collections.Iterator;

import oracle.apps.xdo.dataengine.DataProcessor;
import oracle.apps.xdo.dataengine.Parameter;
import oracle.apps.xdo.template.FOProcessor;
import oracle.apps.xdo.template.RTFProcessor;

public class MySQLReportGenerator implements ReportGenerator{
	// static final String DOC_PATH = "C:\\Users\\Programmer\\Desktop\\Reports\\" ;
	static final String DOC_PATH = "src\\Reports\\MySql\\";
	private String param1, param2, param3, param4, param5;
	
	@Override
	public byte[] processorEngine(String reportFormat, String reportTemplate, String reportStyleFile,
			String reportName) {
		FOProcessor processor = new FOProcessor();
        byte[] bytes = null;
        try {
        	reportTemplate = DOC_PATH + reportTemplate;
        	reportStyleFile = DOC_PATH + reportStyleFile;
            reportName = DOC_PATH + reportName;
	            
            RTFProcessor rtfProcessor =
                new RTFProcessor(reportTemplate); //input template
            rtfProcessor.setOutput(reportStyleFile); // output file
            rtfProcessor.process();
            

            String filename = null;
            filename = reportName;

            String data = reportName + ".xml";
            String template = reportStyleFile;
            
            String output = null;
            

            if (reportFormat == null) {
                processor.setData(data);
                processor.setTemplate(template);
                processor.setOutput(filename + ".pdf");
                output = filename + ".pdf";
                processor.setOutputFormat(FOProcessor.FORMAT_PDF);
                
            } else {
                processor.setData(data);
                processor.setTemplate(template);
                processor.setOutput(filename + "." + reportFormat);
                output = filename + "." + reportFormat;
                if (reportFormat.equalsIgnoreCase("pdf")) {
                    processor.setOutput(filename + "." + reportFormat);
                    processor.setOutputFormat(FOProcessor.FORMAT_PDF);
                } else if (reportFormat.equalsIgnoreCase("rtf")) {
                    processor.setOutput(filename + "." + reportFormat);
                    processor.setOutputFormat(FOProcessor.FORMAT_RTF);
                } else if (reportFormat.equalsIgnoreCase("html")) {
                    processor.setOutput(filename + "." + reportFormat);
                    processor.setOutputFormat(FOProcessor.FORMAT_HTML);
                } else if (reportFormat.equalsIgnoreCase("xls")) {
                    processor.setOutput(filename + "." + reportFormat);
                    processor.setOutputFormat(FOProcessor.FORMAT_EXCEL);
                }
            }
            processor.generate();

            File file = new File(output);
            bytes = getBytesFromFile(file);
        }catch(Exception e) {
        	e.printStackTrace();
        }
        
        return bytes;
	}

	@Override
	public void dataEngine(String reportName, String reportDataFile, Map<String, String> map, Connection Conn) {
		try {
			reportDataFile = DOC_PATH + reportDataFile;
			reportName = DOC_PATH +  reportName;
			String data = reportDataFile;
			String output = reportName + ".xml";
			
			DataProcessor dataProcessor = new DataProcessor();
			dataProcessor.setDataTemplate(data);
			
			if(map.isEmpty()) {
				param1 = null;
				param2 = null;
				param3 = null;
				param4 = null;
				param5 = null;
			} else {
				param1 = map.get("param1");
				param2 = map.get("param2");
				param3 = map.get("param3");
				param4 = map.get("param4");
				param5 = map.get("param5");
			}
			
			ArrayList parameters = dataProcessor.getParameters();
            Iterator it = parameters.iterator();
            
            while(it.hasNext()) {
            	Parameter p = (Parameter)it.next();
            	
            	System.out.println("Parameter passed is " + p.getName());
            	if (p.getName().equals("PARAM_1")) {
                    p.setValue(param1);
                }
            	
            	if (p.getName().equals("PARAM_2")) {
                    p.setValue(param2);
                }
            	
            	if (p.getName().equals("PARAM_3")) {
                    p.setValue(param3);
                }
            	
            	if (p.getName().equals("PARAM_4")) {
                    p.setValue(param4);
                }
            	
            	if (p.getName().equals("PARAM_5")) {
                    p.setValue(param5);
                }
            }
            
            dataProcessor.setParameters(parameters);
            dataProcessor.setConnection(Conn);
            dataProcessor.setOutput(output);
            dataProcessor.processData();
            
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public byte[] getBytesFromFile(File file) throws Exception {
		InputStream is = new FileInputStream(file);

        // Get the size of the file
        long length = file.length();

        if (length > Integer.MAX_VALUE) {
            // File is too large
        }

        // Create the byte array to hold the data
        byte[] bytes = new byte[(int)length];

        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length &&
               (numRead = is.read(bytes, offset, bytes.length - offset)) >=
               0) {
            offset += numRead;
        }

        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            System.out.println("Could not read generated file");
        }

        // Close the input stream and return bytes
        is.close();
        return bytes;
	}
}
