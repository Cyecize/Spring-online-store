package com.cyecize.skatefixers.areas.products.entities;

import javax.persistence.*;

@Entity
@Table(name = "product_sizes")
public class ProductSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(name = "size_name", nullable = false, unique = true)
    private String sizeName;

    public ProductSize(){
        //TODO create sizes from admin panel
        //TODO create sizes on application startup of none present
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }
}
