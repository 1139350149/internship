package com.relocation.test.filter;

import com.relocation.test.annotation.Token;
import com.relocation.test.repository.EmployeeRepository;
import com.relocation.test.util.JsonUtil;
import com.relocation.test.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    private EmployeeRepository employeeRepository;

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
//        if (!(handler instanceof HandlerMethod)) {
//            return true;
//        }
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        Method method = handlerMethod.getMethod();
//
//        // 判断接口是否需要登录
//        Token methodAnnotation = method.getAnnotation(Token.class);
//        // 有 @LoginRequired 注解，需要认证
//        if (methodAnnotation != null) {
//            // 执行认证
//            String token = request.getHeader("Authorization");  // 从 http 请求头中取出 token
//            if (token == null) {
//                throw new RuntimeException("无token，请重新登录");
//            }
//            return TokenUtil.verify(token, employeeRepository);
//        }
//        else {
//            return false;
//        }
        return true;
    }
}
