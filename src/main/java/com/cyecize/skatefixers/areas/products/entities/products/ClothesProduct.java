package com.cyecize.skatefixers.areas.products.entities.products;

import com.cyecize.skatefixers.areas.products.entities.BaseProduct;
import com.cyecize.skatefixers.areas.products.entities.ProductSize;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import java.util.List;

@Entity
public class ClothesProduct extends BaseProduct {

    @ManyToMany(targetEntity = ProductSize.class)
    @JoinTable(name = "products_sizes", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "size_id"))
    private List<ProductSize> sizes;

    public ClothesProduct(){
        super();
    }

    public List<ProductSize> getSizes() {
        return sizes;
    }

    public void setSizes(List<ProductSize> sizes) {
        this.sizes = sizes;
    }
}
