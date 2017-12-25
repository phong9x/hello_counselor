package org.trams.hello.web.common;

import org.trams.hello.bean.UserItem;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class Login {
	
	private static Map<Integer, HttpSession> counselorLoggedIn = new HashMap<>();
	
	public static void saveLoggedInUser(UserItem user, HttpSession session) {
		session.setAttribute("loggedInUser", user);
		counselorLoggedIn.put(user.getId(), session);
	}
	
	public static void removeLoggedInUser(HttpSession session) {
		try {
			Integer loggedInUser = ((UserItem) session.getAttribute("loggedInUser")).getId();

			counselorLoggedIn.remove(loggedInUser);
			session.removeAttribute("loggedInUser");
		} catch (Exception e) {

		}
	}
	
	public static UserItem getLoggedInUser(HttpSession session) {
		return (UserItem) session.getAttribute("loggedInUser");
	}

	public static Map<Integer, HttpSession> getCounselorLoggedIn() {
		return counselorLoggedIn;
	}

}