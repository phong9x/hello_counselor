package org.trams.hello.business.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by bryanlee on 4/26/17.
 */
public class CounselorMessageAddedEvent extends ApplicationEvent {

    private Integer memberId;
    private Integer counselorId;
    private Integer messageId;

    public CounselorMessageAddedEvent(Object source, Integer memberId, Integer counselorId, Integer messageId) {
        super(source);
        this.memberId = memberId;
        this.counselorId = counselorId;
        this.messageId = messageId;
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

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }
}
