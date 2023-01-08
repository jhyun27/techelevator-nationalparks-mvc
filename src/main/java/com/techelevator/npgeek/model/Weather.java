package com.techelevator.npgeek.model;

import java.util.List;

public class Weather {
	
	private String parkCode;
	private int day;
	private int low;
	private int high;
	private String forecast;
	private List<String> advisoryMessages;
	
	
	public String getParkCode() {
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getLow() {
		return low;
	}
	public void setLow(int low) {
		this.low = low;
	}
	public int getHigh() {
		return high;
	}
	public void setHigh(int high) {
		this.high = high;
	}
	public String getForecast() {
		return forecast;
	}
	public void setForecast(String forecast) {
		this.forecast = forecast;
	}
	public List<String> getAdvisoryMessages() {
		return advisoryMessages;
	}
	public void setAdvisoryMessages(List<String> advisoryMessages) {
		this.advisoryMessages = advisoryMessages;
	}

}
