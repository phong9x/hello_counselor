package org.trams.hello.rest.model.enumeration;

/**
 * Created by bryanlee on 05/01/2017.
 */
public enum LoginType {

    NORMAL(0), DORMANT(1), BUSINESS(2), TEMPORARY_PASSWORD(3);

    int code;

    LoginType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
