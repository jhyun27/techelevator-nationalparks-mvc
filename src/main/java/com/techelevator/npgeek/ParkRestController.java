package com.techelevator.npgeek;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.npgeek.dao.ParkDAO;
import com.techelevator.npgeek.dao.SurveyDAO;
import com.techelevator.npgeek.dao.WeatherDAO;
import com.techelevator.npgeek.exceptions.ParkNotFoundException;
import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.Survey;
import com.techelevator.npgeek.model.Weather;

@RestController
@RequestMapping("/api/park") // This is a common naming convention but it can be anything
@CrossOrigin //Cross Origin Resource Sharing Policies protect from rogue Javascript. Using @CrossOrigin opens you up to these vulnerabilities.
public class ParkRestController {
	
	@Autowired
	private ParkDAO parkDao;
	
	@Autowired
	private WeatherDAO weatherDao;
	
	@Autowired
	private SurveyDAO surveyDao;
	
	@GetMapping // Can use normal Request Mapping with APIs but easier to use global Request mapping with GetMapping/PostMapping etc in API controllers
	public List<Park> list() {
		return parkDao.getAllParks();
	}
	//Jackson binding. RestController returns object (or list of objects)... If there is something that Jackson binding can turn into a JSON object, it will
	//Any JavaBean can be turned into JSON objects (String representation) via Jackson
	
	// We want a 404 error if an invalid park code is sent in URL.. Create exception to do this
	@GetMapping("/{parkCode}") //Park code needs to be sent in URL. Create path parameter {parkCode}
	public Park get(@PathVariable String parkCode) { // can still user Request Param but for this one we want path variable
		Park park = parkDao.getParkByParkCode(parkCode);
		if (park == null) {
			// throws 404 error
			throw new ParkNotFoundException(parkCode, "Park not Found");
		}
		return park;
	}
	
	@GetMapping("/{parkCode}/weather/{scale}")
	public List<Weather> getWeather(@PathVariable String parkCode, @PathVariable String scale) {
		if (parkDao.getParkByParkCode(parkCode) == null) {
			throw new ParkNotFoundException(parkCode, "Park not Found");
		}
		
		if (scale.equalsIgnoreCase("c")) {
			return weatherDao.getCWeatherForecastByParkCode(parkCode);
		}
		
		return weatherDao.getFWeatherForecastByParkCode(parkCode);
	}
	
	//Use RequestBody which is JSON and match it up/bind it to object
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Survey save(@RequestBody Survey survey) {
		return surveyDao.submitSurvey(survey.getParkCode(), emailAddress, state, activityLevel);
	}
}
