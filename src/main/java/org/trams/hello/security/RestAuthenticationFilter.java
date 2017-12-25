package org.trams.hello.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.trams.hello.bean.UserItem;
import org.trams.hello.web.common.Login;


@Service
public class RestAuthenticationFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(true);
		String path = ((HttpServletRequest) request).getRequestURI();
		
		if (httpRequest.isRequestedSessionIdFromURL()) {
			if (session != null) {
				session.invalidate();
			}
		}

		if (path.startsWith("/auth") && !path.startsWith("/auth/identity")) {
			UserItem user = Login.getLoggedInUser(session);
			if (user == null) {
				((HttpServletResponse) response).sendRedirect("/");
				return;
			}
		}

		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
}
