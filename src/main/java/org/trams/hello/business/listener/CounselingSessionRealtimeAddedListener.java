package org.trams.hello.business.listener;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.trams.hello.bean.jpa.UserEntity;
import org.trams.hello.business.event.CounselingSessionRealtimeAddedEvent;
import org.trams.hello.business.service.UserService;
import org.trams.hello.common.Sms;

/**
 * Created by bryanlee on 4/21/17.
 */
@Component
public class CounselingSessionRealtimeAddedListener implements ApplicationListener<CounselingSessionRealtimeAddedEvent> {

    private ConcurrentHashMap<Integer, Object> counselorSessionRealtime = new ConcurrentHashMap<>();

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(CounselingSessionRealtimeAddedEvent event) {
        try {
            Integer userId      = event.getMemberId();
            Integer counselorId = event.getCounselorId();
            Integer sessionId   = event.getSessionId();

            UserEntity member = userService.findOne(userId);
            UserEntity counselor = userService.findOne(counselorId);

            Sms.send(counselor.getPhone(), String.format("[Hello] [%s]님 실시간 상담 요청 - 지금 바로 입장해주세요.", member.getFullname()));

            Map<String, Object> data = new HashMap<>();
            data.put("sessionId", sessionId);
            data.put("member"   , member.getFullname());
            counselorSessionRealtime.putIfAbsent(counselorId, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ConcurrentHashMap<Integer, Object> getCounselorSessionRealtime() {
        return counselorSessionRealtime;
    }

    public void setCounselorSessionRealtime(ConcurrentHashMap<Integer, Object> counselorSessionRealtime) {
        this.counselorSessionRealtime = counselorSessionRealtime;
    }
}
