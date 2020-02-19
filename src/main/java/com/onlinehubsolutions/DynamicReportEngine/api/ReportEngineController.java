package com.onlinehubsolutions.DynamicReportEngine.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.onlinehubsolutions.DynamicReportEngine.model.ReportEngineModel;
import com.onlinehubsolutions.DynamicReportEngine.service.ReportEngineService;

@RequestMapping("api/v1/GenerateReport")
@RestController
public class ReportEngineController {
    private final ReportEngineService reportEngineService;

    @Autowired
    public ReportEngineController(ReportEngineService reportEngService) {
        this.reportEngineService = reportEngService;
    }

    //@PostMapping
    public @ResponseBody String generateReport(@Valid @NonNull @RequestBody ReportEngineModel reportEngineModel) {
    	return this.reportEngineService.generateReport(reportEngineModel);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public @ResponseBody byte[] generateReportFile(@Valid @NonNull @RequestBody ReportEngineModel reportEngineModel) {
    	return this.reportEngineService.generateReportFile(reportEngineModel);
    }
}
