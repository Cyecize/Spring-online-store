package com.cyecize.skatefixers.areas.shoppingCart.bindingModels;

import com.cyecize.skatefixers.areas.shoppingCart.bindingModels.validators.ValidProductId;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AddCartItemBindingModel {

    @NotNull
    @ValidProductId
    private Long productId;
    
    @NotNull
    @Min(value = 1)
    private Integer quantity;

    public AddCartItemBindingModel(){

    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
