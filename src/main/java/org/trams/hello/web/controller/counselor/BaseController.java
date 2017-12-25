package org.trams.hello.web.controller.counselor;

import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.trams.hello.bean.UserItem;
import org.trams.hello.web.common.Login;

public class BaseController {
	
	@Autowired
	private HttpServletRequest request;

	@ModelAttribute(value = "requestUri")
	protected String getView() {
		return request.getRequestURI();
	}

	@ModelAttribute(value = "requestUrl")
	protected String getUrl() {
		StringBuilder sb = new StringBuilder(request.getRequestURI());
		sb.append("?_=").append(new Date().getTime());

		Iterator iterator = request.getParameterMap().keySet().iterator();
		String key,val;
		while (iterator.hasNext()) {
			key = String.valueOf(iterator.next());
			if (key.equals("_")) continue;
			val = request.getParameter(key);
			sb.append("&").append(key).append("=").append(val);
		}
		return sb.toString();
	}

	@ModelAttribute(value = "userItem")
	protected UserItem getCounselor() {
		return Login.getLoggedInUser(request.getSession(true));
	}

	protected ResponseEntity<?> SUCCESS() {
		return new ResponseEntity<>(HttpStatus.OK);
	}

	protected ResponseEntity<?> SUCCESS(Object data) {
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	protected ResponseEntity<?> FAIL(Object data) {
		return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
	}

	protected ResponseEntity<?> ERROR() {
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
