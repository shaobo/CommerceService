package com.bw.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Transactional
    @Modifying
    @Query("update Product p set p.stock = p.stock - :qty where p.id = :productId")
    int updateStock(int qty, long productId);
}
