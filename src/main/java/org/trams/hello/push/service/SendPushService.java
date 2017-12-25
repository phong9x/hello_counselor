package org.trams.hello.push.service;

public interface SendPushService {
	public String sendTargetOneAndroid(Integer userId, String identity, String title, String message, Integer pageId, Short type, Integer counselorId , Integer notificationId);
}
