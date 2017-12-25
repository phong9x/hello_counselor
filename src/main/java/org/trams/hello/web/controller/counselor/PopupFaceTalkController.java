package org.trams.hello.web.controller.counselor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.trams.hello.bean.PsychologicalTestSetting;
import org.trams.hello.bean.jpa.CounselingSessionEntity;
import org.trams.hello.business.service.CounselingMemoService;
import org.trams.hello.business.service.CounselingSessionService;
import org.trams.hello.business.service.CounselorService;
import org.trams.hello.business.service.NotificationService;
import org.trams.hello.business.service.PsychologicalTestSettingService;
import org.trams.hello.business.service.RequestUserPsychologicalService;
import org.trams.hello.business.service.RtcService;
import org.trams.hello.business.service.TestResultService;
import org.trams.hello.business.service.UserPsychologicalService;
import org.trams.hello.business.service.UserQuestionaireService;
import org.trams.hello.business.service.UserService;
import org.trams.hello.rest.common.notification.Notification;

/**
 * Created by bryanlee on 1/23/17.
 */
@Controller
@RequestMapping("/auth/talk")
public class PopupFaceTalkController extends BaseController {

    @Autowired
    private CounselingSessionService counselingSessionService;
    @Autowired
    private PsychologicalTestSettingService psychologicalTestSettingService;
    @RequestMapping(value = "/user_info", method = RequestMethod.GET)
	public String getDetail(
			HttpServletRequest request,@RequestParam String roomId,
			HttpSession session, ModelMap map) {
    	 CounselingSessionEntity cse = counselingSessionService.findOneByRoomId(roomId);
         DateFormat formatter = new SimpleDateFormat("yyyy");
         int age = Integer.parseInt(formatter.format(new Date())) - Integer.parseInt(formatter.format(cse.getUser().getDayOfBirth()));
         
         map.put("user"          , cse.getUser());
         map.put("age"           , age);
         map.put("total"         , counselingSessionService.totalCounselingSessionByUserAndCounselor(cse.getUser().getId()));
         map.put("startTime"     , cse.getStartTime().getTime());
         map.put("sessionTime"   , cse.getCounselingTimeType());
         map.put("session"       , cse);
		return getView();
	}
    
    @RequestMapping(value = "/memoPopup", method = RequestMethod.GET)
	public String memoPopup(
			HttpServletRequest request,
			@RequestParam String roomId,
			@RequestParam String fullname,
			HttpSession session, ModelMap map) {
    	map.put("fullname"       , fullname);
		return getView();
	}
    
    @RequestMapping(value = "/psychological", method = RequestMethod.GET)
	public String psychological(
			HttpServletRequest request,
			@RequestParam String roomId,
			@RequestParam Map<String, Object> params,
			HttpSession session, ModelMap map) {
    	params.put("page", 1);
        params.put("size", Integer.MAX_VALUE);
    	Page<CounselingSessionEntity> sessionEntities = counselingSessionService.filter(params);
        CounselingSessionEntity cse = sessionEntities.getContent().get(0);
    	List<PsychologicalTestSetting> psy = psychologicalTestSettingService.findAll();
    	map.put("psy"           , psy);
    	map.put("user"          , cse.getUser());
    	map.put("counselor"          , cse.getCounselor());
		return getView();
	}
    
    @RequestMapping(value = "/account_test", method = RequestMethod.GET)
    public String account_test(
			HttpServletRequest request,
			@RequestParam String roomId,
			@RequestParam Map<String, Object> params,
			HttpSession session, ModelMap map) {
    	params.put("page", 1);
        params.put("size", Integer.MAX_VALUE);
    	Page<CounselingSessionEntity> sessionEntities = counselingSessionService.filter(params);
        CounselingSessionEntity cse = sessionEntities.getContent().get(0);
    	map.put("user"     , cse.getCounselor().getUser());
		return getView();
	}
    
    @RequestMapping(value = "/self_diagnosis", method = RequestMethod.GET)
   	public String self_diagnosis(
   			HttpServletRequest request,@RequestParam String roomId,
   			HttpSession session, ModelMap map) {
       	/* Map<String, Object> params = new HashMap<String, Object>();
       	 params.put("page", 1);
            params.put("size", Integer.MAX_VALUE);
            Page<CounselingSessionEntity> sessionEntities = counselingSessionService.filter(params);
            CounselingSessionEntity cse = sessionEntities.getContent().get(0);

            DateFormat formatter = new SimpleDateFormat("yyyy");
            int age = Integer.parseInt(formatter.format(new Date())) - Integer.parseInt(formatter.format(cse.getUser().getDayOfBirth()));

            List<PsychologicalTestSetting> psy = psychologicalTestSettingService.findAll();

            map.put("user"          , cse.getUser());
            map.put("counselor"     , cse.getCounselor());
            map.put("age"           , age);
            map.put("total"         , sessionEntities.getTotalElements());
            map.put("startTime"     , cse.getStartTime().getTime());
            map.put("sessionTime"   , cse.getCounselingTimeType());
            map.put("session"       , cse);
            map.put("psy"           , psy);*/
   		return getView();
   	}
    
    @RequestMapping(value = "/mindcheck_result", method = RequestMethod.GET)
   	public String mindcheck_result(
   			HttpServletRequest request, @RequestParam String roomId,
   			HttpSession session, ModelMap map) {
    		/*Map<String, Object> params = new HashMap<String, Object>();
       	 	params.put("page", 1);
            params.put("size", Integer.MAX_VALUE);
            Page<CounselingSessionEntity> sessionEntities = counselingSessionService.filter(params);
            CounselingSessionEntity cse = sessionEntities.getContent().get(0);

            DateFormat formatter = new SimpleDateFormat("yyyy");
            int age = Integer.parseInt(formatter.format(new Date())) - Integer.parseInt(formatter.format(cse.getUser().getDayOfBirth()));

            List<PsychologicalTestSetting> psy = psychologicalTestSettingService.findAll();

            map.put("user"          , cse.getUser());
            map.put("counselor"     , cse.getCounselor());
            map.put("age"           , age);
            map.put("total"         , sessionEntities.getTotalElements());
            map.put("startTime"     , cse.getStartTime().getTime());
            map.put("sessionTime"   , cse.getCounselingTimeType());
            map.put("session"       , cse);
            map.put("psy"           , psy);*/
   		return getView();
   	}

}
