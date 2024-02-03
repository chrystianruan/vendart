package com.api.vendart.dtos;

import com.api.vendart.entities.Category;
import com.api.vendart.entities.Product;
import com.api.vendart.entities.Seller;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductDTO {
    private String image;
    private String name;
    private String description;
    private Double value;
    private Category category;
    private Seller seller;
    private Long categoryId;
    private Long sellerId;

    public ProductDTO() {}

    public Product parseToObject() {
        return null;
    }
}
