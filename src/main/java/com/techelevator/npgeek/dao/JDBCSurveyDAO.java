package com.techelevator.npgeek.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.Survey;

@Component
public class JDBCSurveyDAO implements SurveyDAO{

private JdbcTemplate jdbcTemplate;
	
	@Autowired 
	public JDBCSurveyDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void submitSurvey(String parkCode, String emailAddress, String state, String activityLevel) {
		String insertSQL = "INSERT INTO survey_result (parkcode, emailaddress, state, activitylevel) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(insertSQL, parkCode, emailAddress, state, activityLevel);
	}
	
	@Override
	public Survey getSurveyByEmail(String emailAddress) {
		String selectSQL = "SELECT surveyid, " + 
				"parkcode, " + 
				"emailaddress, " + 
				"state, " + 
				"activitylevel FROM survey_result WHERE emailAddress = ?";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(selectSQL, emailAddress);
		Survey survey = null;
		if (rows.next()) {
			survey = mapRowToSurvey(rows);
		}
		
		return survey;
	}

	@Override
	public List<Park> getFavoriteParks() {
		
		String selectSQL = "SELECT sr.parkcode, COUNT(*) AS votes, " + 
				"parkname, " +
				"park.state, " +
				"acreage, " + 
				"elevationinfeet, " + 
				"milesoftrail, " + 
				"numberofcampsites, " + 
				"climate, " + 
				"yearfounded, " + 
				"annualvisitorcount, " + 
				"inspirationalquote, " + 
				"inspirationalquotesource, " + 
				"parkdescription, " + 
				"entryfee, " + 
				"numberofanimalspecies " + 
				"FROM survey_result sr " + 
				"JOIN park ON sr.parkcode = park.parkcode " + 
				"GROUP BY sr.parkcode, " + 
				"parkname, " +
				"park.state, " +
				"acreage, " + 
				"elevationinfeet, " + 
				"milesoftrail, " + 
				"numberofcampsites, " + 
				"climate, " + 
				"yearfounded, " + 
				"annualvisitorcount, " + 
				"inspirationalquote, " + 
				"inspirationalquotesource, " + 
				"parkdescription, " + 
				"entryfee, " + 
				"numberofanimalspecies " + 
				"ORDER BY votes DESC, parkname";

		List<Park> parks = new ArrayList<Park>();
		SqlRowSet rows = jdbcTemplate.queryForRowSet(selectSQL);
		while (rows.next()) {
			Park park = JDBCParkDAO.mapRowToPark(rows);
			park.setNumberOfVotes(rows.getInt("votes"));
			parks.add(park);
		}
		
		return parks;
	}
	
	private Survey mapRowToSurvey(SqlRowSet row) {
		Survey survey = new Survey();
		survey.setSurveyid(row.getInt("surveyid"));
		survey.setParkCode(row.getString("parkcode"));
		survey.setEmailAddress("emailaddress");
		survey.setState(row.getString("state"));
		survey.setActivityLevel(row.getString("activitylevel"));
		
		return survey;
	}
}
