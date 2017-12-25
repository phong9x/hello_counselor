package org.trams.hello.business.listener;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.trams.hello.bean.jpa.CounselorEntity;
import org.trams.hello.bean.jpa.UserEntity;
import org.trams.hello.business.event.PsyTestPaymentAddedEvent;
import org.trams.hello.business.service.CounselingSessionService;
import org.trams.hello.business.service.CounselorService;
import org.trams.hello.business.service.UserService;
import org.trams.hello.common.Sms;

/**
 * Created by bryanlee on 4/21/17.
 */
@Component
public class PsyTestPaymentAddedListener implements ApplicationListener<PsyTestPaymentAddedEvent> {

    private ConcurrentHashMap<Integer, Object> psyPayment = new ConcurrentHashMap<>();

    @Autowired
    private UserService userService;
    @Autowired
    private CounselorService counselorService;
    @Autowired
    private CounselingSessionService counselingSessionService;

    @Override
    public void onApplicationEvent(PsyTestPaymentAddedEvent event) {
        try {
            Integer memberId    = event.getMemberId();
            Integer counselorId = event.getCounselorId();
            Integer psyId       = event.getPsyId();
            String  testSite    = event.getTestSite();

            UserEntity      member      = userService.findOne(memberId);
            CounselorEntity counselor   = counselorService.findByOne(counselorId);

            Sms.send(counselor.getUser().getPhone(), String.format("[Hello] [%s]님 심리검사 결제 완료, %s 사이트에서 인증코드 발송해주세요.", member.getFullname(), testSite));

            Map<String, Object> data = new HashMap<>();
            data.put("psyId"    , psyId);
            data.put("member"   , member.getFullname());
            psyPayment.put(counselorId, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ConcurrentHashMap<Integer, Object> getPsyPayment() {
        return psyPayment;
    }

    public void setPsyPayment(ConcurrentHashMap<Integer, Object> psyPayment) {
        this.psyPayment = psyPayment;
    }
}
