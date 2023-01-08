package com.techelevator.npgeek.dao;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.techelevator.npgeek.advisory.Advisory;
import com.techelevator.npgeek.model.Weather;

@Component
public class JDBCWeatherDAO implements WeatherDAO {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired 
	public JDBCWeatherDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Weather> getFWeatherForecastByParkCode(String parkCode) {
		String selectSQL = "SELECT parkcode, fivedayforecastvalue, low, high, forecast FROM weather WHERE parkcode = ? ORDER BY fivedayforecastvalue";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(selectSQL, parkCode);
		
		List<Weather> weatherForecast = new ArrayList<Weather>();
		while (rows.next()) {
			Weather weather = mapRowToWeather(rows, true);
			weather.setParkCode(parkCode);
			weatherForecast.add(weather);
		}
		
		return weatherForecast;
	}

	@Override
	public List<Weather> getCWeatherForecastByParkCode(String parkCode) {
		String selectSQL = "SELECT parkcode, fivedayforecastvalue, ((low - 32) * 5 / 9) AS low, ((high - 32) * 5 / 9) AS high, forecast FROM weather WHERE parkcode = ? ORDER BY fivedayforecastvalue";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(selectSQL, parkCode);
		
		List<Weather> weatherForecast = new ArrayList<Weather>();
		while (rows.next()) {
			Weather weather = mapRowToWeather(rows, false);
			weather.setParkCode(parkCode);
			weatherForecast.add(weather);
		}
		
		return weatherForecast;
	}

	private Weather mapRowToWeather(SqlRowSet row, boolean isF) {
		Weather weather = new Weather();
		
		weather.setDay(row.getInt("fivedayforecastvalue"));
		int low = row.getInt("low");
		int high = row.getInt("high");
		weather.setLow(low);
		weather.setHigh(high);
		String forecast = forecastToCamelCase(row.getString("forecast"));
		weather.setForecast(forecast);
		
		if (isF == false) {
			low = (int) (low * 9d / 5) + 32;
			high = (int) (high * 9d / 5) + 32;
		}
		weather.setAdvisoryMessages(Advisory.getMessages(forecast, high, low));
		
		return weather;
	}
	
	private String forecastToCamelCase(String forecast) {
		String camelForecast = "";
		if (forecast.equals("partly cloudy")) {
			camelForecast = "partlyCloudy";
		} else {
			camelForecast = forecast;
		}
		return camelForecast;
	}
}
