package com.webdev.hotelRes.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webdev.hotelRes.entity.Feature;
import com.webdev.hotelRes.repository.FeatureRepository;

@Service
public class FeatureServiceImpl implements FeatureService{

    @Autowired
    FeatureRepository featureRepository;

    @Override
    public Feature getFeatureById(Long id) {
        return featureRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
    }

    

    @Override
    public Feature createFeature(Feature feature) {
        return featureRepository.save(feature);
    }

    @Override
    public void deleteFeature(Long id) {
        featureRepository.deleteById(id);
    } 
    
}
