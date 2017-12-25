package org.trams.hello.business.listener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.trams.hello.bean.jpa.CounselingSessionEntity;
import org.trams.hello.bean.jpa.UserEntity;
import org.trams.hello.business.event.CounselingSessionScheduleAddedEvent;
import org.trams.hello.business.service.CounselingSessionService;
import org.trams.hello.business.service.UserService;
import org.trams.hello.common.Sms;

/**
 * Created by bryanlee on 4/21/17.
 */
@Component
public class CounselingSessionScheduleAddedListener implements ApplicationListener<CounselingSessionScheduleAddedEvent> {

    private ConcurrentHashMap<Integer, Object> counselorSessionSchedule = new ConcurrentHashMap<>();
    private static final DateFormat format = new SimpleDateFormat("MM-dd(E) HH:mm", Locale.KOREA);

    @Autowired
    private UserService userService;
    @Autowired
    private CounselingSessionService counselingSessionService;

    @Override
    public void onApplicationEvent(CounselingSessionScheduleAddedEvent event) {
        try {
            Integer counselorId = event.getCounselorId();
            Integer memberId    = event.getMemberId();
            Integer sessionId   = event.getSessionId();

            UserEntity              member  = userService.findOne(memberId);
            CounselingSessionEntity session = counselingSessionService.findOne(sessionId);

            Sms.send(member.getPhone(), String.format("[Hello] [%s]님 상담 예약 - %s에 상담 시작", member.getFullname(), format.format(session.getStartTime())));

            Map<String, Object> data = new HashMap<>();
            data.put("sessionId", sessionId);
            counselorSessionSchedule.putIfAbsent(counselorId, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ConcurrentHashMap<Integer, Object> getCounselorSessionSchedule() {
        return counselorSessionSchedule;
    }

    public void setCounselorSessionSchedule(ConcurrentHashMap<Integer, Object> counselorSessionSchedule) {
        this.counselorSessionSchedule = counselorSessionSchedule;
    }
}
