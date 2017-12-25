package org.trams.hello.rest.common.notification;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trams.hello.bean.User;
import org.trams.hello.bean.jpa.CounselorEntity;
import org.trams.hello.business.service.CounselorService;
import org.trams.hello.business.service.UserService;
import org.trams.hello.web.common.ApplicationDefine;
import org.trams.hello.web.common.utils.JsonUtils;

@Component
public class Notification {
	
	private static String API_KEY = "AIzaSyDeDR71AP-kV4zVx2eJaMl400Ax6RXhLpE";

	@Autowired
	private UserService userService;
	@Autowired
	private CounselorService counselorService;

	public static String sendNotification(List<String> regId, String title, String content, String thumbnail, Integer id, Integer type, Integer isShow){
		try {
			FCMData f = new FCMData();
			f.createData(title, content, thumbnail, id, type, isShow);
			f.setRegistration_ids(regId);
			PushFCM.post(API_KEY, f);
			System.out.println(JsonUtils.toString(f));
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return "";
	}

	public void sendPsyNotification(List<Integer> userIds, Integer counselorId, Integer totalPsy) {

		CounselorEntity counselor = counselorService.findByOne(counselorId);

		String title 	= "심리상담 결제 안내";
		String body 	= counselor.getUser().getFullname() + " Counselor가 " + totalPsy + "건의 심리상담을 요청하였습니다.";

		for (Integer userId : userIds) {
			User user 					= userService.findById(userId);
			sendNotification(Collections.singletonList(user.getFcmToken()), title, body, counselor.getThumbnailUrl(), null, ApplicationDefine.Notification_Type.PsychologicalTest.getCode(), 1);
		}
	}
}
