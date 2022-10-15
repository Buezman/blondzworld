package com.buezman.blondzworld.repository;

import com.buezman.blondzworld.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
