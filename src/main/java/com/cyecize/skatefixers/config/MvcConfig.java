package com.cyecize.skatefixers.config;

import com.cyecize.skatefixers.areas.language.interceptors.LocaleInterceptor;
import com.cyecize.skatefixers.areas.shoppingCart.interceptors.ShoppingCartInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    private final LocaleInterceptor localeInterceptor;

    private final ShoppingCartInterceptor shoppingCartInterceptor;

   // private final TwigInterceptor twigInterceptor;

    @Autowired
    public MvcConfig(LocaleInterceptor logInterceptor, ShoppingCartInterceptor shoppingCartInterceptor) {
        this.localeInterceptor = logInterceptor;
        this.shoppingCartInterceptor = shoppingCartInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.localeInterceptor);
        registry.addInterceptor(this.shoppingCartInterceptor);
      //  registry.addInterceptor(this.twigInterceptor);
    }
}