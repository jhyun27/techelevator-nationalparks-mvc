package com.techelevator.npgeek.model;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;

public class Survey {

	private int surveyid;
	private String parkCode;
	@NotBlank(message="Please enter your email.")
	@Pattern(regexp="^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message="Invalid email.")
	private String emailAddress;
	@NotBlank(message="Please enter your state.")
	private String state;
	@NotBlank(message="Please choose your activity level.")
	private String activityLevel;
	
	public int getSurveyid() {
		return surveyid;
	}
	public void setSurveyid(int surveyid) {
		this.surveyid = surveyid;
	}
	public String getParkCode() {
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getActivityLevel() {
		return activityLevel;
	}
	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}
	
	
}
