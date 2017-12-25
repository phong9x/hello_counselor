package org.trams.hello.business.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by bryanlee on 4/21/17.
 */
public class GenerateTokenEvent extends ApplicationEvent {

    private String phone;
    private String token;

    public GenerateTokenEvent(Object source, String phone, String token) {
        super(source);
        this.phone = phone;
        this.token = token;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
