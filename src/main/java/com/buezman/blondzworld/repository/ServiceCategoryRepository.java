package com.buezman.blondzworld.repository;

import com.buezman.blondzworld.entity.ServiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory, Long> {
}
