package com.api.vendart.repositories;

import com.api.vendart.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT products FROM Product products WHERE products.id = ?1")
    public Product findOne(Long id);
}
