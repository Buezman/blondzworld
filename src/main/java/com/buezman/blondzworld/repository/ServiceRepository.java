package com.buezman.blondzworld.repository;

import com.buezman.blondzworld.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}
