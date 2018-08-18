package com.cyecize.skatefixers.areas.home.viewModels;

import com.cyecize.skatefixers.areas.products.entities.BaseProduct;
import org.springframework.data.domain.Page;

public class SearchPageViewModel {

    private String searchText;

    private Page<BaseProduct> products;

    public SearchPageViewModel(String searchText, Page<BaseProduct> products) {
        this.searchText = searchText;
        this.products = products;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public Page<BaseProduct> getProducts() {
        return products;
    }

    public void setProducts(Page<BaseProduct> products) {
        this.products = products;
    }
}
