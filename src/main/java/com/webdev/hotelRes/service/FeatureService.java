package com.webdev.hotelRes.service;



import org.springframework.stereotype.Service;

import com.webdev.hotelRes.entity.Feature;

@Service
public interface FeatureService {
    Feature getFeatureById(Long id);
    Feature createFeature(Feature feature);
    void deleteFeature(Long id);
}
