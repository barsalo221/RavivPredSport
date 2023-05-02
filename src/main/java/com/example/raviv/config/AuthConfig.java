//package com.example.raviv.config;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.lang.Nullable;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.util.WebUtils;
//
//@Configuration
//@EnableWebMvc
//public class AuthConfig implements WebMvcConfigurer {
//    public void addInterceptors(InterceptorRegistry registry){
//        registry.addInterceptor(new AuthInterceptor())
//                .addPathPatterns("/secured/**");
//
//    }
//    public class AuthInterceptor implements HandlerInterceptor{
//
//
//        public  void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler,@Nullable ModelAndView modelAndView){
//            String user = WebUtils.getCookie(req,"userName").getValue();
//            String password = WebUtils.getCookie(req,"password").getValue();
//            System.out.print(user);
//        }
//    }
//}
