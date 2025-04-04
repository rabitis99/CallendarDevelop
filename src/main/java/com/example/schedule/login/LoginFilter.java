package com.example.schedule.login;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

public class LoginFilter implements Filter {
    private static final String[] whiteList={"/home/login","/home/logout","/authors"};


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest=(HttpServletRequest) servletRequest;
        String requestURL=httpServletRequest.getRequestURI();

        HttpServletResponse httpServletResponse=(HttpServletResponse) servletResponse;

        HttpSession httpSession=httpServletRequest.getSession(false);

        if (!IsWhiteList(requestURL)) {
            if (httpSession == null || httpSession.getAttribute(Const.LOGIN_USER) == null) {
                httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
                httpServletResponse.setContentType("application/json");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write("{\"error\": \"로그인이 필요합니다.\"}");
                return;
            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
    private boolean IsWhiteList(String requestURL ){

        //보고 하자
        return PatternMatchUtils.simpleMatch(whiteList,requestURL);
    }



}
