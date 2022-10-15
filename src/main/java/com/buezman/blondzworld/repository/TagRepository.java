package com.buezman.blondzworld.repository;

import com.buezman.blondzworld.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
