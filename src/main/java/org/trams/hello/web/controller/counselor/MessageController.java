package org.trams.hello.web.controller.counselor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.trams.hello.bean.PageCustom;
import org.trams.hello.bean.UserItem;
import org.trams.hello.bean.jpa.MessageEntity;
import org.trams.hello.bean.jpa.UserReceiveMessageEntity;
import org.trams.hello.bean.web.counselor.InboxMessages;
import org.trams.hello.bean.web.counselor.SendMessages;
import org.trams.hello.bean.web.counselor.UserCounselingSession;
import org.trams.hello.business.service.CounselingSessionService;
import org.trams.hello.business.service.MessageService;
import org.trams.hello.business.service.UserReceiveMessageService;
import org.trams.hello.web.common.Login;

/**
 * Created by bryanlee on 08/03/2017.
 */
@Controller
@RequestMapping("/auth")
public class MessageController extends BaseController {

    @Autowired
    private MessageService messageService;
    
    @Autowired
    private UserReceiveMessageService userReceiveMessageService;

    @Autowired
    private CounselingSessionService counselingSessionService;
    
    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public String list() {
        return getView();
    }

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    @ResponseBody
    public Object create(
            @RequestParam Map<String, Object> params,
            HttpSession session)
    {
        UserItem loggedInUser   = Login.getLoggedInUser(session);
        Integer counselorId     = loggedInUser.getId();

        params.put("fromId", counselorId);
        params.put("type", 0);

        messageService.send(params);
        return SUCCESS();
    }
    
    @RequestMapping(value = "/message/list", method = RequestMethod.GET)
    public String listMess(@RequestParam(value = "page", defaultValue = "1") Integer page, 
			@RequestParam(value = "start_mess_tab1", defaultValue = "") String start_mess_tab1,
			@RequestParam(value = "end_mess_tab1", defaultValue = "") String end_mess_tab1,
			@RequestParam(value = "start_mess_tab2", defaultValue = "") String start_mess_tab2,
			@RequestParam(value = "end_mess_tab2", defaultValue = "") String end_mess_tab2,
			@RequestParam(value = "keyword_message1", defaultValue = "") String keyword_message1,
			@RequestParam(value = "keyword_message2", defaultValue = "") String keyword_message2,
			@RequestParam(value = "orderBy", defaultValue = "create_date_mess") String orderBy,
			@RequestParam(value = "number_diff", defaultValue = "1") Integer number_diff,
			HttpServletRequest request,
			HttpSession session, ModelMap map) throws ParseException
    {
    	UserItem userItem = Login.getLoggedInUser(session);
    	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    	Date startSearch = null;
    	Date endSearch = null;
    	String keyword = "";
    	Integer userId = userItem.getId();
    	if (number_diff == 1) {
    		
    		if(start_mess_tab1 != null) {
        		startSearch = sf.parse(start_mess_tab1); 
        		endSearch = sf.parse(end_mess_tab1); 
        		keyword = keyword_message1;
        	} else {
        		if (userReceiveMessageService.countInboxMessByUserId(userId) != 0) {
        			startSearch = userReceiveMessageService.findFirstDateInboxMessByUserid(userId);
				} else {
					startSearch = new Date();
				}
        		endSearch = new Date();
        		start_mess_tab1 = sf.format(startSearch);
        		end_mess_tab1 = sf.format(endSearch);
			}
    		PageCustom<InboxMessages> pageMs = messageService.filterInboxMessagesOfCounselor(sf.format(startSearch), sf.format(endSearch), keyword, userItem.getId(), page, 30, orderBy);
        	map.put("totalElement", pageMs.getTotalCount());
        	map.put("endIndex", pageMs.getTotalPages());
    		map.put("list", pageMs.getList());
    		map.put("currentIndex", pageMs.getCurrent());
    		map.put("totalCount",pageMs.getTotalCount());
    		map.put("size",pageMs.getSize());
    		map.put("totalPages",pageMs.getTotalPages());
    	} else {
    		
			if (start_mess_tab2 != null) {
	    		startSearch = sf.parse(start_mess_tab2); 
	    		endSearch = sf.parse(end_mess_tab2); 
	    		keyword = keyword_message2;
			} else {
				if (userReceiveMessageService.countSendMessByUserId(userId) != 0) {
					startSearch = userReceiveMessageService.findFirstDateSendMessByUserid(userId);
				} else {
					startSearch = new Date();
				}
	    		
	    		endSearch = new Date();
	    		start_mess_tab2 = sf.format(startSearch);
        		end_mess_tab2 = sf.format(endSearch);
	    	}
			
			PageCustom<SendMessages> pageSendMs = messageService.filterSendMessagesOfCounselor(sf.format(startSearch), sf.format(endSearch), keyword, userItem.getId(), page, 30, orderBy);
        	map.put("totalElement", pageSendMs.getTotalCount());
        	map.put("endIndex", pageSendMs.getTotalPages());
    		map.put("list", pageSendMs.getList());
    		map.put("currentIndex", pageSendMs.getCurrent());
    		map.put("totalCount",pageSendMs.getTotalCount());
    		map.put("size",pageSendMs.getSize());
    		map.put("totalPages",pageSendMs.getTotalPages());
		}
    	
		List<UserCounselingSession> userList = counselingSessionService.listCounselingSessionByCounselorId(userItem.getId());
    	
		map.put("pagination_navigator", "/auth/message/list");
		map.put("beginIndex", 1);
		map.put("counselorId",userItem.getId());
    	map.put("startSearch", sf.format(startSearch));
    	map.put("endSearch", sf.format(endSearch));
    	map.put("keyword_message1", keyword_message1);
    	map.put("keyword_message2", keyword_message2);
    	map.put("start_mess_tab1", start_mess_tab1);
    	map.put("start_mess_tab2", start_mess_tab2);
    	map.put("end_mess_tab2", end_mess_tab2);
    	map.put("end_mess_tab1", end_mess_tab1);
    	map.put("orderBy", orderBy);
    	map.put("number_diff", number_diff);
    	String param_url= request.getQueryString();
    	map.put("userList", userList);
		map.put("param_url", param_url);
    	return getView();
    }

}
