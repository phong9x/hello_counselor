package org.trams.hello.business.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.trams.hello.business.event.FindPasswordEvent;
import org.trams.hello.common.Sms;

/**
 * Created by bryanlee on 4/21/17.
 */
@Component
public class FindPasswordListener implements ApplicationListener<FindPasswordEvent> {

    @Override
    public void onApplicationEvent(FindPasswordEvent event) {
        try {
            String phone    = event.getPhone();
            String tmpPwd   = event.getTmpPwd();
            Sms.send(phone, String.format("[Hello] 임시비밀번호는 [%s]입니다. 로그인 후 새 비밀번호로 재설정 해주세요.", tmpPwd));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
