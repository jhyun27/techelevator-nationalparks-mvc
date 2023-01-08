package com.techelevator.npgeek.dao;

import java.util.List;

import javax.sql.DataSource;
import org.junit.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.DAOIntegrationTest;
import com.techelevator.npgeek.model.Park;

public class JDBCParkDAOIntegrationTest extends DAOIntegrationTest {

	private JdbcTemplate jdbcTemplate;
	private JDBCParkDAO parkDAO;
	private DataSource dataSource = getDataSource();

	@Before
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		parkDAO = new JDBCParkDAO(dataSource);
	}

	@Test
	public void return_all_parks() {
		// Arrange
		Park parkOne = makeNewPark();
		Park parkTwo = makeNewPark();
		parkTwo.setParkCode("test2");

		String selectSql = "SELECT COUNT(*) AS numberOfRows FROM park";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(selectSql);
		rows.next();
		int originalRowCount = rows.getInt("numberOfRows");

		save(parkOne);
		save(parkTwo);

		// Act
		List<Park> parksReturned = parkDAO.getAllParks();

		// Assert
		Assert.assertNotNull(parksReturned);
		Assert.assertEquals(originalRowCount + 2, parksReturned.size());
	}
	
	@Test
	public void get_park_by_park_code() {
		Park park = makeNewPark();
		
		save(park);
		
		Park parkReturned = parkDAO.getParkByParkCode(park.getParkCode());
		Assert.assertEquals(park.getParkCode(), parkReturned.getParkCode());
	}
	
	@Test
	public void get_all_available_states() {
		String selectSql = "SELECT COUNT(DISTINCT state) from park";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(selectSql);
		rows.next();
		int originalRowCount = rows.getInt("count");
		
		Park park = makeNewPark();
		save(park);
		
		List<String> parks = parkDAO.getAllColumnValuesByColumn("state");
		
		Assert.assertEquals(originalRowCount + 1, parks.size());
	}
	
	@Test
	public void get_all_available_climates() {
		String selectSql = "SELECT COUNT(DISTINCT climate) from park";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(selectSql);
		rows.next();
		int originalRowCount = rows.getInt("count");
		
		Park park = makeNewPark();
		Park park2 = makeNewPark();
		park2.setParkCode("GHGH");
		save(park);
		save(park2);		
		
		List<String> parks = parkDAO.getAllColumnValuesByColumn("climate");
		
		Assert.assertEquals(originalRowCount + 1, parks.size());
	}
	
	@Test
	public void get_parks_by_search_all() {
		
		List<Park> parks1 = parkDAO.getParksBySearch("all", 4000, 4001, 0, true, "all");
		List<Park> parks2 = parkDAO.getParksBySearch("all", 4001, 5000, 0, true, "all");
		List<Park> parks3 = parkDAO.getParksBySearch("all", 3000, 3999, 0, true, "all");
		List<Park> parks4 = parkDAO.getParksBySearch("all", 0, 3999, 0, true, "all");
		Park park = makeNewPark();
		save(park);
		
		List<Park> newParks = parkDAO.getParksBySearch("all", 4000, 4001, 0, true, "all");
		Assert.assertEquals(parks1.size() + 1, newParks.size());
		
		newParks = parkDAO.getParksBySearch("all", 4001, 5000, 0, true, "all");
		Assert.assertEquals(parks2.size(), newParks.size());
		
		newParks = parkDAO.getParksBySearch("all", 3000, 3999, 0, true, "all");
		Assert.assertEquals(parks3.size(), newParks.size());
		
		newParks = parkDAO.getParksBySearch("all", 0, 3999, 0, true, "all");
		Assert.assertEquals(parks4.size(), newParks.size());
	}
	
	@Test
	public void get_parks_by_search_all_states() {
		
		List<Park> parks1 = parkDAO.getParksBySearch("all", 4000, 4001, 0, true, "Special");
		List<Park> parks2 = parkDAO.getParksBySearch("all", 4001, 5000, 0, true, "Special");
		List<Park> parks3 = parkDAO.getParksBySearch("all", 3000, 3999, 0, true, "Special");
		List<Park> parks4 = parkDAO.getParksBySearch("all", 0, 3999, 0, true, "Special");
		Park park = makeNewPark();
		save(park);
		
		List<Park> newParks = parkDAO.getParksBySearch("all", 4000, 4001, 0, true, "Special");
		Assert.assertEquals(parks1.size() + 1, newParks.size());
		
		newParks = parkDAO.getParksBySearch("all", 4001, 5000, 0, true, "Special");
		Assert.assertEquals(parks2.size(), newParks.size());
		
		newParks = parkDAO.getParksBySearch("all", 3000, 3999, 0, true, "Special");
		Assert.assertEquals(parks3.size(), newParks.size());
		
		newParks = parkDAO.getParksBySearch("all", 0, 3999, 0, true, "Special");
		Assert.assertEquals(parks4.size(), newParks.size());
	}
	
	@Test
	public void get_parks_by_search_all_climates() {
		
		List<Park> parks1 = parkDAO.getParksBySearch("Neverland", 4000, 4001, 0, true, "all");
		List<Park> parks2 = parkDAO.getParksBySearch("Neverland", 4001, 5000, 0, true, "all");
		List<Park> parks3 = parkDAO.getParksBySearch("Neverland", 3000, 3999, 0, true, "all");
		List<Park> parks4 = parkDAO.getParksBySearch("Neverland", 0, 3999, 0, true, "all");
		Park park = makeNewPark();
		save(park);
		
		List<Park> newParks = parkDAO.getParksBySearch("Neverland", 4000, 4001, 0, true, "all");
		Assert.assertEquals(parks1.size() + 1, newParks.size());
		
		newParks = parkDAO.getParksBySearch("Neverland", 4001, 5000, 0, true, "all");
		Assert.assertEquals(parks2.size(), newParks.size());
		
		newParks = parkDAO.getParksBySearch("Neverland", 3000, 3999, 0, true, "all");
		Assert.assertEquals(parks3.size(), newParks.size());
		
		newParks = parkDAO.getParksBySearch("Neverland", 0, 3999, 0, true, "all");
		Assert.assertEquals(parks4.size(), newParks.size());
	}
	
	@Test
	public void get_parks_by_search_specific() {
		
		List<Park> parks1 = parkDAO.getParksBySearch("Neverland", 4000, 4001, 0, true, "Special");
		List<Park> parks2 = parkDAO.getParksBySearch("Neverland", 4001, 5000, 0, true, "Special");
		List<Park> parks3 = parkDAO.getParksBySearch("Neverland", 3000, 3999, 0, true, "Special");
		List<Park> parks4 = parkDAO.getParksBySearch("Neverland", 0, 3999, 0, true, "Special");
		Park park = makeNewPark();
		save(park);
		
		List<Park> newParks = parkDAO.getParksBySearch("Neverland", 4000, 4001, 0, true, "Special");
		Assert.assertEquals(parks1.size() + 1, newParks.size());
		
		newParks = parkDAO.getParksBySearch("Neverland", 4001, 5000, 0, true, "Special");
		Assert.assertEquals(parks2.size(), newParks.size());
		
		newParks = parkDAO.getParksBySearch("Neverland", 3000, 3999, 0, true, "Special");
		Assert.assertEquals(parks3.size(), newParks.size());
		
		newParks = parkDAO.getParksBySearch("Neverland", 0, 3999, 0, true, "Special");
		Assert.assertEquals(parks4.size(), newParks.size());
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
