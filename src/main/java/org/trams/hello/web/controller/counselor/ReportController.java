package org.trams.hello.web.controller.counselor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.trams.hello.bean.jpa.CounselingSessionEntity;
import org.trams.hello.bean.jpa.CounselorEntity;
import org.trams.hello.bean.jpa.PaymentHistoryEntity;
import org.trams.hello.bean.jpa.PaymentProfitEntity;
import org.trams.hello.bean.web.counselor.CounselingSessionDto;
import org.trams.hello.business.service.CounselingSessionService;
import org.trams.hello.business.service.CounselorService;
import org.trams.hello.business.service.PaymentProfitService;
import org.trams.hello.web.common.ApplicationDefine;
import org.trams.hello.web.common.Login;
import org.trams.hello.web.common.utils.ExcelUtils;

/**
 * Created by bryanlee on 4/11/17.
 */
@Controller
@RequestMapping("/auth")
public class ReportController extends BaseController {

    private DateFormat formatterDate        = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
    private DateFormat formatterDateWeek    = new SimpleDateFormat("MM-dd", Locale.KOREA);
    private DateFormat formatterDateMonth   = new SimpleDateFormat("yyyy-MM", Locale.KOREA);

    @Autowired
    private CounselingSessionService counselingSessionService;
    @Autowired
    private CounselorService counselorService;
    @Autowired
    private PaymentProfitService paymentProfitService;

    @RequestMapping(value = "/report/statistics", method = RequestMethod.GET)
    public String statistics() {
        return getView();
    }

    @RequestMapping(value = "/report/profit", method = RequestMethod.GET)
    public String profit(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month,
            HttpSession session,
            ModelMap map
    ) {

        Calendar instance = Calendar.getInstance();
        if (year == null) {
            year = instance.get(Calendar.YEAR);
        }
        if (month == null) {
            month = instance.get(Calendar.MONTH) + 1;
        }

        // query counselor info
        int counselorId = Login.getLoggedInUser(session).getId();
        CounselorEntity counselorEntity = counselorService.findByOne(counselorId);
        Map<String, Object> model = new HashMap<>();
        model.put("year"    , year);
        model.put("month"   , month);
        model.put("accountBankHolder"   , counselorEntity.getAccountBankHolder());
        model.put("accountBankNumber"   , counselorEntity.getAccountBankNumber());
        model.put("accountBankName"     , counselorEntity.getBank() != null ? counselorEntity.getBank().getBankName() : "");

        // query payment profit
        Map<String, Object> paramsProfit = new HashMap<>();
        paramsProfit.put("page"         , 1);
        paramsProfit.put("size"         , Integer.MAX_VALUE);
        paramsProfit.put("month"        , month);
        paramsProfit.put("year"         , year);
        paramsProfit.put("userId"       , counselorId);
        if(month == instance.get(Calendar.MONTH) + 1 && year == instance.get(Calendar.YEAR)){
        	
        }
        Page<PaymentProfitEntity> paymentProfitEntities = paymentProfitService.filter(paramsProfit);
        if (!paymentProfitEntities.getContent().isEmpty()) {
            model.put("profit", paymentProfitEntities.getContent().get(0));
            map.put("profit_status",paymentProfitEntities.getContent().get(0).getStatus());
        }else{
        	map.put("profit_status",0);
        }

        // query payment history
        List<PaymentHistoryEntity> paymentHistories = new LinkedList<>();

        Map<String, Object> paramsSession = new HashMap<>();
        paramsSession.put("page"       , 1);
        paramsSession.put("size"       , Integer.MAX_VALUE);
        paramsSession.put("counselorId", Login.getLoggedInUser(session).getId());
        paramsSession.put("statuses"   , new Short[] { 1, 2, 3 });

        paramsSession.put("fromTime"   , year + "/" + month + "/01");
        paramsSession.put("toTime"     , year + "/" + month + "/31");

        Page<CounselingSessionEntity> sessionEntities = counselingSessionService.filter(paramsSession);
        for (CounselingSessionEntity cse : sessionEntities.getContent()) {
            PaymentHistoryEntity payment = cse.getPaymentHistory();
            if (payment != null) {
                if (payment.getStatus().equals((short) 1) && payment.getTypePayment().equals(2)) {
                    paymentHistories.add(payment);
                }
            }
        }

        Collections.sort(paymentHistories, new Comparator<PaymentHistoryEntity>() {
            @Override
            public int compare(PaymentHistoryEntity o1, PaymentHistoryEntity o2) {
                return o1.getId().compareTo(o2.getId());
            }
        });

        model.put("paymentHistories", paymentHistories);
        if(counselorEntity.getProfitPercent() == null){
        	map.put("percent", ApplicationDefine.COUNSELOR_PROFIT_PERCENT_DEFAULT);
        }else{
        	map.put("percent", counselorEntity.getProfitPercent());
        }
        map.put("tax_vat", ApplicationDefine.TAX_VAT);
        map.put("tax_profit", ApplicationDefine.TAX_PROFIT_COUNSELOR);
       
        map.put("model", model);
        return getView();
    }

    @RequestMapping(value = "/report/statistics/data", method = RequestMethod.GET)
    @ResponseBody
    public Object getData(
            @RequestParam(required = false) String fromTime,
            @RequestParam(required = false) String toTime,
            @RequestParam(defaultValue = "period") String type,
            @RequestParam(defaultValue = "day") String method,
            HttpSession session) {
        Map<String, Object> params = new HashMap<>();
        params.put("page"       , 1);
        params.put("size"       , Integer.MAX_VALUE);
        params.put("counselorId", Login.getLoggedInUser(session).getId());
        if (fromTime != null && !fromTime.trim().isEmpty()) {
            params.put("fromTime"   , fromTime);
        }
        if (toTime != null && !toTime.trim().isEmpty()) {
            params.put("toTime"     , toTime);
        }
        params.put("statuses", new Short[] { 1, 2, 3 });

        Page<CounselingSessionEntity> sessionEntities = counselingSessionService.filter(params);
        return SUCCESS(formatData(sessionEntities.getContent(), type, method));
    }

    @RequestMapping(value = "/report/excel", method = RequestMethod.POST)
    public void exportExcel(
            @RequestParam Map<String, Object> params,
            HttpServletRequest request,
            HttpServletResponse response
    ) {

        List<String> headers        = Arrays.asList(String.valueOf(params.get("headers")).split(","));
        List<String> _bodies        = Arrays.asList(String.valueOf(params.get("bodies")).split(";"));
        List<List<String>> bodies   = new ArrayList<>();

        for (String body : _bodies) {
            bodies.add(Arrays.asList(body.split(":")));
        }

        ExcelUtils.createExcelFile(request, response, "Statistics", headers, bodies);
    }

    private Object formatData(List<CounselingSessionEntity> data, String type, String method) {

        if ("period".equalsIgnoreCase(type)) {
            switch (method) {
                case "day":
                    return formatDataByPeriodDay(data);
                case "week":
                    return formatDataByPeriodWeek(data);
                case "month":
                default:
                    return formatDataByPeriodMonth(data);
            }
        } else if ("date".equalsIgnoreCase(type)) {
            return formatDataByDate(data);
        } else {
            return formatDataByTime(data);
        }
    }

    private Map<String, CounselingSessionDto> formatDataByPeriodDay(List<CounselingSessionEntity> data) {
        Map<String, CounselingSessionDto> result = new TreeMap<>();

        if (!data.isEmpty()) {
            for (CounselingSessionEntity cse : data) {
                String key = formatterDate.format(cse.getStartTime());
                Short type = cse.getCounselType();
                if (result.containsKey(key)) {
                    CounselingSessionDto session = result.get(key);
                    if (cse.getPaymentHistory() != null) {
                        session.setMoney((cse.getPaymentHistory().getCoin() != null ? cse.getPaymentHistory().getCoin() : 0) + session.getMoney());
                    }
                    session.setCounseled(session.getCounseled() + 1);
                    if (type.equals((short) 2)) session.setReservation(session.getReservation() + 1);
                    session.setTime(session.getTime() + 30);

                    result.put(key, session);
                } else {
                    CounselingSessionDto session = new CounselingSessionDto();
                    session.setKey(key);
                    if (cse.getPaymentHistory() != null) {
                        session.setMoney(cse.getPaymentHistory().getCoin() != null ? cse.getPaymentHistory().getCoin() : 0);
                    }
                    session.setCounseled(1);
                    if (type.equals((short) 2)) session.setReservation(1);
                    session.setTime(30);

                    result.put(key, session);
                }
            }
        }
        return result;
    }

    private Map<String, CounselingSessionDto> formatDataByPeriodWeek(List<CounselingSessionEntity> data) {
        Map<String, CounselingSessionDto> result = new TreeMap<>();

        if (!data.isEmpty()) {
            for (CounselingSessionEntity cse : data) {
                Calendar instance = Calendar.getInstance();
                instance.setTime(cse.getStartTime());
                instance.set(Calendar.DAY_OF_WEEK, 2);

                StringBuilder keyBuilder = new StringBuilder(formatterDateWeek.format(instance.getTime()));
                keyBuilder.append(" ~ ");

                instance.add(Calendar.WEEK_OF_MONTH, 1);
                instance.set(Calendar.DAY_OF_WEEK, 1);

                keyBuilder.append(formatterDateWeek.format(instance.getTime()));
                String key = keyBuilder.toString();

                Short type = cse.getCounselType();
                if (result.containsKey(key)) {
                    CounselingSessionDto session = result.get(key);
                    if (cse.getPaymentHistory() != null) {
                        session.setMoney((cse.getPaymentHistory().getCoin() != null ? cse.getPaymentHistory().getCoin() : 0) + session.getMoney());
                    }
                    session.setCounseled(session.getCounseled() + 1);
                    if (type.equals((short) 2)) session.setReservation(session.getReservation() + 1);
                    session.setTime(session.getTime() + 30);

                    result.put(key, session);
                } else {
                    CounselingSessionDto session = new CounselingSessionDto();
                    session.setKey(key);
                    if (cse.getPaymentHistory() != null) {
                        session.setMoney(cse.getPaymentHistory().getCoin() != null ? cse.getPaymentHistory().getCoin() : 0);
                    }
                    session.setCounseled(1);
                    if (type.equals((short) 2)) session.setReservation(1);
                    session.setTime(30);

                    result.put(key, session);
                }
            }
        }
        return result;
    }

    private Map<String, CounselingSessionDto> formatDataByPeriodMonth(List<CounselingSessionEntity> data) {
        Map<String, CounselingSessionDto> result = new TreeMap<>();

        if (!data.isEmpty()) {
            for (CounselingSessionEntity cse : data) {
                String key = formatterDateMonth.format(cse.getStartTime());
                Short type = cse.getCounselType();
                if (result.containsKey(key)) {
                    CounselingSessionDto session = result.get(key);
                    if (cse.getPaymentHistory() != null) {
                        session.setMoney((cse.getPaymentHistory().getCoin() != null ? cse.getPaymentHistory().getCoin() : 0) + session.getMoney());
                    }
                    session.setCounseled(session.getCounseled() + 1);
                    if (type.equals((short) 2)) session.setReservation(session.getReservation() + 1);
                    session.setTime(session.getTime() + 30);

                    result.put(key, session);
                } else {
                    CounselingSessionDto session = new CounselingSessionDto();
                    session.setKey(key);
                    if (cse.getPaymentHistory() != null) {
                        session.setMoney(cse.getPaymentHistory().getCoin() != null ? cse.getPaymentHistory().getCoin() : 0);
                    }
                    session.setCounseled(1);
                    if (type.equals((short) 2)) session.setReservation(1);
                    session.setTime(30);

                    result.put(key, session);
                }
            }
        }
        return result;
    }

    private Object formatDataByDate(List<CounselingSessionEntity> data) {
        Map<String, CounselingSessionDto> result = new LinkedHashMap<>();
        List<Integer> weekDays = Arrays.asList(2, 3, 4, 5, 6, 7, 1);
        List<String> weekNames = Arrays.asList("월", "화", "수", "목", "금", "토", "일");

        for (Integer day : weekDays) {
            CounselingSessionDto session = new CounselingSessionDto();
            session.setKey(day.toString());
            session.setMoney(0);
            session.setCounseled(0);
            session.setTime(0);
            session.setReservation(0);
            result.put(weekNames.get(weekDays.indexOf(day)), session);
        }

        Calendar calendar = Calendar.getInstance();
        if (!data.isEmpty()) {
            for (CounselingSessionEntity cse : data) {
                calendar.setTime(cse.getStartTime());
                int day     = calendar.get(Calendar.DAY_OF_WEEK);
                String key  = weekNames.get(weekDays.indexOf(day));
                Short type  = cse.getCounselType();

                if (result.containsKey(key)) {
                    CounselingSessionDto session = result.get(key);
                    if (cse.getPaymentHistory() != null) {
                        session.setMoney((cse.getPaymentHistory().getCoin() != null ? cse.getPaymentHistory().getCoin() : 0) + session.getMoney());
                    }
                    session.setCounseled(session.getCounseled() + 1);
                    if (type.equals((short) 2)) session.setReservation(session.getReservation() + 1);
                    session.setTime(session.getTime() + 30);

                    result.put(key, session);
                } else {
                    CounselingSessionDto session = new CounselingSessionDto();
                    session.setKey(key);
                    if (cse.getPaymentHistory() != null) {
                        session.setMoney(cse.getPaymentHistory().getCoin() != null ? cse.getPaymentHistory().getCoin() : 0);
                    }
                    session.setCounseled(1);
                    if (type.equals((short) 2)) session.setReservation(1);
                    session.setTime(30);

                    result.put(key, session);
                }
            }
        }
        return result;
    }

    private Object formatDataByTime(List<CounselingSessionEntity> data) {
        Map<String, CounselingSessionDto> result = new TreeMap<>();
        List<Integer> hours = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23);
        for (Integer hour : hours) {
            CounselingSessionDto session = new CounselingSessionDto();
            session.setKey(hour.toString());
            session.setMoney(0);
            session.setCounseled(0);
            session.setTime(0);
            session.setReservation(0);
            result.put(hour.toString(), session);
        }

        Calendar calendar = Calendar.getInstance();
        if (!data.isEmpty()) {
            for (CounselingSessionEntity cse : data) {
                calendar.setTime(cse.getStartTime());
                int hour    = calendar.get(Calendar.HOUR_OF_DAY);
                String key  = hour + "";
                Short type  = cse.getCounselType();

                if (result.containsKey(key)) {
                    CounselingSessionDto session = result.get(key);
                    if (cse.getPaymentHistory() != null) {
                        session.setMoney((cse.getPaymentHistory().getCoin() != null ? cse.getPaymentHistory().getCoin() : 0) + session.getMoney());
                    }
                    session.setCounseled(session.getCounseled() + 1);
                    if (type.equals((short) 2)) session.setReservation(session.getReservation() + 1);
                    session.setTime(session.getTime() + 30);

                    result.put(key, session);
                } else {
                    CounselingSessionDto session = new CounselingSessionDto();
                    session.setKey(key);
                    if (cse.getPaymentHistory() != null) {
                        session.setMoney(cse.getPaymentHistory().getCoin() != null ? cse.getPaymentHistory().getCoin() : 0);
                    }
                    session.setCounseled(1);
                    if (type.equals((short) 2)) session.setReservation(1);
                    session.setTime(30);

                    result.put(key, session);
                }
            }
        }
        return result;
    }

}
