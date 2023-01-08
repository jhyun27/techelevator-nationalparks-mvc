package com.techelevator.npgeek.dao;

import java.util.List;
import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.Survey;

public interface SurveyDAO {

	List<Park> getFavoriteParks();
	void submitSurvey(String parkCode, String emailAddress, String state, String activityLevel);
	Survey getSurveyByEmail(String emailAddress);
}
