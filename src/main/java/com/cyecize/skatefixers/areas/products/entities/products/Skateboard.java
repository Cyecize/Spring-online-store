package com.cyecize.skatefixers.areas.products.entities.products;

import com.cyecize.skatefixers.areas.products.entities.BaseProduct;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Skateboard extends BaseProduct {

    @Column(name = "board_width")
    private Double boardWidth;

    Skateboard() {
        super();
    }

    public Skateboard(Double boardWidth) {
        this.boardWidth = boardWidth;
    }
}
