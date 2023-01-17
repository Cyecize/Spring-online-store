package com.cyecize.skatefixers.services;

import com.cyecize.skatefixers.areas.products.entities.BaseProduct;
import com.cyecize.skatefixers.areas.products.entities.products.Product;
import com.cyecize.skatefixers.areas.products.services.BaseProductService;
import com.cyecize.skatefixers.areas.shoppingCart.repositories.ShoppingCartRepository;
import com.cyecize.skatefixers.areas.shoppingCart.services.ShoppingCartService;
import com.cyecize.skatefixers.areas.shoppingCart.services.ShoppingCartServiceImpl;
import com.cyecize.skatefixers.areas.shoppingCart.viewModels.ShoppingCartItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ShoppingCartServiceTests {

    private static final int CART_ITEMS = 5;

    private static final String ITEMS_JSON = "{\"1\":1,\"2\":2,\"3\":3,\"4\":4,\"5\":5}";

    private ShoppingCartService shoppingCartService;

    private ShoppingCartRepository shoppingCartRepository = Mockito.mock(ShoppingCartRepository.class);

    private BaseProductService productService = Mockito.mock(BaseProductService.class);

    private List<ShoppingCartItem> shoppingCartItems;

    @BeforeEach
    public void onBefore() {
        shoppingCartItems = new ArrayList<>();
        for (int i = 1; i <= CART_ITEMS; i++) {
            BaseProduct product = new Product();
            product.setId((long) i);
            shoppingCartItems.add(new ShoppingCartItem(product, i));
        }
        shoppingCartService = new ShoppingCartServiceImpl(shoppingCartRepository, productService);

        Mockito.when(this.productService.findOneById(Mockito.anyLong())).thenAnswer((a) -> {
            Product product = new Product();
            product.setId(a.getArgument(0));
            return product;
        });
    }

    @Test
    public void encodeShoppingCart_validShoppingCart_shouldParseToKVP_JSON() {
        assertEquals(ITEMS_JSON, this.shoppingCartService.encodeShoppingCart(this.shoppingCartItems), "values dont match");
    }

    @Test
    public void encodeShoppingCart_NullValue_ShouldThrowNullPointer() {
        assertThrows(NullPointerException.class, () -> this.shoppingCartService.encodeShoppingCart(null));
    }

    @Test
    public void encodeShoppingCart_emptyArr_ShouldReturnEmptyJson() {
        assertEquals("{}", this.shoppingCartService.encodeShoppingCart(new ArrayList<>()), "result not empty");
    }

    @Test
    public void parseShoppingCart_ValidJsonString_ReturnEntityRepresentation() throws Exception {
        List<ShoppingCartItem> items = this.shoppingCartService.parseShoppingCart(ITEMS_JSON);
        assertEquals(5, items.size(), "items size not equal");
        for (int i = 0; i < this.shoppingCartItems.size(); i++) {
            ShoppingCartItem exp = this.shoppingCartItems.get(i);
            ShoppingCartItem act = items.get(i);
            assertEquals(exp.getProduct().getId(), act.getProduct().getId(), "id doesnt match");
            assertEquals(exp.getQuantity(), act.getQuantity(), "quantity doesnt match");
        }
    }

    @Test
    public void parseShoppingCart_InvalidJsonString_ShouldThrowException() throws Exception {
        String json = "{nonValidIneger:2,3:3}";
        assertThrows(Exception.class, () -> this.shoppingCartService.parseShoppingCart(json));
    }
}
