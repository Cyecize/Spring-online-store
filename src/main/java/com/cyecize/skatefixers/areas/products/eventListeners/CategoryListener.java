package com.cyecize.skatefixers.areas.products.eventListeners;

import com.cyecize.skatefixers.areas.products.services.BaseProductService;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.EntityManager;



public class CategoryListener{

    @Autowired
    private  BaseProductService productService;

    @Autowired
    private EntityManager entityManager;

    public CategoryListener() {
    }

//    @PreRemove TODO figure out a way to detach entities
//    public void onRemove(Category category){
//        AutowireHelper.autowire(this);
//
//        for(BaseProduct product : category.getProducts()) {
//            entityManager.detach(product);
//            this.productService.hideProduct(product);
//        }
//    }

}
