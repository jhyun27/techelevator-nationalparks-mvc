package com.techelevator.npgeek.dao;

import java.util.List;

import javax.sql.DataSource;

import org.junit.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.DAOIntegrationTest;
import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.Weather;

public class JDBCWeatherDAOIntegrationTest extends DAOIntegrationTest{
	private JdbcTemplate jdbcTemplate;
	private JDBCWeatherDAO weatherDAO;
	private DataSource dataSource = getDataSource();
	private int originalRowCount;

	@Before
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		weatherDAO = new JDBCWeatherDAO(dataSource);
		originalRowCount = getRowCount();
		createWeatherSet();
	}
	
	@Test
	public void get_f_weather_forecast_by_parkcode() {
		List<Weather> weathers = weatherDAO.getFWeatherForecastByParkCode("ABCD");
		Weather weather1 = weathers.get(0);
		Weather weather2 = weathers.get(1);
		Weather weather3 = weathers.get(2);
		Weather weather4 = weathers.get(3);
		Weather weather5 = weathers.get(4);
		
		Assert.assertEquals(5, weather1.getLow());
		Assert.assertEquals(5, weather2.getLow());
		Assert.assertEquals(60, weather3.getLow());
		Assert.assertEquals(60, weather4.getLow());
		Assert.assertEquals(-10, weather5.getLow());
		
		Assert.assertEquals("partlyCloudy", weather1.getForecast());
		Assert.assertEquals(99, weather2.getHigh());
	}
	
	@Test
	public void get_c_weather_forecast_by_parkcode() {
		List<Weather> weathers = weatherDAO.getCWeatherForecastByParkCode("ABCD");
		Weather weather1 = weathers.get(0);
		Weather weather2 = weathers.get(1);
		Weather weather3 = weathers.get(2);
		Weather weather4 = weathers.get(3);
		Weather weather5 = weathers.get(4);
		
		Assert.assertEquals(-15, weather1.getLow());
		Assert.assertEquals(-15, weather2.getLow());
		Assert.assertEquals(15, weather3.getLow());
		Assert.assertEquals(15, weather4.getLow());
		Assert.assertEquals(-23, weather5.getLow());
		
		Assert.assertEquals("partlyCloudy", weather1.getForecast());
		Assert.assertEquals(37, weather2.getHigh());
	}
	
	private void createWeatherSet() {
		Park park = makeNewPark();
		savePark(park);
		
		String insertSQL = "INSERT INTO weather (parkcode, fivedayforecastvalue, low, high, forecast) VALUES "
				+ "(?, ?, ?, ?, ?)";
		jdbcTemplate.update(insertSQL, "ABCD", 1, 5, 10, "partly cloudy");
		jdbcTemplate.update(insertSQL, "ABCD", 2, 5, 99, "sunny");
		jdbcTemplate.update(insertSQL, "ABCD", 3, 60, 80, "snow");
		jdbcTemplate.update(insertSQL, "ABCD", 4, 60, 82, "rain");
		jdbcTemplate.update(insertSQL, "ABCD", 5, -10, 15, "thunderstorms");
	}
	
	private void savePark(Park park) {
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
		park.setParkCode("ABCD");
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
	
	private int getRowCount() {
		String selectSql = "SELECT COUNT(*) AS numberOfRows FROM weather";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(selectSql);
		rows.next();
		return rows.getInt("numberOfRows");
	}
}
