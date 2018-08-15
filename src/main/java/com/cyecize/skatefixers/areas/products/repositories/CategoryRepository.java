package com.cyecize.skatefixers.areas.products.repositories;

import com.cyecize.skatefixers.areas.products.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findCategoryByCategoryNameLatinOrCategoryNameCyrillic(String lat, String cyr);

    //@Query(value = "select * from product_categories as c where c.parent_category IS NULL ", nativeQuery = true)
    @Query(value = "SELECT c FROM Category c WHERE c.parentCategory IS NULL ")
    List<Category> findMainCategories();

}
