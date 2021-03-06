package com.cyecize.skatefixers.areas.products.entities;

import com.cyecize.skatefixers.areas.googleDrive.util.ImageIdExtractor;

import javax.persistence.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "brands")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(name = "brand_name", nullable = false, unique = true)
    private String brandName;

    @Column(name = "image", nullable = false)
    private String image;

    @OneToMany(targetEntity = BaseProduct.class, mappedBy = "brand")
    private List<BaseProduct> products;


    public Brand(){
        //TODO persist some brands in none present on app startup
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getImage() {
        return image;
    }


    public void setImage(String image) {
        this.image = ImageIdExtractor.setFormat(image);
    }

    public String extractId(){
       return ImageIdExtractor.extractId(this.image);
    }

    public List<BaseProduct> getProducts() {
        return products;
    }

    public void setProducts(List<BaseProduct> products) {
        this.products = products;
    }
}
