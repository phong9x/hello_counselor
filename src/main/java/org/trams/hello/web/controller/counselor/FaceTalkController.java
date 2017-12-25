package org.trams.hello.web.controller.counselor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.trams.hello.bean.CounselingMemo;
import org.trams.hello.bean.PsychologicalTestSetting;
import org.trams.hello.bean.RequestUserPsychological;
import org.trams.hello.bean.UserPsychological;
import org.trams.hello.bean.jpa.CounselingMemoEntity;
import org.trams.hello.bean.jpa.CounselingSessionEntity;
import org.trams.hello.bean.jpa.CounselorEntity;
import org.trams.hello.bean.jpa.TestResultEntity;
import org.trams.hello.bean.jpa.UserEntity;
import org.trams.hello.bean.jpa.UserQuestionaireEntity;
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
import org.trams.hello.web.common.ApplicationDefine;
import org.trams.hello.web.dto.SelfDiagnosis;

/**
 * Created by bryanlee on 1/23/17.
 */
@Controller
@RequestMapping("/auth")
public class FaceTalkController extends BaseController {

    @Autowired
    private RtcService rtcService;
    @Autowired
    private UserService userService;
    @Autowired
    private CounselingSessionService counselingSessionService;
    @Autowired
    private CounselingMemoService counselingMemoService;
    @Autowired
    private UserQuestionaireService userQuestionaireService;
    @Autowired
    private TestResultService testResultService;
    @Autowired
    private PsychologicalTestSettingService psychologicalTestSettingService;
    @Autowired
    private RequestUserPsychologicalService requestUserPsychologicalService;
    @Autowired
    private UserPsychologicalService userPsychologicalService;
    @Autowired
    private CounselorService counselorService;
    @Autowired
    private NotificationService notificationService;

    @RequestMapping(value = "/otp", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> generateOtp(@RequestParam HashMap<String, Object> params) {
        Map<String, Object> participant = rtcService.createParticipant(params);
        
        System.out.println("kq:" + participant);
        
        return SUCCESS(participant);
    }

    @RequestMapping(value = "/talk", method = RequestMethod.GET)
    public String home(
            @RequestParam Map<String, Object> params,
            ModelMap map,
            HttpSession session)
    {
    	
        params.put("page", 1);
        params.put("size", Integer.MAX_VALUE);
        Page<CounselingSessionEntity> sessionEntities = counselingSessionService.filter(params);
        CounselingSessionEntity cse = sessionEntities.getContent().get(0);

        DateFormat formatter = new SimpleDateFormat("yyyy");
        int age = Integer.parseInt(formatter.format(new Date())) - Integer.parseInt(formatter.format(cse.getUser().getDayOfBirth()));

        List<PsychologicalTestSetting> psy = psychologicalTestSettingService.findAll();
        
        if(cse.getCounselType() == 1){
            Date counselorEntry = cse.getEntryCounselorTime();
            Date userEntry = cse.getEntryUserTime();
            if(counselorEntry != null && userEntry != null){
            	Long startTime = (counselorEntry.after(userEntry) ? counselorEntry.getTime() : userEntry.getTime());
            	map.put("endTime", startTime + (cse.getCounselingTimeType() * 60 * 1000));
            }
        }        
        
        map.put("user"          , cse.getUser());
        map.put("counselor"     , cse.getCounselor());
        map.put("age"           , age);
        map.put("total"         , sessionEntities.getTotalElements());
        map.put("startTime"     , cse.getStartTime().getTime());
        map.put("sessionTime"   , cse.getCounselingTimeType());
       
        map.put("session"       , cse);
        map.put("psy"           , psy);

        return getView();
    }

    @RequestMapping(value = "/talk/join", method = RequestMethod.GET)
    public Object join(
            @RequestParam String roomId,
            @RequestParam(defaultValue = "0") int type
    ) {
        CounselingSessionEntity sessionEntity = counselingSessionService.findOneByRoomId(roomId);
        Date curentDate = new Date();
        // counselor join
        if (type == 0 && sessionEntity.getEntryCounselorTime() == null ) {
            sessionEntity.setEntryCounselorTime(curentDate);
            //sessionEntity.setStartTime(curentDate);
        }
        // user join
        if (type == 1 && sessionEntity.getEntryUserTime() == null) {
            sessionEntity.setEntryUserTime(curentDate);
        }

        counselingSessionService.saveOrUpdate(sessionEntity);
        return SUCCESS();
    }

    @RequestMapping(value = "/talk/memo", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getMemo(
            @RequestParam String roomId
    ) {
        CounselingSessionEntity sessionEntity = counselingSessionService.findOneByRoomId(roomId);

        Map<String, Object> params = new HashMap<>();
        params.put("size"       , 10);
        params.put("page"       , 1);
        params.put("size"       , Integer.MAX_VALUE);
        params.put("userId"     , sessionEntity.getUser().getId());
        params.put("counselorId", sessionEntity.getCounselor().getId());
        Page<CounselingMemoEntity> memoEntities = counselingMemoService.filter(params);
        for (CounselingMemoEntity cme : memoEntities.getContent()) {
            cme.setUser(null);
            cme.setCounselor(null);
        }

        return SUCCESS(memoEntities);
    }

    @RequestMapping(value = "/talk/memo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> saveMemo(
            @RequestParam String roomId,
            @RequestParam String memo
    ) {
        CounselingSessionEntity cse = counselingSessionService.findOneByRoomId(roomId);

        CounselingMemo counselingMemo = new CounselingMemo();
        counselingMemo.setUserId(cse.getUser().getId());
        counselingMemo.setCounselorId(cse.getCounselor().getId());
        counselingMemo.setMemo(memo);
        counselingMemo.setCreateDate(new Date());
        counselingMemoService.create(counselingMemo);
        return SUCCESS();
    }

    @RequestMapping(value = "/talk/memo", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<?> deleteMemo(
            @RequestParam Integer memoId)
    {
        counselingMemoService.delete(memoId);
        return SUCCESS();
    }

    @RequestMapping(value = "/talk/mindcheck", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> questions(
            @RequestParam String roomId,
            HttpSession session
    ) {

        CounselingSessionEntity cse = counselingSessionService.findOneByRoomId(roomId);

        Map<String, Object> params = new HashMap<>();
        params.put("page", 1);
        params.put("size", Integer.MAX_VALUE);
        //params.put("counselorId", counselorId);
        params.put("userId"     , cse.getUser().getId());
        params.put("types"      , new Short[] { (short) 2 });

        Page<UserQuestionaireEntity> userQuestionaireEntities = userQuestionaireService.filter(params);

        List<Map<String, Object>> mindchecks = new ArrayList<>();

        if (!userQuestionaireEntities.getContent().isEmpty()) {

            Map<String, Object> testParams = new HashMap<>();
            testParams.put("page"       , 1);
            testParams.put("size"       , Integer.MAX_VALUE);

            for (UserQuestionaireEntity uqe : userQuestionaireEntities.getContent()) {

                Map<String, Object> mindcheck = new HashMap<>();
                mindcheck.put("createdDate" , uqe.getCreateDate());
                mindcheck.put("point"       , uqe.getPoint());

                testParams.put("questionId" , uqe.getId());
                Page<TestResultEntity> testResultEntities = testResultService.filter(testParams);
                List<Map<String, Object>> testResults = new ArrayList<>();
                for (TestResultEntity entity : testResultEntities.getContent()) {
                    Map<String, Object> testMap = new HashMap<>();
                    testMap.put("question"  , entity.getQuestion().getContent());
                    testMap.put("answer"    , entity.getAnswer().getContent());
                    testResults.add(testMap);
                }

                mindcheck.put("data", testResults);
                mindchecks.add(mindcheck);
            }
        }

        return SUCCESS(mindchecks);
    }

    @RequestMapping(value = "/talk/finish", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> finish(
            @RequestParam Integer sessionId
    ) {
        Date currentDate = new Date();
        CounselingSessionEntity cse = counselingSessionService.findOne(sessionId);
        if(!cse.getStatus().equals(ApplicationDefine.CounselingSessionStatus.COUNSELD.getCode())){
        	cse.setEndTime(currentDate);
        	cse.setExitCounselorTime(currentDate);
        	cse.setExitUserTime(currentDate);
        	cse.setStatus(ApplicationDefine.CounselingSessionStatus.COUNSELD.getCode());
        	counselingSessionService.saveOrUpdate(cse);
        }
        return SUCCESS();
    }

    @RequestMapping(value = "/talk/psy", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> psy(
            @RequestParam Map<String, Object> params
    ) {
        List<PsychologicalTestSetting> psychologicalTestSettings = psychologicalTestSettingService.findAll();
        return SUCCESS(psychologicalTestSettings);
    }

    @RequestMapping(value = "/talk/psy", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> createPsy(
            @RequestParam String roomId,
            @RequestParam String money,
            @RequestParam String psy
    ) {

        CounselingSessionEntity cse = counselingSessionService.findOneByRoomId(roomId);

        int userId      = cse.getUser().getId();
        int counselorId = cse.getCounselor().getId();
        String psys[]    = psy.split(",");

        RequestUserPsychological r = new RequestUserPsychological();
        Calendar now = Calendar.getInstance();
        Date currentDay         = now.getTime();
        now.add(Calendar.DATE, 3);
        Date threeDaysFromNow   = now.getTime();

        r.setCounselorId(cse.getCounselor().getId());
        r.setUserId(cse.getUser().getId());
        r.setRequestPsychological(currentDay);
        r.setStatus((short) 0);
        r.setCreateDate(currentDay);
        r.setUpdateDate(currentDay);
        r.setEffectPaymentPsychological(threeDaysFromNow);
        r.setFee(Integer.parseInt(money));
        r.setEmail(cse.getUser().getEmail());
        r = requestUserPsychologicalService.create(r);

        for (String p : psys) {
            UserPsychological u = new UserPsychological();
            u.setUserId(userId);
            u.setCounselorId(counselorId);
            u.setCreateDate(currentDay);
            u.setPsychologicalTestId(Integer.parseInt(p));
            u.setRequestPsychologicalId(r.getId());
            userPsychologicalService.create(u);
        }

        UserEntity u = userService.findOne(cse.getUser().getId());
		CounselorEntity c = counselorService.findByOne(cse.getCounselor().getId());
		notificationService.createNotification(u, u.getUsername(),"심리검사 결제 안내", c.getUser().getFullname() + "심리상담사가 요청한 심리검사를 결제해주세요.", null, (short) ApplicationDefine.Notification_Type.PsychologicalTest.getCode(),null);
        return SUCCESS();
    }
    
    @RequestMapping(value = "/talk/selfDiagnosis", method = RequestMethod.GET)
    @ResponseBody
    public Object selfDiagnosis(
    		@RequestParam String roomId) {
    	
    	CounselingSessionEntity cse = counselingSessionService.findOneByRoomId(roomId);
    	
    	HashMap<String, Object> ret = new HashMap<>();
        List<SelfDiagnosis> stressDiagnosis = userQuestionaireService.getSelfDiagnosis(3, cse.getUser().getId());
        List<SelfDiagnosis> dipressedDiagnosis = userQuestionaireService.getSelfDiagnosis(4, cse.getUser().getId());
        List<SelfDiagnosis> anxietyDiagnosis = userQuestionaireService.getSelfDiagnosis(5, cse.getUser().getId());
        
        ret.put("stressDiagnosis", stressDiagnosis);
        ret.put("dipressedDiagnosis", dipressedDiagnosis);
        ret.put("anxietyDiagnosis", anxietyDiagnosis);
        
        return SUCCESS(ret);
    }

    @RequestMapping(value = "/talk/room", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> newRoom(
            @RequestParam Integer counselingSessionId,
            @RequestParam Integer disconnectCount
    ) {
        CounselingSessionEntity cse = counselingSessionService.findOne(counselingSessionId);
        if(cse != null && cse.getDisconnectCount() < disconnectCount){
        	cse.setDisconnectCount(disconnectCount);
    		cse = counselingSessionService.saveOrUpdate(cse);
        }
        
        HashMap<String, Object> ret = new HashMap<>();
        ret.put("session", cse);
        
        return SUCCESS(ret);
    }
    
}
