package org.trams.hello.web.common.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.trams.hello.rest.common.AbstractRestController;

public class ServletUtils extends AbstractRestController{

    public static void response(HttpServletResponse response, int code, String message) throws IOException {
        response.setStatus(code);
        response.setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        JSONObject js =new JSONObject();
        js.put("status", 100);
        js.put("mesage", message);
        js.put("data", null);
        System.out.println(js.toString());
        PrintWriter writer = response.getWriter();
        writer.write(js.toString());
        writer.close();
    }

}
