package com.onlinehubsolutions.DynamicReportEngine.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReportEngineModel {
	private final String id;
	private final String param1;
	private final String param2;
	private final String param3;
	private final String param4;
	private final String param5;
	private final String format;

	public ReportEngineModel(
		@JsonProperty("id") String id,
		@JsonProperty("param1") String param1,
		@JsonProperty("param2") String param2,
		@JsonProperty("param3") String param3,
		@JsonProperty("param4") String param4,
		@JsonProperty("param5") String param5,
		@JsonProperty("format") String format
	) {
		this.id = id;
		this.param1 = param1;	
		this.param2 = param2;
		this.param3 = param3;
		this.param4 = param4;
		this.param5 = param5;
		this.format = format;
	}

	public String getId() {
		return id;
	}

	public String getParam1() {
		return param1;
	}

	public String getParam2() {
		return param2;
	}

	public String getParam3() {
		return param3;
	}

	public String getParam4() {
		return param4;
	}

	public String getParam5() {
		return param5;
	}

	public String getFormat() {
		return format;
	}
}
