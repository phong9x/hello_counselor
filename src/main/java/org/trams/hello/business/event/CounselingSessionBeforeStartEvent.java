package org.trams.hello.business.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by bryanlee on 4/21/17.
 */
public class CounselingSessionBeforeStartEvent extends ApplicationEvent {

    private String member;
    private String phoneMember;
    private String phoneCounselor;

    public CounselingSessionBeforeStartEvent(Object source, String member, String phoneMember, String phoneCounselor) {
        super(source);
        this.member = member;
        this.phoneMember = phoneMember;
        this.phoneCounselor = phoneCounselor;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getPhoneMember() {
        return phoneMember;
    }

    public void setPhoneMember(String phoneMember) {
        this.phoneMember = phoneMember;
    }

    public String getPhoneCounselor() {
        return phoneCounselor;
    }

    public void setPhoneCounselor(String phoneCounselor) {
        this.phoneCounselor = phoneCounselor;
    }
}
