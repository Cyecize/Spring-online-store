package com.cyecize.skatefixers.areas.shoppingCart.interceptors;

import com.cyecize.skatefixers.areas.shoppingCart.services.ShoppingCartService;
import com.cyecize.skatefixers.constants.WebConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Component
public class ShoppingCartInterceptor  extends HandlerInterceptorAdapter {

    private final ShoppingCartService shoppingCartService;


    @Autowired
    public ShoppingCartInterceptor(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!(handler instanceof HandlerMethod))
            return true;
        this.shoppingCartService.initCart(this.getCookie(request));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        if(!(handler instanceof HandlerMethod))
            return ;
        this.shoppingCartService.saveCart(response);
    }

    private String getCookie(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return null;
        }
        Cookie cookie = Arrays.stream(request.getCookies()).filter(c -> c.getName().equals(WebConstants.COOKIE_CART_NAME))
                .findFirst().orElse(null);
        if (cookie == null) {
            return null;
        }

        return cookie.getValue();
    }
}
