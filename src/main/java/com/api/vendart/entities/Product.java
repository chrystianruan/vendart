package com.api.vendart.entities;

import com.api.vendart.dtos.ProductDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Date;
@Entity
@Table(name = "products")
@SQLDelete(sql = "UPDATE products SET deleted_at = NOW() WHERE id=?")
@Where(clause = "deleted_at is null")
@Getter @Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "image")
    private String image;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "value ")
    private Double value;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    @Column(name = "deleted_at")
    private Date deletedAt;

    public Product() {}

    public ProductDTO parseToDTO() {
        ProductDTO dto = new ProductDTO();
        dto.setName(this.getName());
        dto.setValue(this.getValue());
        dto.setCategory(this.getCategory());
        dto.setImage(this.getImage());
        dto.setSeller(this.getSeller());
        dto.setDescription(this.getDescription());

        return dto;
    }
}
