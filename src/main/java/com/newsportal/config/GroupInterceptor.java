package com.newsportal.config;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

public class GroupInterceptor extends HandlerInterceptorAdapter{

    //TODO implement logic for group access control
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String groupId = request.getParameter("id");
        return super.preHandle(request, response, handler);
    }
}
