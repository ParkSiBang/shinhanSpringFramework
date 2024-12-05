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
		// ��û �����ϱ� ��
		HttpServletRequest req = (HttpServletRequest) request;

		String contextPath = req.getServletContext().getContextPath();
		String uri = req.getRequestURI();

		uri = uri.substring(contextPath.length());
		if (!uri.equals("/auth/login.do") && !uri.contains("/rest")) {
			HttpSession session = req.getSession();
			MemberDTO member = (MemberDTO) session.getAttribute("loginMember");
			if (member == null) {
				System.out.println("�α��� ����");
				HttpServletResponse res = (HttpServletResponse) response;
				res.sendRedirect(contextPath + "/auth/login.do");
				return;
			}
		}

		chain.doFilter(request, response);
		// ��û ���� ��
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
