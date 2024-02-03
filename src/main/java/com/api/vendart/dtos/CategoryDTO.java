package com.api.vendart.dtos;

import com.api.vendart.entities.Category;
import com.api.vendart.entities.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter @Setter
public class CategoryDTO {
    private String name;
    private List<Product> products;
    public CategoryDTO() {}

    public Category parseToObject() {
        Category category = new Category();
        category.setName(this.name);
        category.setProducts(this.products);

        return category;
    }
}
