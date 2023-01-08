package com.techelevator.npgeek;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.dao.ParkDAO;
import com.techelevator.npgeek.dao.SurveyDAO;
import com.techelevator.npgeek.dao.WeatherDAO;
import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.Search;
import com.techelevator.npgeek.model.Survey;
import com.techelevator.npgeek.model.Weather;

@Component
public class NationalParkSystem {
	@Autowired
	private ParkDAO parkDAO;
	
	@Autowired
	private WeatherDAO weatherDAO;
	
	@Autowired
	private SurveyDAO surveyDAO;
	
	public List<Park> getAllParks() {
		return parkDAO.getAllParks();
	}
	
	public Park getParkByParkCode(String parkCode) {
		return parkDAO.getParkByParkCode(parkCode);
	}
	
	public List<String> getAllParkStates() {
		return parkDAO.getAllColumnValuesByColumn("state");
	}
	
	public List<String> getAllParkClimates() {
		return parkDAO.getAllColumnValuesByColumn("climate");
	}
	
	public List<Park> getParksBySearch(Search search) {
		if (search.getState() == null) {
			search.setState("all");
		}
		if (search.getClimate() == null) {
			search.setClimate("all");
		}
		
		return parkDAO.getParksBySearch(search.getState(), search.getMinElevationFeet(), search.getMaxElevationFeet(), search.getMinMilesOfTrail(), search.getNeedsCampsites(), search.getClimate());
	}

	public List<Weather> getWeatherForecastByParkCode(String parkCode, String tempUnit) {
		if (tempUnit != null && tempUnit.equals("C")) {
			return weatherDAO.getCWeatherForecastByParkCode(parkCode);
		}
		return weatherDAO.getFWeatherForecastByParkCode(parkCode);
	}
	
	public void submitSurvey(Survey survey) {
		surveyDAO.submitSurvey(survey.getParkCode(), survey.getEmailAddress(), survey.getState(), survey.getActivityLevel());
	}
	
	public boolean emailHasAlreadySubmittedSurvey(String emailAddress) {
		boolean alreadySubmitted = false;
		
		if (surveyDAO.getSurveyByEmail(emailAddress) != null) {
			alreadySubmitted = true;
		}
		
		return alreadySubmitted;
	}
	
	public List<Park> getFavoriteParks() {
		return surveyDAO.getFavoriteParks();
	}
}
