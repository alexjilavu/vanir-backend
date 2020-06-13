package com.jimaio.vanir.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.apache.http.client.utils.URIBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

@Component("avatarUtils")
public class AvatarUtils {

	private static Map<String, List<String>> avatarProperties = new HashMap<>();
	
	public AvatarUtils() {
		parseJson();
	}
	
	private void parseJson() {
		JSONParser parser = new JSONParser();
		ClassLoader classLoader = AvatarUtils.class.getClassLoader();
		InputStream stream = classLoader.getResourceAsStream("avatarProps.json");
		
		try {
			String result = new BufferedReader(new InputStreamReader(stream))
					  .lines().collect(Collectors.joining("\n")); 
			
			Object obj = parser.parse(result);
		
			JSONObject jsonObject = (JSONObject) obj;
		
			for (Object key : jsonObject.keySet()) {
				JSONObject category = (JSONObject) jsonObject.get(key);
				
				JSONArray propertiesList = (JSONArray) category.get("properties");
				var iterator = propertiesList.iterator();
				
				List<String> props = new ArrayList<>();
				while (iterator.hasNext()) {
					props.add(iterator.next().toString());
				}
				
				avatarProperties.put(key.toString(), props);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Eroare la deschiderea fisierului json cu props");
		}
	}
	
	public String generateAvatarUrl() {
		
		try {
			URIBuilder builder = new URIBuilder("https://avataaars.io/");
			
			for (String key : avatarProperties.keySet()) {
				List<String> propsList = avatarProperties.get(key);
				int randomProp = ThreadLocalRandom.current().nextInt(0, propsList.size());
				builder.addParameter(key, propsList.get(randomProp));
			}
			
			return builder.toString();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
