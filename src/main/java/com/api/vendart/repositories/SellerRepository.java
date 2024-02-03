package com.api.vendart.repositories;

import com.api.vendart.entities.Category;
import com.api.vendart.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    @Query("SELECT sellers FROM Seller sellers WHERE sellers.id = ?1")
    public Seller findOne(Long id);

}
