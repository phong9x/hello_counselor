package org.trams.hello.rest.common.notification;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
public class FCMData implements Serializable  {
	private List<String> registration_ids;
	private Map<String,Object> data;
	private Map<String,Object> notification;
	private String priority;
	private String sound;
	
	
	public void addRegId(String regId){
		if(registration_ids == null)
			registration_ids = new LinkedList<String>();
		registration_ids.add(regId);
	}
	
	public void createData(String title, String content,String thumbnail, Integer id, Integer type, Integer isShow){
		data = new HashMap<String,Object>();
//		notification = new HashMap<String,Object>();
//		notification.put("title", title);
//		notification.put("body", content);
//		notification.put("id", id);	
//		notification.put("type", type);	
//		notification.put("isShow", isShow);	
//		notification.put("sound", "default");	
		HashMap<String, Object> body = new HashMap<>();
		body.put("title", title);
		body.put("content", content);
		body.put("thumbnail", thumbnail);
		body.put("id", id);	
		body.put("id", id);	
		body.put("type", type);	
		body.put("isShow", isShow);	
		data.put("body", body);
		data.put("volume", "3.21.15");
		//data.put("contents", "");
		//priority = "HIGH";
	}
	
	
	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}

	public Map<String, Object> getNotification() {
		return notification;
	}

	public void setNotification(Map<String, Object> notification) {
		this.notification = notification;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public List<String> getRegistration_ids() {
		return registration_ids;
	}

	public void setRegistration_ids(List<String> registration_ids) {
		this.registration_ids = registration_ids;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
