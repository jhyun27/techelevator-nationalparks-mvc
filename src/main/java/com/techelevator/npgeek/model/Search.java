package com.techelevator.npgeek.model;

import javax.validation.constraints.AssertTrue;

public class Search {
	
	private String state;
	private String minElevationFeetString;
	private Integer minElevationFeet;
	private String maxElevationFeetString;
	private Integer maxElevationFeet;
	private Integer minMilesOfTrail;
	private boolean needsCampsites;
	private String climate;
	
	private boolean elevationString;
	@AssertTrue(message="Please enter valid numbers.")
	public boolean isElevationString() {
		try {
			
			if (minElevationFeetString == null || minElevationFeetString.equals("")) {
				minElevationFeet = 0;
			} else {
				minElevationFeet = Integer.parseInt(minElevationFeetString);
			}
		} catch (NumberFormatException e){
			return false;
		}
		
		try {
			if (maxElevationFeetString == null  || maxElevationFeetString.equals("")) {
				maxElevationFeet = Integer.MAX_VALUE;
			} else {
				maxElevationFeet = Integer.parseInt(maxElevationFeetString);
			}
		} catch (NumberFormatException e) {
			return false;
		}
		
		if (minElevationFeet >= 0 && maxElevationFeet >= 0 && maxElevationFeet >= minElevationFeet) {
			return true;
		}
		return false;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMinElevationFeetString() {
		return minElevationFeetString;
	}

	public void setMinElevationFeetString(String minElevationFeetString) {
		this.minElevationFeetString = minElevationFeetString;
	}

	public String getMaxElevationFeetString() {
		return maxElevationFeetString;
	}

	public void setMaxElevationFeetString(String maxElevationFeetString) {
		this.maxElevationFeetString = maxElevationFeetString;
	}

	public Integer getMinElevationFeet() {
		return minElevationFeet;
	}
	public void setMinElevationFeet(int minElevation) {
		this.minElevationFeet = minElevation;
	}
	public Integer getMaxElevationFeet() {
		return maxElevationFeet;
	}
	public void setMaxElevationFeet(int maxElevation) {
		this.maxElevationFeet = maxElevation;
	}
	public Integer getMinMilesOfTrail() {
		return minMilesOfTrail;
	}
	public void setMinMilesOfTrail(Integer minMilesOfTrail) {
		this.minMilesOfTrail = minMilesOfTrail;
	}
	public boolean getNeedsCampsites() {
		return needsCampsites;
	}
	public void setNeedsCampsites(boolean needsCampsites) {
		this.needsCampsites = needsCampsites;
	}
	public String getClimate() {
		return climate;
	}
	public void setClimate(String climate) {
		this.climate = climate;
	}

	public void setElevationString(boolean elevationString) {
		this.elevationString = elevationString;
	}

}
