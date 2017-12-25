package org.trams.hello.web.controller.counselor;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.trams.hello.bean.Counselor;
import org.trams.hello.bean.PageCustom;
import org.trams.hello.bean.PsychologicalTestSetting;
import org.trams.hello.bean.User;
import org.trams.hello.bean.UserItem;
import org.trams.hello.bean.api.UserPsychologicalFileApi;
import org.trams.hello.bean.jpa.RequestUserPsychologicalEntity;
import org.trams.hello.bean.web.counselor.PsychologicalTestCounselor;
import org.trams.hello.bean.web.counselor.UserCounselingSession;
import org.trams.hello.business.service.CounselingSessionService;
import org.trams.hello.business.service.CounselorService;
import org.trams.hello.business.service.PsychologicalTestSettingService;
import org.trams.hello.business.service.RequestUserPsychologicalService;
import org.trams.hello.business.service.UserPsychologicalFileService;
import org.trams.hello.business.service.UserPsychologicalService;
import org.trams.hello.business.service.UserService;
import org.trams.hello.web.common.Login;

@Controller
@RequestMapping("/auth")
public class PsychologicalTestController extends BaseController {
	@Autowired
    private PsychologicalTestSettingService psychologicalTestSettingService;
	@Autowired
    private UserPsychologicalService userPsychologicalService;
	@Autowired
    private UserPsychologicalFileService userPsychologicalFileService;
	@Autowired
    private CounselorService counselorService;
	@Autowired
    private CounselingSessionService counselingSessionService;
	@Autowired
    private RequestUserPsychologicalService requestUserPsychologicalService;
	@Resource
	private ServletContext servletContext;
	@Autowired
	private UserService userService;
	
	private static PageCustom<PsychologicalTestCounselor> psychologicalTests;
	
	
	@RequestMapping(value = "/psychologicaltest/list", method = RequestMethod.GET)
	public String index(@RequestParam(value = "page", defaultValue = "1") Integer page, 
			@RequestParam(value = "testname0", defaultValue = "") Integer[] testnameId,
			@RequestParam(value = "statuss", defaultValue = "") Short[] statuss,
			@RequestParam(value = "fullname", defaultValue = "") String fullname,
			@RequestParam(value = "orderBy", defaultValue = "request_psychological") String orderBy,
			HttpServletRequest request,
			HttpSession session, ModelMap map) {
		UserItem userItem = Login.getLoggedInUser(session);
		List<PsychologicalTestSetting> psychologicalTestSettings = psychologicalTestSettingService.findAll();
		psychologicalTests = userPsychologicalService.filterUserPsychologicals(testnameId,statuss, fullname, userItem.getId(), page, 30, orderBy);
		Counselor c = counselorService.findById(userItem.getId());
		User u = userService.findById(userItem.getId());
		List<UserCounselingSession> userCounselingSessions = counselingSessionService.listCounselingSSInAWeek(userItem.getId());
		getMap(map, psychologicalTestSettings, psychologicalTests, c, testnameId, statuss, fullname, orderBy, request, userCounselingSessions);
		map.put("user", u);
		map.put("counselor", c);
		return getView();
	}
	
	public ModelMap getMap(ModelMap map, List<PsychologicalTestSetting> psychologicalTestSettings, PageCustom<PsychologicalTestCounselor> psychologicalTests, Counselor c, 
			Integer[] testnameId, Short[] statuss, String fullname, String orderBy, HttpServletRequest request, List<UserCounselingSession> userCounselingSessions) {
		map.put("psychologicalTestSettings", psychologicalTestSettings);
		map.put("testname0", testnameId);
		map.put("statuss", statuss);
		map.put("fullname", fullname);
		map.put("totalElement", psychologicalTests.getTotalCount());
		
		map.put("beginIndex", 1);
		map.put("endIndex", psychologicalTests.getTotalPages());
		map.put("list", psychologicalTests.getList());
		map.put("currentIndex", psychologicalTests.getCurrent());
		map.put("totalCount",psychologicalTests.getTotalCount());
		map.put("size",psychologicalTests.getSize());
		map.put("totalPages",psychologicalTests.getTotalPages());
		map.put("guidancePassword",c.getGuidancePassword());
		map.put("guidanceUsername",c.getGuidanceUsername());
		map.put("orderBy",orderBy);
		map.put("mbtiCertificate",c.getMbtiCertificate());
		map.put("userCounselingSessions",userCounselingSessions);
		map.put("counselorId",c.getId());
		String param_url= request.getQueryString();
		if(param_url != null && param_url.contains("page=")){
			param_url=param_url.replaceAll("[&?]page.*?(?=&|\\?|$)", "");
		}
		map.put("pagination_navigator", "/auth/psychologicaltest/list");
		return map;
	}
	
	@RequestMapping(value = "/psychologicaltest/detail", method = RequestMethod.GET)
	public String detailPsy(
			@RequestParam(value = "id", defaultValue = "") Integer id,
			HttpServletRequest request,
			HttpSession session, ModelMap map) {
		UserItem userItem = Login.getLoggedInUser(session);
		RequestUserPsychologicalEntity r = requestUserPsychologicalService.findByIdEntity(id);
		map.put("r", r);
		List<UserPsychologicalFileApi> list = userPsychologicalFileService.listByRequestPsychologicalIdInWebCouselor(id);
		map.put("psyDetailList", list);
		map.put("userPsychologicalTestIds", "");
		User u = userService.findById(userItem.getId());
		map.put("counselorId",u.getId());
		return getView();
	}
	
	
	
	
}
