package com.techelevator.npgeek;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.Search;
import com.techelevator.npgeek.model.Survey;
import com.techelevator.npgeek.model.Weather;

@Controller
@SessionAttributes({"tempUnit"})
public class ParkController {
	
	@Autowired
	private NationalParkSystem npSystem;
	
	@RequestMapping(path="/", method=RequestMethod.GET)
	public String displayHomePage(ModelMap map) {
		
		List<Park> parks = npSystem.getAllParks();
		map.addAttribute("parks", parks);
		
		return "homePage";
	}

	@RequestMapping(path="/detail", method=RequestMethod.GET)
	public String displayDetailPage(@RequestParam String parkCode, @RequestParam(required=false) String tempUnit, ModelMap map) {
		Park park = npSystem.getParkByParkCode(parkCode);
		map.addAttribute("park", park);
		map.addAttribute("parkCode", parkCode);
		
		if (tempUnit != null) {
			map.addAttribute("tempUnit", tempUnit);
		} else {
			if (map.get("tempUnit") == null) {
				tempUnit = "F";
			} else {
			tempUnit = (String) map.get("tempUnit");
			}
		}
		
		List<Weather> weatherForecast = npSystem.getWeatherForecastByParkCode(parkCode, tempUnit);
		map.addAttribute("weatherForecast", weatherForecast);
		
		return "detailPage";
	}
	
	@RequestMapping(path="/survey", method=RequestMethod.GET) 
	public String displaySurvey(ModelMap map) {
		
		List<Park> parks = npSystem.getAllParks();
		map.addAttribute("parks", parks);

		if(!map.containsAttribute("survey")) {
			map.addAttribute("survey", new Survey());
		}
		
		return "survey";
	}
	
	@RequestMapping(path="/survey", method=RequestMethod.POST) 
	public String postSurvey(@Valid @ModelAttribute("survey") Survey survey, BindingResult result, ModelMap map) {
		List<Park> parks = npSystem.getAllParks();
		map.addAttribute("parks", parks);
		
		if (result.hasErrors()) {
			return "survey";
		}
		
		boolean hasSubmitted = npSystem.emailHasAlreadySubmittedSurvey(survey.getEmailAddress());
		if (!hasSubmitted) {
			
			npSystem.submitSurvey(survey);
			
			return "redirect:/favoriteParks";
		} else {
			return "redirect:/submitError";
		}
		
	}
	
	@RequestMapping(path="/favoriteParks", method=RequestMethod.GET) 
	public String displayFavoriteParks(ModelMap map) {
		List<Park> favoriteParks = npSystem.getFavoriteParks();
		
		map.addAttribute("favoriteParks", favoriteParks);
		
		return "favoriteParks";
	}
	
	@RequestMapping(path="/submitError", method=RequestMethod.GET) 
	public String displaySubmitErrorPage(ModelMap map) {
		return "alreadySubmitted";
	}
	
	@RequestMapping(path="/planTrip", method=RequestMethod.GET)
	public String displayPlanTripPage(@Valid @ModelAttribute("search") Search search, BindingResult result, ModelMap map) {
		
		List<String> states = npSystem.getAllParkStates();
		map.addAttribute("states", states);
		
		List<String> climates = npSystem.getAllParkClimates();
		map.addAttribute("climates", climates);
		
		if (!map.containsAttribute("searchResults")) {
			List<Park> searchResults = npSystem.getParksBySearch(search);
			map.addAttribute("searchResults", searchResults);
		}
		
		
		return "planYourTrip";
	}
}

