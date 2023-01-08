package com.techelevator.npgeek.dao;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.model.Park;

@Component
public class JDBCParkDAO implements ParkDAO {

	private JdbcTemplate jdbcTemplate;
	private static final String SELECT_PARK_SQL = "SELECT " +
			"parkcode, " +
			"parkname, " +
			"state, " +
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
			"FROM park ";
	
	@Autowired
	public JDBCParkDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Park> getAllParks() {
		
		String selectSQL = SELECT_PARK_SQL + "ORDER BY parkname";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(selectSQL);
		List<Park> parks = new ArrayList<Park>();
		while (rows.next()) {
			Park park = mapRowToPark(rows);
			parks.add(park);
		}
		
		return parks;
	}

	@Override
	public Park getParkByParkCode(String parkCode) {
		
		String selectSQL = SELECT_PARK_SQL + "WHERE parkcode = ?";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(selectSQL, parkCode);
		rows.next();
		Park park = mapRowToPark(rows);
		
		return park;
	}
	
	@Override
	public List<String> getAllColumnValuesByColumn(String columnName) {
	  String selectSQL = "SELECT " + columnName +
	          " FROM park " + 
	          " GROUP BY " + columnName +
	          " ORDER BY " + columnName;
	  SqlRowSet rows = jdbcTemplate.queryForRowSet(selectSQL);
	  List<String> values = new ArrayList<String>();
	  while (rows.next()) {
	      values.add(rows.getString(columnName));
	  }
	  return values;
	}
	
	public List<Park> getParksBySearch(String state, Integer minElevation, Integer maxElevation, Integer minMilesOfTrail, boolean needsCampsites, String climate){
		String selectSQL = SELECT_PARK_SQL + 
				"WHERE elevationinfeet BETWEEN ? AND ? " + 
				"AND milesoftrail > ?";
		
		if (needsCampsites) {
			selectSQL += " AND numberofcampsites > 0";
		}
				
		SqlRowSet rows;
		if (climate.equals("all") && state.equals("all")) {
			selectSQL += " AND climate IN (SELECT climate FROM park)" +
					" AND state IN (SELECT state FROM park)";
			rows = jdbcTemplate.queryForRowSet(selectSQL, minElevation, maxElevation, minMilesOfTrail);
		}
		else if (climate.equals("all")) {
			selectSQL += " AND climate IN (SELECT climate FROM park)" +
					" AND state = ?";
			rows = jdbcTemplate.queryForRowSet(selectSQL, minElevation, maxElevation, minMilesOfTrail, state);		
		}
		else if (state.equals("all")) {
			selectSQL += " AND climate = ?" +
					" AND state IN (SELECT state FROM park)";
			rows = jdbcTemplate.queryForRowSet(selectSQL, minElevation, maxElevation, minMilesOfTrail, climate); 
		}
		else {
			selectSQL += " AND climate = ?" +
					" AND state = ?";
			rows = jdbcTemplate.queryForRowSet(selectSQL, minElevation, maxElevation, minMilesOfTrail, climate, state);
		}
	
		List<Park> parks = new ArrayList<Park>();
		
		while (rows.next() ) {
			parks.add(mapRowToPark(rows));
		}
		
		return parks;
	}
	
	protected static Park mapRowToPark(SqlRowSet row) {
		Park park = new Park();
		park.setParkCode(row.getString("parkcode"));
		park.setParkName(row.getString("parkname"));
		park.setState(row.getString("state"));
		park.setAcreage(row.getInt("acreage"));
		park.setElevationFt(row.getInt("elevationinfeet"));
		park.setMilesOfTrail(row.getDouble("milesoftrail"));
		park.setNumberOfCampsites(row.getInt("numberofcampsites"));
		park.setClimate(row.getString("climate"));
		park.setYearFounded(row.getInt("yearfounded"));
		park.setAnnualVisitorCount(row.getInt("annualvisitorcount"));
		park.setInspirationalQuote(row.getString("inspirationalquote"));
		park.setInspirationalQuoteSource(row.getString("inspirationalquotesource"));
		park.setParkDescription(row.getString("parkdescription"));
		park.setEntryFee(row.getInt("entryfee"));
		park.setNumberOfAnimalSpecies(row.getInt("numberofanimalspecies"));
		
		return park;
	}
}
