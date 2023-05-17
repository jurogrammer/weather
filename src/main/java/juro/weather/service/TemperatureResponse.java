package juro.weather.service;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TemperatureResponse {
	@JsonProperty("stnId")
	private int stationId;

	@JsonProperty("avgTa")
	private double averageTemperature;

	@JsonProperty("maxTa")
	private double maximumTemperature;

	@JsonProperty("minTa")
	private double minimumTemperature;

	@JsonProperty("tma")
	private String formattedDate;

	@JsonProperty("dt")
	private String date;

	@JsonProperty("stnNm")
	private String stationName;

	@JsonProperty("lati")
	private double latitude;

	@JsonProperty("lngt")
	private double longitude;
}
