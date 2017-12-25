package org.trams.hello.common;

import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * Created by bryanlee on 05/01/2017.
 */
public class Sms {

    final static RestTemplate template = new RestTemplate();

    public static boolean send(String phone, String content) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("secret", "AimmedHello");
        params.add("title", "Hello");
        params.add("to", phone);
        params.add("message", content);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

        try {
            ResponseEntity<String> response = template.exchange(new URI("http://api.aimmed.co.kr/hello/sms.asp"), HttpMethod.POST, entity, String.class);
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
    
    

}
