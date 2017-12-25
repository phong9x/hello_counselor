package org.trams.hello.web.controller.counselor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.trams.hello.bean.UserItem;
import org.trams.hello.bean.jpa.FaqEntity;
import org.trams.hello.business.service.FaqService;
import org.trams.hello.web.common.Login;

@Controller
@RequestMapping("/auth")
public class FAQController extends BaseController {
	@Autowired
    private FaqService faqService;
	
	@RequestMapping(value = "/faq/list", method = RequestMethod.GET)
	public String index(@RequestParam(value = "page", defaultValue = "1") Integer page, 
			@RequestParam(value = "keyword", defaultValue = "") String keyword,
			HttpServletRequest request,
			HttpSession session, ModelMap map) {
		UserItem userItem = Login.getLoggedInUser(session);
		if(keyword == null){
			keyword = "";
		}
		List<FaqEntity> pageList = faqService.findByTitleOrContent(keyword);
		
		map.put("keyword", keyword);
        map.put("list", pageList);
        map.put("counselorId", userItem.getId());
		return getView();
	}
	
}
