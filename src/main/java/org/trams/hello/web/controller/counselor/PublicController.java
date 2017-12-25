package org.trams.hello.web.controller.counselor;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.trams.hello.bean.CounselingCenter;
import org.trams.hello.bean.Counselor;
import org.trams.hello.bean.Policy;
import org.trams.hello.bean.User;
import org.trams.hello.bean.UserItem;
import org.trams.hello.bean.jpa.CategoryEntity;
import org.trams.hello.bean.jpa.CounselorBiographyEntity;
import org.trams.hello.bean.jpa.CounselorEntity;
import org.trams.hello.bean.jpa.CounselorFieldEntity;
import org.trams.hello.bean.jpa.UserCertificateEntity;
import org.trams.hello.bean.jpa.UserEducationEntity;
import org.trams.hello.bean.jpa.UserEntity;
import org.trams.hello.bean.jpa.UserFileEntity;
import org.trams.hello.business.event.FindPasswordEvent;
import org.trams.hello.business.event.GenerateTokenEvent;
import org.trams.hello.business.service.CategoryService;
import org.trams.hello.business.service.CounselingCenterService;
import org.trams.hello.business.service.CounselorBiographyService;
import org.trams.hello.business.service.CounselorFieldService;
import org.trams.hello.business.service.CounselorService;
import org.trams.hello.business.service.PolicyService;
import org.trams.hello.business.service.UserCertificateService;
import org.trams.hello.business.service.UserEducationService;
import org.trams.hello.business.service.UserFileService;
import org.trams.hello.business.service.UserService;
import org.trams.hello.business.service.mapping.UserServiceMapper;
import org.trams.hello.rest.model.enumeration.LoginType;
import org.trams.hello.web.common.ApplicationDefine;
import org.trams.hello.web.common.Login;
import org.trams.hello.web.common.utils.EncryptionUtils;
import org.trams.hello.web.dto.UserDto;

@Controller
@RequestMapping("/public")
public class PublicController extends BaseController {

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private UserService userService;
	@Autowired
	private CounselingCenterService counselingCenterService;
	@Autowired
	private CounselorService counselorService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CounselorFieldService counselorFieldService;
	@Autowired
	private CounselorBiographyService counselorBiographyService;
	@Autowired
	private UserEducationService userEducationService;
	@Autowired
	private UserCertificateService userCertificateService;
	@Autowired
	private UserFileService userFileService;
	@Autowired
	private UserServiceMapper userServiceMapper;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	private PolicyService policyService;
	
	@RequestMapping(value = "/findidpwd", method = RequestMethod.GET)
	public String findPasswordId(ModelMap map) {
		return getView();
	}

	@RequestMapping(value = "/findid", method = RequestMethod.POST)
	@ResponseBody
	public Object findId(
			@RequestParam String fullname,
			@RequestParam String phone)
	{
		UserEntity userEntity = userService.findByFullnameAndPhone(fullname, phone);
		if (userEntity == null) {
			return ERROR();
		}

		// send token to phone numer
		String token = RandomStringUtils.randomNumeric(6);
		applicationEventPublisher.publishEvent(new GenerateTokenEvent(this, phone, token));

		String username 				= userEntity.getUsername();
		StringBuilder maskedUsername 	= new StringBuilder(username);
		int indexOfAt 	= maskedUsername.indexOf("@");
		if (indexOfAt > -1) {
			maskedUsername.replace(indexOfAt-2, indexOfAt, "**");
		}

		Map<String, Object> result = new HashMap<>(2);
		result.put("token"	, token);
		result.put("id"		, maskedUsername);
		return SUCCESS(result);
	}

	@RequestMapping(value = "/findpwd", method = RequestMethod.POST)
	@ResponseBody
	public Object findPwd(
			@RequestParam String fullname,
			@RequestParam String username,
			@RequestParam String phone)
	{
		UserEntity userEntity = userService.findByFullnameAndUsernameAndPhone(fullname, username, phone);
		if (userEntity == null) {
			return ERROR();
		}

		// send token to phone numer
		String token = RandomStringUtils.randomNumeric(6);
		applicationEventPublisher.publishEvent(new GenerateTokenEvent(this, phone, token));

		Map<String, Object> result = new HashMap<>(2);
		result.put("token"	, token);
		return SUCCESS(result);
	}

	@RequestMapping(value = "/findpwd/confirm", method = RequestMethod.POST)
	@ResponseBody
	public Object findPwdConfirm(
			@RequestParam String fullname,
			@RequestParam String username,
			@RequestParam String phone)
	{
		UserEntity userEntity = userService.findByFullnameAndUsernameAndPhone(fullname, username, phone);
		if (userEntity == null) {
			return ERROR();
		}

		User user = userServiceMapper.mapUserEntityToUser(userEntity);

		// send tmpPwd to phone numer
		String tmpPwd = RandomStringUtils.randomNumeric(6);
		user.setPasswordTemp(EncryptionUtils.jwtBuild(tmpPwd));
		user.setPassword(EncryptionUtils.jwtBuild(tmpPwd));
		userService.update(user);

		applicationEventPublisher.publishEvent(new FindPasswordEvent(this, phone, tmpPwd));
		return SUCCESS();
	}

	@RequestMapping(value = "/home", method = RequestMethod.POST)
	@ResponseBody
	public Object loginPost(@RequestBody Map<String, Object> params, HttpSession session, ModelMap map) {
		Date currentDate 	= new Date();
		String username 	= String.valueOf(params.get("username"));
		String password 	= String.valueOf(params.get("password"));
		User user 			= userService.loginCounselor(username, EncryptionUtils.jwtBuild(password));

		// check main password
		if (user == null) {
			// check temp password
			user = userService.loginCounselorTemp(username, EncryptionUtils.jwtBuild(password));
			if (user == null) {
				return FAIL(-1);
			}
		}

		Counselor counselor = counselorService.findById(user.getId());
		if (counselor == null) {
			return FAIL(-1);
		}
		
		if (user.getStatusActive().equals(ApplicationDefine.UserStatusActive.WITHDRAWAL.getCode())) {
			return FAIL(9);
		}

		if (counselor.getStatus().equals(ApplicationDefine.Counselor_Status.Waiting.getCode())
				|| counselor.getStatus().equals(ApplicationDefine.Counselor_Status.Reserve.getCode())) {
			return FAIL(counselor.getStatus());
		}

		UserItem u = new UserItem();
		u.setId(user.getId());
		u.setFullname(user.getFullname());
		u.setLastLogin(currentDate);
		u.setEmail(user.getEmail());
		u.setRole(user.getRoleId().toString());
		u.setAvatar(counselor.getThumbnailUrl());
		Login.saveLoggedInUser(u, session);
		user.setLastLogin(currentDate);
		userService.update(user);
		if (user.getLastLogin() != null) {
			// get 3 months from now
			Calendar calendar = Calendar.getInstance();
			calendar.add(3, Calendar.MONTH);
			Date threeMonthsFromNow = calendar.getTime();

			if (threeMonthsFromNow.after(user.getLastLogin())) {
				return SUCCESS(Collections.singletonMap("status", LoginType.DORMANT.getCode()));
			}
		}

		return SUCCESS(Collections.singletonMap("status", LoginType.NORMAL.getCode()));

	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerGet(ModelMap map) {

		List<CounselingCenter> businesses = counselingCenterService.findAll();
		List<CategoryEntity> categories = categoryService.findByTypeAndIsShow((short) 1, (short) 1);
		
		Policy termsOfService = policyService.getPoli((short)1, (short)2);
		Policy privacyPolicy = policyService.getPoli((short) 2, (short)2);
		
    	map.put("termsOfService", termsOfService);
    	map.put("privacyPolicy", privacyPolicy);
		
		map.put("businesses", businesses);
		map.put("categories", categories);

		return getView();
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public Object registerPost(@ModelAttribute UserDto param) {
		try {
			userService.register(param, servletContext);
			return SUCCESS();
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR();
		}
	}

	@RequestMapping(value = "/postpone", method = RequestMethod.GET)
	public String postpone(@RequestParam(defaultValue = "") String username, ModelMap map) {

		try {
			UserEntity 					user 			= userService.findOneBy_Username(username);
			CounselorEntity             counselor       = counselorService.findByOne(user.getId());
			List<CounselingCenter> 		businesses 		= counselingCenterService.findAll();
			List<CategoryEntity>        categories      = categoryService.findByTypeAndIsShow((short) 1, (short) 1);
			List<UserFileEntity>        userFiles       = userFileService.listPagingByTypeAndUserId((short) 1, user.getId());
			List<UserCertificateEntity> certificates    = userCertificateService.findByUserId(user.getId());
			UserEducationEntity         education       = userEducationService.findByUserId(user.getId());
			List<CounselorBiographyEntity> biographies  = counselorBiographyService.findByCounselorId(user.getId());

			List<CounselorFieldEntity>  fields          = counselorFieldService.listBy_CounselorId(user.getId());
			List<Integer>               _fields         = new LinkedList<>();
			for (CounselorFieldEntity cfe : fields) {
				_fields.add(cfe.getCounselorFieldId());
			}

			map.put("businesses"	, businesses);
			map.put("categories"    , categories);
			map.put("user"          , user);
			map.put("counselor"     , counselor);
			map.put("fields"        , _fields);
			map.put("userFiles"     , userFiles);
			map.put("certificates"  , certificates);
			map.put("education"     , education);
			map.put("biographies"   , biographies);

			if (!counselor.getStatus().equals(ApplicationDefine.Counselor_Status.Reserve.getCode())) {
				throw new IllegalArgumentException("Counselor is not reserved");
			}

			return getView();
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/public/register";
		}
	}

	@RequestMapping(value = "/postpone", method = RequestMethod.POST)
	@ResponseBody
	public Object postponePost(@ModelAttribute UserDto param) {
		try {
			userService.postpone(param, servletContext);
			return SUCCESS();
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR();
		}
	}

	@RequestMapping(value = "/sendToken", method = RequestMethod.POST)
	@ResponseBody
	public Object sendToken(@RequestParam String phone) {

		String token = RandomStringUtils.randomNumeric(6);
		applicationEventPublisher.publishEvent(new GenerateTokenEvent(this, phone, token));
		return SUCCESS(Collections.singletonMap("token", token));
	}

	@RequestMapping(value = "/checkexistence", method = RequestMethod.POST)
	@ResponseBody
	public Object checkExistence(@RequestParam String username) {

		UserEntity userEntity = userService.findOneBy_Username(username);
		Short status = -1; // not exist
		
		if(userEntity != null){
			status = 1; //exist
			CounselorEntity counselorEntity = counselorService.findByOne(userEntity.getId());
			if(counselorEntity != null){
				status = counselorEntity.getStatus(); //if 3 -> pause
			}
		}

		return SUCCESS(Collections.singletonMap("status", status));
	}

}
