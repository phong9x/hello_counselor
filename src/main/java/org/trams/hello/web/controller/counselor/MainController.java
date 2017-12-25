package org.trams.hello.web.controller.counselor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.trams.hello.bean.Faq;
import org.trams.hello.bean.ImageWebsite;
import org.trams.hello.business.service.FaqService;
import org.trams.hello.business.service.ImageWebsiteService;

@Controller
@RequestMapping("/")
public class MainController extends BaseController {

	@Autowired
	private ImageWebsiteService imageWebsiteService;
	@Autowired
	private FaqService faqService;

	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String loginGet(ModelMap map) {
		List<ImageWebsite> list = imageWebsiteService.list((short) 1);
		List<Faq> faqs = faqService.listInCounselorWeb();
		map.put("list", list);
		map.put("faqs", faqs);
		return "/public/home";
	}
}
