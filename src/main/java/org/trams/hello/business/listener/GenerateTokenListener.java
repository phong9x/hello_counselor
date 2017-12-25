package org.trams.hello.business.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.trams.hello.business.event.GenerateTokenEvent;
import org.trams.hello.common.Sms;

/**
 * Created by bryanlee on 4/21/17.
 */
@Component
public class GenerateTokenListener implements ApplicationListener<GenerateTokenEvent> {

    @Override
    public void onApplicationEvent(GenerateTokenEvent event) {
        try {
            String phone = event.getPhone();
            String token = event.getToken();
            Sms.send(phone, String.format("[Hello] 본인확인 인증번호는 [%s]입니다. 정확히 입력해주세요.", token));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
