package com.shinhan.myapp.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shinhan.myapp.vo.MemberDTO;

@WebFilter("*.do")
public class LoginCheckFilter implements Filter {

	public LoginCheckFilter() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 요청 수행하기 전
		HttpServletRequest req = (HttpServletRequest) request;

		String contextPath = req.getServletContext().getContextPath();
		String uri = req.getRequestURI();

		uri = uri.substring(contextPath.length());
		if (!uri.equals("/auth/login.do") && !uri.contains("/rest")) {
			HttpSession session = req.getSession();
			MemberDTO member = (MemberDTO) session.getAttribute("loginMember");
			if (member == null) {
				System.out.println("로그인 안함");
				HttpServletResponse res = (HttpServletResponse) response;
				res.sendRedirect(contextPath + "/auth/login.do");
				return;
			}
		}

		chain.doFilter(request, response);
		// 요청 수행 후
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
