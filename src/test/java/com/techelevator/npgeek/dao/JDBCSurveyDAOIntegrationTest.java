package com.techelevator.npgeek.dao;

import java.util.List;

import javax.sql.DataSource;

import org.junit.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.DAOIntegrationTest;
import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.Survey;

public class JDBCSurveyDAOIntegrationTest extends DAOIntegrationTest{
	private JdbcTemplate jdbcTemplate;
	private JDBCSurveyDAO surveyDAO;
	private DataSource dataSource = getDataSource();
	private int originalRowCount;

	@Before
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		surveyDAO = new JDBCSurveyDAO(dataSource);
		originalRowCount = getRowCount();
	}
	
	@Test
	public void submit_survey_works() {
		surveyDAO.submitSurvey("HGHG", "hi@fgkjfhg.com", "Neverland", "Slowpoke");
		int newRowCount = getRowCount();
		
		Assert.assertEquals(originalRowCount + 1, newRowCount);
	}
	
	@Test 
	public void get_survey_by_email() {
		surveyDAO.submitSurvey("HGHG", "hi@fgkjfhg.com", "Neverland", "Slowpoke");
		Survey survey = surveyDAO.getSurveyByEmail("hi@fgkjfhg.com");
		
		Assert.assertNotNull(survey);
		Assert.assertEquals("Neverland", survey.getState());
		
		survey = surveyDAO.getSurveyByEmail("hicm");
		Assert.assertNull(survey);
	}
	
	@Test 
	public void get_favorite_parks() {
		Park park = makeNewPark();
		save(park);
		List<Park> parks = surveyDAO.getFavoriteParks();
		surveyDAO.submitSurvey(park.getParkCode(), "hi@fgkjfhg.com", park.getState(), "Slowpoke");
		
		List<Park> parks2 = surveyDAO.getFavoriteParks();
		
		Assert.assertEquals(parks.size() + 1, parks2.size());
		
	}
	
	private int getRowCount() {
		String selectSql = "SELECT COUNT(*) AS numberOfRows FROM survey_result";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(selectSql);
		rows.next();
		return rows.getInt("numberOfRows");
	}
	
	private void save(Park park) {
		String insertSQL = "INSERT INTO park (parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, "
				+ "numberofcampsites, climate, yearfounded, annualvisitorcount, inspirationalquote, inspirationalquotesource, "
				+ "parkdescription, entryfee, numberofanimalspecies) VALUES "
				+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(insertSQL, park.getParkCode(), park.getParkName(), park.getState(), park.getAcreage(), park.getElevationFt(),
				park.getMilesOfTrail(), park.getNumberOfCampsites(), park.getClimate(), park.getYearFounded(), park.getAnnualVisitorCount(), 
				park.getInspirationalQuote(), park.getInspirationalQuoteSource(), park.getParkDescription(), park.getEntryFee(), park.getNumberOfAnimalSpecies());
	}
	
	private Park makeNewPark() {
		Park park = new Park();
		park.setParkCode("HGHG");
		park.setParkName("Best park");
		park.setState("Neverland");
		park.setAcreage(5758967);
		park.setElevationFt(4000);
		park.setMilesOfTrail(222.3);
		park.setNumberOfCampsites(23);
		park.setClimate("Special");
		park.setYearFounded(1993);
		park.setAnnualVisitorCount(45749743);
		park.setInspirationalQuote("Beets, Bears, Battlestar Galactica");
		park.setInspirationalQuoteSource("-Dwight Shrute, The Office");
		park.setParkDescription("Best place ever");
		park.setEntryFee(5000);
		park.setNumberOfAnimalSpecies(234);
		
		return park;
	}
}
