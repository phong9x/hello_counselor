package org.trams.hello.business.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by bryanlee on 4/21/17.
 */
public class FindPasswordEvent extends ApplicationEvent {

    private String phone;
    private String tmpPwd;

    public FindPasswordEvent(Object source, String phone, String tmpPwd) {
        super(source);
        this.phone = phone;
        this.tmpPwd = tmpPwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTmpPwd() {
        return tmpPwd;
    }

    public void setTmpPwd(String tmpPwd) {
        this.tmpPwd = tmpPwd;
    }
}
