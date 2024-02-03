package com.api.vendart.repositories;

import com.api.vendart.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT categories FROM Category categories WHERE categories.id = ?1")
    public Category findOne(Long id);

}
