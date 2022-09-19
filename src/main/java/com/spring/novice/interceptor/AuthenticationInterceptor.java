package com.spring.novice.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		/* session 객체를 가져옴 */
		HttpSession session = request.getSession();

		/* login처리를 담당하는 사용자 정보를 담고 있는 객체를 가져옴 */
		Object obj = session.getAttribute("sessionUser");
		
		/* 로그인이 안되있을 경우 */
		if (obj == null) {
			// 로그인이 안되어 있는 상태임으로 로그인 페이지로 되돌려보냄
			response.sendRedirect("../user/loginPage");
			
			/*컨트롤러 수행하지 않게끔 리턴 false로 반환*/
			return false; 			
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
}
