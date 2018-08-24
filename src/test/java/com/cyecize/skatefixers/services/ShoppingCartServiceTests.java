package com.cyecize.skatefixers.services;

import com.cyecize.skatefixers.areas.products.entities.BaseProduct;
import com.cyecize.skatefixers.areas.products.entities.products.Product;
import com.cyecize.skatefixers.areas.products.services.BaseProductService;
import com.cyecize.skatefixers.areas.shoppingCart.repositories.ShoppingCartRepository;
import com.cyecize.skatefixers.areas.shoppingCart.services.ShoppingCartService;
import com.cyecize.skatefixers.areas.shoppingCart.services.ShoppingCartServiceImpl;
import com.cyecize.skatefixers.areas.shoppingCart.viewModels.ShoppingCartItem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartServiceTests {

    private static final int CART_ITEMS = 5;

    private static final String ITEMS_JSON = "{\"1\":1,\"2\":2,\"3\":3,\"4\":4,\"5\":5}";

    private ShoppingCartService shoppingCartService;

    private ShoppingCartRepository shoppingCartRepository = Mockito.mock(ShoppingCartRepository.class);

    private BaseProductService productService = Mockito.mock(BaseProductService.class);

    private List<ShoppingCartItem> shoppingCartItems;

    @Before
    public void onBefore(){
        shoppingCartItems = new ArrayList<>();
        for (int i = 1; i <= CART_ITEMS; i++) {
            BaseProduct product = new Product();
            product.setId((long)i);
            shoppingCartItems.add(new ShoppingCartItem(product, i));
        }
        shoppingCartService = new ShoppingCartServiceImpl(shoppingCartRepository, productService);

        Mockito.when(this.productService.findOneById(Mockito.anyLong())).thenAnswer((a)->{
           Product product = new Product();
           product.setId(a.getArgument(0));
           return product;
        });
    }

    @Test
    public void encodeShoppingCart_validShoppingCart_shouldParseToKVP_JSON(){
        Assert.assertEquals("values dont match", ITEMS_JSON, this.shoppingCartService.encodeShoppingCart(this.shoppingCartItems));
    }

    @Test(expected = NullPointerException.class)
    public void encodeShoppingCart_NullValue_ShouldThrowNullPointer(){
        this.shoppingCartService.encodeShoppingCart(null);
    }

    @Test
    public void encodeShoppingCart_emptyArr_ShouldReturnEmptyJson(){
        Assert.assertEquals("result not empty", "{}", this.shoppingCartService.encodeShoppingCart(new ArrayList<>()));
    }

    @Test
    public void parseShoppingCart_ValidJsonString_ReturnEntityRepresentation() throws Exception{
        List<ShoppingCartItem> items = this.shoppingCartService.parseShoppingCart(ITEMS_JSON);
        Assert.assertEquals("items size not equal", 5, items.size());
        for (int i = 0; i < this.shoppingCartItems.size(); i++) {
            ShoppingCartItem exp = this.shoppingCartItems.get(i);
            ShoppingCartItem act = items.get(i);
            Assert.assertEquals("id doesnt match", exp.getProduct().getId(), act.getProduct().getId());
            Assert.assertEquals("quantity doesnt match", exp.getQuantity(), act.getQuantity());
        }
    }

    @Test(expected = Exception.class)
    public void parseShoppingCart_InvalidJsonString_ShouldThrowException() throws Exception{
        String json = "{nonValidIneger:2,3:3}";
        this.shoppingCartService.parseShoppingCart(json);
    }
}
