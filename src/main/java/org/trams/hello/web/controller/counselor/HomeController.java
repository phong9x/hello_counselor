package org.trams.hello.web.controller.counselor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.trams.hello.bean.User;
import org.trams.hello.bean.UserItem;
import org.trams.hello.bean.jpa.*;
import org.trams.hello.business.service.*;
import org.trams.hello.business.service.mapping.CounselorServiceMapper;
import org.trams.hello.web.common.ApplicationDefine;
import org.trams.hello.web.common.Login;
import org.trams.hello.web.common.utils.ConstantUtils;
import org.trams.hello.web.common.utils.DataUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bryanlee on 09/01/2017.
 */
@Controller
@RequestMapping("/auth")
public class HomeController extends BaseController {

    private static short TYPE_STATIC_EVALUTION  = 1;
    private static RestTemplate restTemplate    = new RestTemplate();

    @Autowired
    private UserCertificateService userCertificateService;
    @Autowired
    private UserEducationService userEducationService;
    @Autowired
    private CounselorService counselorService;
    @Autowired
    private CounselorServiceMapper counselorServiceMapper;
    @Autowired
    private CounselorBiographyService counselorBiographyService;
    @Autowired
    private CounselingSessionService counselingSessionService;
    @Autowired
    private UserReceiveMessageService userReceiveMessageService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private PaymentProfitService paymentProfitService;
    @Autowired
    private CounselorScheduleSettingService counselorScheduleSettingService;
    @Autowired
    private UserQuestionaireService userQuestionaireService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(HttpSession session, ModelMap map) {
        UserItem loggedInUser       = Login.getLoggedInUser(session);
        Integer userId              = loggedInUser.getId();

        User user                   = userService.findById(userId);
        CounselorEntity counselor   = counselorService.findByOne(userId);
        Calendar now                = Calendar.getInstance();

        int certificates        = userCertificateService.countByUser(userId);
        int educations          = userEducationService.countByUser(userId);
        boolean isActive        = false;
        long lastCounsel, currentCounsel, scheduledCounselor, notSeenMessage;

        // calculate total counsel till now
        Map<String, Object> sessionParams = new HashMap<>();
        sessionParams.put("counselorId"     , userId);
        sessionParams.put("statuses"        , new Short[] {ApplicationDefine.CounselingSessionStatus.NOT_COUNSELED.getCode()});
        sessionParams.put("notFinished"     , "yep");

        scheduledCounselor = counselingSessionService.count(sessionParams);

        // calculate is online status
        if (counselor.getCounselOnline().equals((short) 1)) {
            Map<String, Object> reservationParams = new HashMap<>();
            reservationParams.put("page", 1);
            reservationParams.put("size", 10);
            reservationParams.put("counselorId", userId);
            reservationParams.put("reservationFrom", DataUtils.parseStringFromDate(now.getTime(), DataUtils.DatePattern.YYYYMMDD.getPattern()));
            reservationParams.put("reservationTo", DataUtils.parseStringFromDate(now.getTime(), DataUtils.DatePattern.YYYYMMDD.getPattern()));

            Page<CounselorScheduleSettingEntity> scheduleSettings = counselorScheduleSettingService.filter(reservationParams);
            if (!scheduleSettings.getContent().isEmpty()) {
                CounselorScheduleSettingEntity csse = scheduleSettings.getContent().get(0);
                if (csse.getOnlineTime() != null) {
                    String thisTime;
                    if (now.get(Calendar.MINUTE) <= 30) {
                        thisTime = now.get(Calendar.HOUR_OF_DAY) + 0.5 + "";
                    } else {
                        thisTime = now.get(Calendar.HOUR_OF_DAY) + 1 + "";
                    }
                    isActive = Arrays.asList(csse.getOnlineTime().split(";")).contains(thisTime);
                }
            }
        }

        /*calculate money by month*/
        Calendar c = Calendar.getInstance();
		int month = c.get(Calendar.MONTH); // beware of month indexing from zero
		int year  = c.get(Calendar.YEAR);
        if(user.getRoleId() != 3) {
        	PaymentProfitEntity getTotalMoneyByMonth = paymentProfitService.getTotalMoneyByMonth(year, month, user.getId(), (short) 1);
    		map.put("isCounselorCenter", 0);
    		map.put("getTotalMoneyByMonth" , getTotalMoneyByMonth);
    	} else {
    		map.put("isCounselorCenter", 1);
		}
        
        /*calculate lastCounsel by last month*/
        Calendar currentCC1 = Calendar.getInstance();
        Calendar currentCC2 = Calendar.getInstance();
        currentCounsel = counselingSessionService.countTotalCouseling(DataUtils.firstDayCurrentMonth(currentCC1), currentCC2.getTime(), userId);
        
		Calendar calendarNew1 = Calendar.getInstance();
		Calendar calendarNew2 = Calendar.getInstance();
        lastCounsel = counselingSessionService.countTotalCouseling(DataUtils.firstDayLastMonth(calendarNew1), DataUtils.lastDayLastMonth(calendarNew2), userId);
        
        // filter notice
        Page<NoticeEntity> notices = noticeService.listPagingByCounselorIdInWebC(1, 4, "create_date");
        // filter comment
        Page<UserQuestionaireEntity> comments = userQuestionaireService.listUserQuestionareByCouselorId(counselor.getId(), TYPE_STATIC_EVALUTION, 1, 4);
        
        // filter messages
        Page<UserReceiveMessageEntity> messages = userReceiveMessageService.filterByUser(userId, new Short[]{0}, 1, Integer.MAX_VALUE);
        notSeenMessage = messages.getContent().size();
        
//        float points = counselingSessionService.totalPointByCounselor(counselor.getId());
//        counselor.setPoints((float) (Math.round((points) * 10.0) / 10.0));
//        counselorService.update(counselorServiceMapper.mapCounselorEntityToCounselor(counselor));
        
        map.put("counselor"             , counselor);
        map.put("certificates"          , certificates);
        map.put("educations"            , educations);
        map.put("lastCounsel"           , lastCounsel);
        map.put("currentCounsel"        , currentCounsel);
        map.put("scheduledCounselor"    , scheduledCounselor);
        map.put("notSeenMessage"        , notSeenMessage);
        map.put("isActive"              , isActive);
        map.put("notices"               , notices.getContent());
        map.put("comments"              , comments.getContent());

        return getView();
    }

    @RequestMapping(value = "/home", method = RequestMethod.PUT)
    @ResponseBody
    public Object homeUpdate(HttpSession session) {
        UserItem loggedInUser       = Login.getLoggedInUser(session);
        Integer userId              = loggedInUser.getId();

        userService.updateLogin(userId);
        return SUCCESS();
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Object home(HttpSession session) {
        Login.removeLoggedInUser(session);
        return SUCCESS();
    }
    
    @RequestMapping(value = "/changepassword", method = RequestMethod.GET)
    public Object changePassword(HttpServletRequest request, HttpSession session, ModelMap map) {
		UserItem userItem = Login.getLoggedInUser(session);
		map.put("counselorId", userItem.getId());
    	return getView();
    }

    @RequestMapping(value = "/polling", method = RequestMethod.GET)
    @ResponseBody
    public Object polling(HttpSession session) {
        UserItem userItem = Login.getLoggedInUser(session);
        Integer counselorId = userItem.getId();

        Map result = restTemplate.getForObject(ConstantUtils.getConfig("admin.domain") + "/api/v1/public/polling/" + counselorId, Map.class);

        return SUCCESS(result.get("data"));
    }
    
}
