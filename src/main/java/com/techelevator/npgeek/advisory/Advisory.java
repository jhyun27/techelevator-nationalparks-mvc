package com.techelevator.npgeek.advisory;

import java.util.ArrayList;
import java.util.List;

public class Advisory {
	
	public static List<String> getMessages(String forecast, int high, int low) {
		List<String> messages = new ArrayList<String>();
		messages.addAll(getForecastMessage(forecast));
		messages.addAll(getTempMessage(high, low));
		
		return messages;
	}
	
	private static List<String> getForecastMessage(String forecast) {
		List<String> messages = new ArrayList<String>();
		switch(forecast) {
			case "snow":
				messages.add("Pack snowshoes.");
				break;
			case "rain": 
				messages.add("Pack raingear and wear waterproof shoes.");
				break;
			case "thunderstorms":
				messages.add("Seek shelter and avoid hiking on exposed ridges.");
				break;
			case "sunny":
				messages.add("Pack sunblock.");
				break;
		}
		
		return messages;
	}
	
	private static List<String> getTempMessage(int high, int low) {
		List<String> messages = new ArrayList<String>();
		if (high > 75) {
			messages.add("Bring an extra gallon of water.");
		}
		if (low < 20) {
			messages.add("Beware frigid temperatures.");
		}
		if (high - low > 20) {
			messages.add("Wear breathable layers.");
		}
		
		return messages;
	}
	

}
