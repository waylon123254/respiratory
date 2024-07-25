package com.example.springboot.Exception;

import com.example.springboot.Common.NoAuth;
import com.example.springboot.Common.ResponseEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Component
//public class JwtInterceptor implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if (!(handler instanceof HandlerInterceptor)){
//            return  true;
//        }
//       NoAuth noAuth = ((HandlerMethod) handler).getMethodAnnotation(NoAuth.class);
//       if (null!=noAuth)
//       {
//           return  true;
//       }
//       String token =request.getHeader("token");
//       if (StringUtils.isBlank(token)){
//          throw new ServiceException(ResponseEnum.UNAUTHORIZED.getCode(),ResponseEnum.UNAUTHORIZED.getMessage());
//       }
//        return HandlerInterceptor.super.preHandle(request, response, handler);
//    }
//}
