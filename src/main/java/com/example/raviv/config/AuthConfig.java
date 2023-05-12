package com.example.raviv.config;

import com.example.raviv.controller.UserController;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.WebUtils;


@EnableWebMvc
@Configuration
@CrossOrigin(value = "http://localhost:3000/" ,allowCredentials = "true")
public class AuthConfig implements WebMvcConfigurer {

    @Autowired
    private UserController userController;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("/secured/**");

    }

    public class AuthInterceptor implements HandlerInterceptor {
        public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) {
            if (!req.getMethod().equals(HttpMethod.OPTIONS.toString())) {
                Cookie userNameC = WebUtils.getCookie(req, "userName");
                Cookie passwordC = WebUtils.getCookie(req, "password");
                if (passwordC == null || userNameC == null) {
                    return false;
                }
//            System.out.println("cookie is set: "+userNameC.getValue());
                return userController.authenticate(userNameC.getValue(), passwordC.getValue());
            }
            return true;
        }

    }
}