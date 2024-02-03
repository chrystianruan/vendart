package com.api.vendart.entities;

import com.api.vendart.dtos.CategoryDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "categories")
@SQLDelete(sql = "UPDATE categories SET deleted_at = NOW() WHERE id=?")
@Where(clause = "deleted_at is null")
@Getter @Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    @Column(name = "deleted_at")
    private Date deletedAt;
    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public Category(){}

    public CategoryDTO parseToDTO() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(this.getName());
        categoryDTO.setProducts(this.getProducts());

        return categoryDTO;
    }

}
