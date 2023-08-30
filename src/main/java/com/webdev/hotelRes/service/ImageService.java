package com.webdev.hotelRes.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.webdev.hotelRes.entity.Image;

@Service
public interface ImageService {
    Image getImageById(Long id);
    List<Image> getImagesByRoomId(Long roomId);
    Image createImage(Image image);
    void deleteImage(Long id);
}
