package org.trams.hello.business.listener;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.trams.hello.bean.jpa.UserEntity;
import org.trams.hello.business.event.CounselorMessageAddedEvent;
import org.trams.hello.business.service.UserService;

/**
 * Created by bryanlee on 4/26/17.
 */
@Component
public class CounselorMessageAddedListener implements ApplicationListener<CounselorMessageAddedEvent> {

    private ConcurrentHashMap<Integer, Object> messageAdded = new ConcurrentHashMap<>();

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(CounselorMessageAddedEvent event) {
        try {
            Integer memberId = event.getMemberId();
            Integer counselorId = event.getCounselorId();
            Integer messageId = event.getMessageId();

            UserEntity member = userService.findOne(memberId);
            Map<String, Object> data = new HashMap<>();
            data.put("messageId", messageId);
            data.put("member"   , member.getFullname());

            messageAdded.put(counselorId, data);
        } catch (Exception e) {

        }
    }

    public ConcurrentHashMap<Integer, Object> getMessageAdded() {
        return messageAdded;
    }

    public void setMessageAdded(ConcurrentHashMap<Integer, Object> messageAdded) {
        this.messageAdded = messageAdded;
    }
}
