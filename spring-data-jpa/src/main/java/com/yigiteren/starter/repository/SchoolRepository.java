package com.yigiteren.starter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yigiteren.starter.entities.School;

@Repository
public interface SchoolRepository extends JpaRepository<School, Integer> {
    
}
