package org.trams.hello.web.controller.counselor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.trams.hello.bean.Policy;
import org.trams.hello.bean.UserItem;
import org.trams.hello.business.service.PolicyService;
import org.trams.hello.web.common.Login;

@Controller
@RequestMapping("/auth")
public class PolicyController extends BaseController {
	@Autowired
    private PolicyService policyService;
	
	@RequestMapping(value = "/policy/policy", method = RequestMethod.GET)
	public String index(HttpServletRequest request, HttpSession session, ModelMap map) {
		UserItem userItem = Login.getLoggedInUser(session);
		Policy termsOfService = policyService.getPoli((short)1, (short)2);
		Policy privacyPolicy = policyService.getPoli((short) 2, (short)2);
		
    	map.put("termsOfService", termsOfService);
    	map.put("privacyPolicy", privacyPolicy);
    	map.put("counselorId", userItem.getId());
		return getView();
	}
	
	
}
