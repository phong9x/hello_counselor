package org.trams.hello.business.service.impl;

import java.net.URI;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.trams.hello.business.service.RtcService;
import org.trams.hello.web.common.utils.JsonUtils;

/**
 * Created by bryanlee on 19/01/2017.
 */
@Service
@SuppressWarnings({"unchecked"})
public class RtcServiceGooroomeeImpl implements RtcService {

  private static final String API_SERVER      = "https://facetalk.hellowithyou.com:9443";
  private static final String SERVICE_SERVER  = "https://facetalk.hellowithyou.com:8443";
  private static final String TOKEN           = "1970d100a51ff971019b13dd931dab89d71848a168ee4zdq13";

//  private static final String API_SERVER      = "https://api.gooroomee.com";
//  private static final String SERVICE_SERVER  = "https://gooroomee.com";
//  private static final String TOKEN           = "166911e6711545118b3b16c1116bcc1a0c2195ca1d7e3185f6";

    private static final String RESULT_CODE_OK  = "GRM_200";
    private static final String API_CREATE_ROOM_URL     = API_SERVER + "/api/v1/room";
    private static final String API_CLOSE_ROOM_URL      = API_SERVER + "/api/v1/room/{roomId}";
    private static final String API_CREATE_PARTICIPANT  = API_SERVER + "/api/v1/room/user/otp";

    private static final RestTemplate template;
    private static final HttpHeaders headers;
    private static final HttpHeaders headerFormUrlEncoded;

    static {
        template    = new RestTemplate();
        headers     = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("X-GRM-AuthToken", TOKEN);

        headerFormUrlEncoded = new HttpHeaders();
        headerFormUrlEncoded.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headerFormUrlEncoded.add("X-GRM-AuthToken", TOKEN);
    }

    @Override
    public Map<String, Object> authenticate(Map<String, Object> params) {
        return null;
    }

    @Override
    public Map<String, Object> createRoom(Map<String, Object> params) throws RuntimeException {
        try {
            HttpEntity<String> entity = new HttpEntity<>(JsonUtils.toString(params), headers);

            ResponseEntity<LinkedHashMap> exchange = template.exchange(new URI(API_CREATE_ROOM_URL), HttpMethod.POST, entity, LinkedHashMap.class);

            if (!exchange.getStatusCode().equals(HttpStatus.OK)) {
                throw new RuntimeException(exchange.getStatusCode().getReasonPhrase());
            } else {
                LinkedHashMap body  = exchange.getBody();
                String resultCode   = String.valueOf(body.get("resultCode"));

                if (!resultCode.equals(RESULT_CODE_OK)) {
                    throw new RuntimeException(String.valueOf(body.get("description")));
                }

                return (Map<String, Object>) body.get("data");
            }

        } catch (Exception e) {
            throw new RuntimeException("Create room failed, caused by: " + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> closeRoom(Map<String, Object> params) throws RuntimeException {
        try {
            HttpEntity<?> entity = new HttpEntity<>(headers);

            ResponseEntity<LinkedHashMap> exchange = template.exchange(new URI(API_CLOSE_ROOM_URL.replace("{roomId}", String.valueOf(params.get("roomId")))), HttpMethod.DELETE, entity, LinkedHashMap.class);

            if (!exchange.getStatusCode().equals(HttpStatus.OK)) {
                throw new RuntimeException(exchange.getStatusCode().getReasonPhrase());
            } else {
                LinkedHashMap body  = exchange.getBody();
                String resultCode   = String.valueOf(body.get("resultCode"));

                if (!resultCode.equals(RESULT_CODE_OK)) {
                    throw new RuntimeException(String.valueOf(body.get("description")));
                }

                return body;
            }

        } catch (Exception e) {
            throw new RuntimeException("Close room failed, caused by: " + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> createParticipant(Map<String, Object> params) throws RuntimeException {
        try {
            MultiValueMap<String, Object> participantParams = new LinkedMultiValueMap<>(2);
            participantParams.add("roomId"     , params.get("roomId"));
            participantParams.add("username"   , params.get("username"));
            participantParams.add("roleId"     , params.get("roleId"));

            HttpEntity<MultiValueMap> entity = new HttpEntity<>(participantParams, headerFormUrlEncoded);

            ResponseEntity<LinkedHashMap> exchange = template.exchange(new URI(API_CREATE_PARTICIPANT), HttpMethod.POST, entity, LinkedHashMap.class);

            if (!exchange.getStatusCode().equals(HttpStatus.OK)) {
                throw new RuntimeException(exchange.getStatusCode().getReasonPhrase());
            } else {
                LinkedHashMap body  = exchange.getBody();
                String resultCode   = String.valueOf(body.get("resultCode"));

                if (!resultCode.equals(RESULT_CODE_OK)) {
                    throw new RuntimeException(String.valueOf(body.get("description")));
                }

                return (Map<String, Object>) body.get("data");
            }

        } catch (Exception e) {
            throw new RuntimeException("Create participant failed, caused by: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            HttpEntity<String> entity = new HttpEntity<>(JsonUtils.toString(Collections.singletonMap("roomTitle", "Trams Test")), headers);

            ResponseEntity<LinkedHashMap> exchange = template.exchange(new URI(API_CREATE_ROOM_URL), HttpMethod.POST, entity, LinkedHashMap.class);

            if (!exchange.getStatusCode().equals(HttpStatus.OK)) {
                throw new RuntimeException(exchange.getStatusCode().getReasonPhrase());
            } else {
                LinkedHashMap body = exchange.getBody();
                String resultCode = String.valueOf(body.get("resultCode"));

                if (!resultCode.equals(RESULT_CODE_OK)) {
                    throw new RuntimeException(String.valueOf(body.get("description")));
                }

                System.out.println(JsonUtils.toString(body));
            }

        } catch (Exception e) {
            throw new RuntimeException("Create room failed, caused by: " + e.getMessage());
        }
    }

}
