package org.trams.hello.business.service;

import java.util.Map;

/**
 * Created by bryanlee on 19/01/2017.
 */
public interface RtcService {

    /**
     *
     * @param params
     * @return
     */
    Map<String, Object> authenticate(Map<String, Object> params);

    /**
     *
     * @param params
     * @return
     */
    Map<String, Object> createRoom(Map<String, Object> params);

    /**
     *
     * @param params
     * @return
     */
    Map<String, Object> closeRoom(Map<String, Object> params) throws RuntimeException;

    /**
     *
     * @param params
     * @return
     */
    Map<String, Object> createParticipant(Map<String, Object> params) throws RuntimeException;

}
