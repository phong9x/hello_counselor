package org.trams.hello.web.controller.counselor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.trams.hello.bean.CounselingMemo;
import org.trams.hello.bean.UserItem;
import org.trams.hello.bean.jpa.CounselingMemoEntity;
import org.trams.hello.bean.jpa.CounselingSessionEntity;
import org.trams.hello.bean.jpa.TestResultEntity;
import org.trams.hello.bean.jpa.UserQuestionaireEntity;
import org.trams.hello.business.service.CounselingMemoService;
import org.trams.hello.business.service.CounselingSessionService;
import org.trams.hello.business.service.TestResultService;
import org.trams.hello.business.service.UserQuestionaireService;
import org.trams.hello.web.common.Login;
import org.trams.hello.web.dto.SelfDiagnosis;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by bryanlee on 2/7/17.
 */
@Controller
@RequestMapping("/auth")
public class CounselorController extends BaseController {

    @Autowired
    private CounselingSessionService counselingSessionService;
    @Autowired
    private CounselingMemoService counselingMemoService;
    @Autowired
    private UserQuestionaireService userQuestionaireService;
    @Autowired
    private TestResultService testResultService;

    @RequestMapping(value = "/reservation/list", method = RequestMethod.GET)
    public String index(
            @RequestParam Map<String, Object> params,
            HttpSession session,
            ModelMap map)
    {
        if (!params.containsKey("page")) params.put("page", 1);
        UserItem userItem = Login.getLoggedInUser(session);

        String counselType0 = String.valueOf(params.get("counselType0"));
        String counselType1 = String.valueOf(params.get("counselType1"));
        String paymentType0 = String.valueOf(params.get("paymentType0"));
        String paymentType1 = String.valueOf(params.get("paymentType1"));

        List<Short> counselTypes = new ArrayList<>(2);
        List<Short> paymentTypes = new ArrayList<>(2);

        if (!counselType0.equals("null") && !counselType0.trim().isEmpty()) {
            counselTypes.add(Short.valueOf(counselType0));
        }
        if (!counselType1.equals("null") && !counselType1.trim().isEmpty()) {
            counselTypes.add(Short.valueOf(counselType1));
        }

        if (!paymentType0.equals("null") && !paymentType0.trim().isEmpty()) {
            paymentTypes.add(Short.valueOf(paymentType0));
        }
        if (!paymentType1.equals("null") && !paymentType1.trim().isEmpty()) {
            paymentTypes.add(Short.valueOf(paymentType1));
        }
        if (!counselTypes.isEmpty()) {
            params.put("counselTypes", counselTypes.toArray(new Short[] {}));
        }
        if (!paymentTypes.isEmpty()) {
            params.put("paymentTypes", paymentTypes.toArray(new Short[] {}));
        }

        params.put("size"               , 30);
        params.put("counselorId"        , userItem.getId());
        params.put("notFinished"        , "yep");
        params.putIfAbsent("sorts"      , "startTime");
        params.putIfAbsent("direction"  , "desc");

        Page<CounselingSessionEntity> sessionEntities = counselingSessionService.filter(params);
        map.put("sessionEntities", sessionEntities);
        map.put("params", params);

        return getView();
    }

    @RequestMapping(value = "/reservation/detail", method = RequestMethod.GET)
    public String detail(
            @RequestParam Integer sessionId,
            HttpSession session,
            ModelMap map)
    {
        map.put("hasCounselingHistory", false);

        UserItem loggedInUser = Login.getLoggedInUser(session);
        Integer counselorId = loggedInUser.getId();

        Map<String, Object> params = new HashMap<>(4);
        params.put("page", 1);
        params.put("size", 10);
        params.put("id"     , sessionId);
        params.put("counselorId" , counselorId);

        Page<CounselingSessionEntity> sessionEntities = counselingSessionService.filter(params);
        if (sessionEntities != null) {
            CounselingSessionEntity cse = sessionEntities.getContent().get(0);
            map.put("session", cse);

            Map<String, Object> historyParams = new HashMap<>(4);
            historyParams.put("page", 1);
            historyParams.put("size", 10);
            historyParams.put("counselorId", counselorId);
            historyParams.put("userId", cse.getUser().getId());

            Page<CounselingSessionEntity> checkSessionHistory = counselingSessionService.filter(historyParams);

            if (!checkSessionHistory.getContent().isEmpty()) {
                map.put("hasCounselingHistory", true);
            }
        }
        return getView();
    }

    @RequestMapping(value = "/counselhistory/list", method = RequestMethod.GET)
    public String counselHistory(
            @RequestParam Map<String, Object> params,
            HttpSession session,
            ModelMap map)
    {
        if (!params.containsKey("page")) params.put("page", 1);
        UserItem userItem = Login.getLoggedInUser(session);

        String status0 = String.valueOf(params.get("status0"));
        String status1 = String.valueOf(params.get("status1"));
        String paymentType0 = String.valueOf(params.get("paymentType0"));
        String paymentType1 = String.valueOf(params.get("paymentType1"));

        List<Short> statuses = new ArrayList<>(2);
        List<Short> paymentTypes = new ArrayList<>(2);

        if (!status0.equals("null") && !status0.trim().isEmpty()) {
            statuses.add(Short.valueOf(status0));
        }
        if (!status1.equals("null") && !status1.trim().isEmpty()) {
            statuses.add(Short.valueOf(status1));
        }

        if (!paymentType0.equals("null") && !paymentType0.trim().isEmpty()) {
            paymentTypes.add(Short.valueOf(paymentType0));
        }
        if (!paymentType1.equals("null") && !paymentType1.trim().isEmpty()) {
            paymentTypes.add(Short.valueOf(paymentType1));
        }

        if (!statuses.isEmpty()) {
            params.put("statuses", statuses.toArray(new Short[] {}));
        } else {
            params.put("statuses", new Short[] {2, 3});
        }
        if (!paymentTypes.isEmpty()) {
            params.put("paymentTypes", paymentTypes.toArray(new Short[] {}));
        }

        params.put("size", 30);
        params.put("counselorId", userItem.getId());

        Page<CounselingSessionEntity> sessionEntities = counselingSessionService.filter(params);
        map.put("sessionEntities", sessionEntities);
        map.put("params", params);

        return getView();
    }

    @RequestMapping(value = "/counselhistory/detail", method = RequestMethod.GET)
    public String counselDetail(
            @RequestParam Integer sessionId,
            HttpSession session,
            ModelMap map)
    {
        UserItem loggedInUser = Login.getLoggedInUser(session);
        Integer counselorId = loggedInUser.getId();

        Map<String, Object> params = new HashMap<>(4);
        params.put("page", 1);
        params.put("size", 10);
        params.put("id"     , sessionId);
        params.put("counselorId" , counselorId);

        Page<CounselingSessionEntity> sessionEntities = counselingSessionService.filter(params);
        if (sessionEntities != null) {
            CounselingSessionEntity cse = sessionEntities.getContent().get(0);
            map.put("session", cse);

            Map<String, Object> memoParams = new HashMap<>(4);
            memoParams.put("page", 1);
            memoParams.put("size", Integer.MAX_VALUE);
            memoParams.put("userId", cse.getUser().getId());
            memoParams.put("counselorId", cse.getCounselor().getId());
            Page<CounselingMemoEntity> memoEntities = counselingMemoService.filter(memoParams);

            for (CounselingMemoEntity cme : memoEntities.getContent()) {
                cme.setUser(null);
                cme.setCounselor(null);
            }

            Map<String, Object> statisfactionParams = new HashMap<>();
            statisfactionParams.put("page"          , 1);
            statisfactionParams.put("size"          , 1);
            statisfactionParams.put("userId"        , cse.getUser().getId());
            statisfactionParams.put("counselorId"   , cse.getCounselor().getId());
            statisfactionParams.put("sessionId"     , cse.getId());
            statisfactionParams.put("types"         , new Short[] { (short) 1});

            Page<UserQuestionaireEntity> satisfactions = userQuestionaireService.filter(statisfactionParams);
            if (!satisfactions.getContent().isEmpty()) {
                UserQuestionaireEntity uqe = satisfactions.getContent().get(0);
                uqe.setCounselor(null);
                uqe.setQuestionnaire(null);
                uqe.setListOfTestResult(null);
                uqe.setUser(null);
                uqe.setSelfDiagnosisResults(null);

                map.put("satisfaction", uqe);
            }

            map.put("memos", memoEntities);
        }
        return getView();
    }

    @RequestMapping(value = "/counselmemo/detail", method = RequestMethod.DELETE)
    @ResponseBody
    public Object memoDelete(
            @RequestParam Integer memoId
    ) {
        counselingMemoService.delete(memoId);
        return SUCCESS();
    }

    @RequestMapping(value = "/counselmemo", method = RequestMethod.POST)
    @ResponseBody
    public Object memoCreate(
            @RequestParam Map<String, Object> params,
            HttpSession session
    ) {
        UserItem loggedInUser = Login.getLoggedInUser(session);
        Integer counselorId = loggedInUser.getId();
        String memo         = String.valueOf(params.get("memo"));
        Integer userId      = Integer.parseInt(String.valueOf(params.get("userId")));

        CounselingMemo counselingMemo = new CounselingMemo();
        counselingMemo.setUserId(userId);
        counselingMemo.setCounselorId(counselorId);
        counselingMemo.setMemo(memo);
        counselingMemo.setCreateDate(new Date());
        counselingMemoService.create(counselingMemo);

        return SUCCESS();
    }

    @RequestMapping(value = "/mindcheck", method = RequestMethod.GET)
    @ResponseBody
    public Object getMindChecks(
            @RequestParam(defaultValue = "0") int userId,
            HttpSession session
    ) {
        UserItem loggedInUser = Login.getLoggedInUser(session);
        Integer counselorId = loggedInUser.getId();

        Map<String, Object> params = new HashMap<>();
        params.put("page", 1);
        params.put("size", Integer.MAX_VALUE);
        //params.put("counselorId", counselorId);
        params.put("userId"     , userId);
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
    
    @RequestMapping(value = "/selfDiagnosis", method = RequestMethod.GET)
    @ResponseBody
    public Object selfDiagnosis(
            @RequestParam(defaultValue = "0") int userId,
            HttpSession session) {
    	HashMap<String, Object> ret = new HashMap<>();
        List<SelfDiagnosis> stressDiagnosis = userQuestionaireService.getSelfDiagnosis(3, userId);
        List<SelfDiagnosis> dipressedDiagnosis = userQuestionaireService.getSelfDiagnosis(4, userId);
        List<SelfDiagnosis> anxietyDiagnosis = userQuestionaireService.getSelfDiagnosis(5, userId);
        
        ret.put("stressDiagnosis", stressDiagnosis);
        ret.put("dipressedDiagnosis", dipressedDiagnosis);
        ret.put("anxietyDiagnosis", anxietyDiagnosis);
        
        return SUCCESS(ret);
    }

}
