package com.cyecize.skatefixers.areas.shoppingCart.services;

import com.cyecize.skatefixers.areas.language.Constants;
import com.cyecize.skatefixers.areas.products.entities.BaseProduct;
import com.cyecize.skatefixers.areas.products.services.BaseProductService;
import com.cyecize.skatefixers.areas.shoppingCart.entities.ShoppingCart;
import com.cyecize.skatefixers.areas.shoppingCart.repositories.ShoppingCartRepository;
import com.cyecize.skatefixers.areas.shoppingCart.viewModels.ShoppingCartItem;
import com.cyecize.skatefixers.areas.users.entities.User;
import com.cyecize.skatefixers.constants.WebConstants;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private static final String EMPTY_CART = "{}";

    private final ShoppingCartRepository shoppingCartRepository;

    private final BaseProductService productService;

    private List<ShoppingCartItem> currentCart;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, BaseProductService productService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productService = productService;
    }

    @Override
    public void clear() {
        this.currentCart = new ArrayList<>();
    }

    @Override
    public void mergeDbAndCookieCarts(User user) {
        ShoppingCart shoppingCart = user.getShoppingCart();
        if (shoppingCart == null) shoppingCart = this.createShoppingCart(user);
        List<ShoppingCartItem> userCart;
        try {
            userCart = this.parseShoppingCart(shoppingCart.getSerializedProducts());
        } catch (Exception e) {
            this.editShoppingCart(shoppingCart, EMPTY_CART);
            userCart = new ArrayList<>();
        }
        this.currentCart = this.mergeCarts(userCart, this.currentCart);
    }

    @Override
    public void addProduct(BaseProduct product, int quantity) {
        ShoppingCartItem item = this.currentCart.stream().filter(sc -> sc.getProduct().getId().equals(product.getId())).findFirst().orElse(null);
        if (item == null)
            this.currentCart.add(new ShoppingCartItem(product, quantity));
        else
            item.addQuantity(quantity);
    }

    @Override
    public void initCart(String cart) {
        if (cart == null) cart = EMPTY_CART;
        try {
            cart = URLDecoder.decode(cart, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            cart = EMPTY_CART;
        }
        List<ShoppingCartItem> shoppingCartItems;
        try {
            shoppingCartItems = this.parseShoppingCart(cart);
        } catch (Exception e) {
            shoppingCartItems = new ArrayList<>();
        }
        if(this.currentCart != null)
            this.currentCart = this.mergeCarts(shoppingCartItems, this.currentCart);
        else
            this.currentCart = shoppingCartItems;
    }

    @Override
    public void saveCart(HttpServletResponse response) {
        String encodedCart = this.encodeShoppingCart(this.currentCart);
        String cookieCart;
        try {
            cookieCart = URLEncoder.encode(encodedCart, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            cookieCart = EMPTY_CART;
        }
        Cookie cookie = new Cookie(WebConstants.COOKIE_CART_NAME, cookieCart);
        cookie.setPath("/");
        cookie.setMaxAge(Constants.COOKIE_MAX_AGE);
        response.addCookie(cookie);

        if(!this.isAuthenticated())
            return;
        User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.editShoppingCart(u.getShoppingCart() != null ? u.getShoppingCart() : this.createShoppingCart(u), encodedCart);
    }

    @Override
    public void removeProduct(Long productId) {
        this.currentCart = this.currentCart.stream().filter(ci -> !ci.getProduct().getId().equals(productId)).collect(Collectors.toList());
    }

    @Override
    public int getShoppingCartSize() {
        return this.currentCart.size();
    }

    @Override
    public List<ShoppingCartItem> getShoppingCart() {
        return this.currentCart;
    }

    private List<ShoppingCartItem> parseShoppingCart(String shoppingCartJson) throws Exception {
        try {
            List<ShoppingCartItem> shoppingCartItems = new ArrayList<>();
            Map productsMap = new Gson().fromJson(shoppingCartJson, Map.class);
            productsMap.forEach((k, v) -> {
                shoppingCartItems.add(new ShoppingCartItem(this.productService.findOneById(Long.parseLong(k + "")), (int) (Double.parseDouble(v + ""))));
            });
            return shoppingCartItems;
        } catch (Exception e) {
            throw new Exception("Error parsing shopping cart");
        }
    }

    private String encodeShoppingCart(List<ShoppingCartItem> shoppingCartItems) {
        Map<Long, Integer> prodQuanPair = new HashMap<>();
        for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
            prodQuanPair.put(shoppingCartItem.getProduct().getId(), shoppingCartItem.getQuantity());
        }
        return new Gson().toJson(prodQuanPair);
    }

    private ShoppingCart createShoppingCart(User user) {
        if(this.shoppingCartRepository.findShoppingCartByUser(user) != null)
            return this.shoppingCartRepository.findShoppingCartByUser(user);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCart.setSerializedProducts(EMPTY_CART);
        this.shoppingCartRepository.saveAndFlush(shoppingCart);
        return shoppingCart;
    }

    private void editShoppingCart(ShoppingCart shoppingCart, String cart) {
        if(cart.equals(shoppingCart.getSerializedProducts()))
            return;
        shoppingCart.setSerializedProducts(cart);
        this.shoppingCartRepository.saveAndFlush(shoppingCart);
    }

    private List<ShoppingCartItem> mergeCarts(List<ShoppingCartItem> cartItemsOne , List<ShoppingCartItem> cartItemsTwo){
        List<Long> cartOneProducts = cartItemsOne.stream().map(uc -> uc.getProduct().getId()).collect(Collectors.toList());
        for (ShoppingCartItem shoppingCartItem : cartItemsTwo) {
            if (!cartOneProducts.contains(shoppingCartItem.getProduct().getId()))
                cartItemsOne.add(shoppingCartItem);
        }
        return cartItemsOne;
    }

    private boolean isAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                !(SecurityContextHolder.getContext().getAuthentication()
                        instanceof AnonymousAuthenticationToken) ;
    }
}
