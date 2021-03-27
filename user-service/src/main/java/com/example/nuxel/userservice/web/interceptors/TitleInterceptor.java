package com.example.nuxel.userservice.web.interceptors;


import com.example.nuxel.userservice.anotations.PageTitle;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TitleInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        if(modelAndView == null){
            modelAndView = new ModelAndView();
        } else {
            if(handler instanceof HandlerMethod){
                PageTitle methodAnnotation = ((HandlerMethod) handler).getMethodAnnotation(PageTitle.class);

                if(methodAnnotation != null){
                    modelAndView.addObject("title",
                            methodAnnotation.value());
                }
            }
        }
    }
}
