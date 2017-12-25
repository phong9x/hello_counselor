package org.trams.hello.business.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by bryanlee on 4/21/17.
 */
public class CounselingSessionScheduleAddedEvent extends ApplicationEvent {

    private Integer memberId;
    private Integer counselorId;
    private Integer sessionId;

    public CounselingSessionScheduleAddedEvent(Object source, Integer memberId, Integer counselorId, Integer sessionId) {
        super(source);
        this.memberId = memberId;
        this.counselorId = counselorId;
        this.sessionId = sessionId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getCounselorId() {
        return counselorId;
    }

    public void setCounselorId(Integer counselorId) {
        this.counselorId = counselorId;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }
}
