package com.cyecize.skatefixers.areas.users.eventListeners;
import com.cyecize.skatefixers.areas.products.services.BaseProductService;
import com.cyecize.skatefixers.areas.shoppingCart.services.ShoppingCartService;
import com.cyecize.skatefixers.areas.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class LoginListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

    private final UserService userService;

    private final ShoppingCartService shoppingCartService;

    @Autowired
    public LoginListener(UserService userService, ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event)
    {
        UserDetails userDetails = (UserDetails) event.getAuthentication().getPrincipal();
        this.shoppingCartService.mergeDbAndCookieCarts(this.userService.findOneByUsername(userDetails.getUsername()));
        System.out.println("Login event triggered");
    }
}