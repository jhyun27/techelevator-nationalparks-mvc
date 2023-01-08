package com.techelevator.npgeek.dao;

import java.util.List;
import com.techelevator.npgeek.model.Park;

public interface ParkDAO {
	
	List<Park> getAllParks();
	Park getParkByParkCode(String parkCode);
	List<Park> getParksBySearch(String state, Integer minElevationFeet, Integer maxElevationFeet,
			Integer minMilesOfTrail, boolean needsCampsites, String climate);
	List<String> getAllColumnValuesByColumn(String columnName);
}
