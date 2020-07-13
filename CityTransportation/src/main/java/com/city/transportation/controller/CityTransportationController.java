package com.city.transportation.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityTransportationController {
	
		@Autowired
		Environment env;

		@GetMapping(value = "/connected", produces = MediaType.TEXT_PLAIN_VALUE)
		public ResponseEntity<String> validateCitiesConnections(@RequestParam(name = "origin") String origin,
				@RequestParam(name = "destination") String destination) throws Exception {

			String result = "no";
//"Newark", "Boston" or "Boston" "Newark",
			if (origin != null && destination != null) {
                String city = connectedCitiesCombination(origin,null);
				if (!destination.equalsIgnoreCase(city)){
				
					city = connectedCitiesCombination(null,destination);
					if(city!="" && origin.equalsIgnoreCase(city)) {
						result="yes";
					}else if(city!="" && destination.equalsIgnoreCase(city)) {
						result = "yes";
					}
				}else if(city!="" && destination.equalsIgnoreCase(city)) {
						result = "yes";
					}
				}
			
			return new ResponseEntity<>(result, HttpStatus.OK);
		}

		private String connectedCitiesCombination(String origin,String destination) {

			Map<String, String> connectedCities = new HashMap<>();
			connectedCities.put("Boston", "New York");
			connectedCities.put("Philadelphia", "Newark");
			connectedCities.put("Newark", "Boston");
			connectedCities.put("Trenton", "Albany");
			String cityName="";
			
			if (destination==null && connectedCities.containsKey(origin)) {
				cityName = connectedCities.get(origin);
			}else if(origin==null ) {
				// get origin by destination  
				/*
				 * connectedCities.forEach((key, value) -> { if (value.equals(destination)) {
				 * cityName = key; } });
				 */
				
				for(String key: connectedCities.keySet()) {
				    if(key.equalsIgnoreCase(destination)) {
				    	cityName = key; 
				     }
				}
			}

			return cityName;

		}

	}


