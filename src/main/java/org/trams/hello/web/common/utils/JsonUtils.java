package org.trams.hello.web.common.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by minh on 4/6/16.
 */
public class JsonUtils {

    public static final ObjectMapper mapper = new ObjectMapper();

    public static String toString(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static <T> T from(String json, Class<T> klass) {
        try {
            return mapper.readValue(json, klass);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    public static List<String> ConvertJsonTo_ListString(String json,String atributeName) {
		JSONParser parser = new JSONParser();
		try {
			JSONObject jsonObj = (JSONObject) new JSONParser().parse(json);
			JSONArray msg = (JSONArray) jsonObj.get(atributeName);
			Iterator<String> iterator = msg.iterator();
			List<String> str=new ArrayList<>();
			while (iterator.hasNext()) {
				str.add(iterator.next());
			}
			return str;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
    
    public static List<Integer> ConvertJsonTo_ListInteger(String json,String atributeName) {
		JSONParser parser = new JSONParser();
		try {
			JSONObject jsonObj = (JSONObject) new JSONParser().parse(json);
			JSONArray msg = (JSONArray) jsonObj.get(atributeName);
			Iterator<Long> iterator = msg.iterator();
			List<Integer> str=new ArrayList<>();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
				str.add((int)(long)iterator.next());
			}
			return str;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

}