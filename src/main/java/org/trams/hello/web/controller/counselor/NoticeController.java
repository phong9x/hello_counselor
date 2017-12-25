package org.trams.hello.web.controller.counselor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.trams.hello.bean.Notice;
import org.trams.hello.bean.UserItem;
import org.trams.hello.bean.jpa.NoticeEntity;
import org.trams.hello.business.service.NoticeService;
import org.trams.hello.business.service.UserNoticeService;
import org.trams.hello.web.common.Login;

@Controller
@RequestMapping("/auth")
public class NoticeController extends BaseController {
	@Autowired
    private NoticeService noticeService;
	@Autowired
    private UserNoticeService userNoticeService;
	
	@RequestMapping(value = "/notice/list", method = RequestMethod.GET)
	public String index(@RequestParam(value = "page", defaultValue = "1") Integer page, 
			@RequestParam(value = "orderBy", defaultValue = "create_date") String orderBy,
			HttpServletRequest request,
			HttpSession session, ModelMap map) {
		UserItem userItem = Login.getLoggedInUser(session);
		
		Page<NoticeEntity> pageList = noticeService.listPagingByCounselorIdInWebC(page, 30, orderBy);
		
        map.put("orderBy", orderBy);
        map.put("list", pageList.getContent());
        map.put("size", 30);
		map.put("totalElement", pageList.getTotalElements());
		map.put("beginIndex", 1);
		map.put("endIndex", pageList.getTotalPages());
		map.put("totalCount",pageList.getTotalElements());
		map.put("totalPages",pageList.getTotalPages());
		map.put("totalPages",pageList.getTotalPages());
		map.put("counselorId", userItem.getId());
		String param_url= request.getQueryString();
		if(param_url != null && param_url.contains("page=")){
			param_url=param_url.replaceAll("[&?]page.*?(?=&|\\?|$)", "");
		}
		map.put("param_url", param_url);
		map.put("pagination_navigator", "/auth/notice/list");
		
		
		return getView();
	}
	
	@RequestMapping(value = "/notice/detail", method = RequestMethod.GET)
	public String getDetail(@RequestParam(value = "id") Integer id,
			HttpServletRequest request,
			HttpSession session, ModelMap map) {
		UserItem userItem = Login.getLoggedInUser(session);
		Notice notice = noticeService.findById(id);
		notice.setViewNumber(notice.getViewNumber() + 1);
		notice = noticeService.update(notice);
		map.put("notice", notice);
		map.put("counselorId", userItem.getId());
		return getView();
	}
	
}
