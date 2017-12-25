package org.trams.hello.web.controller.counselor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.trams.hello.bean.UserItem;
import org.trams.hello.web.common.Login;

@Controller
@RequestMapping("/auth")
public class InquiryController extends BaseController {
	
	@RequestMapping(value = "/inquiry/inquiry", method = RequestMethod.GET)
	public String index(HttpServletRequest request, HttpSession session, ModelMap map) {
		UserItem userItem = Login.getLoggedInUser(session);

		
		map.put("counselorId", userItem.getId());
		map.put("counselorEmail", userItem.getEmail());
		return getView();
	}
	
	
}
