package com.webdev.hotelRes.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webdev.hotelRes.entity.Feature;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long> {
    
}
