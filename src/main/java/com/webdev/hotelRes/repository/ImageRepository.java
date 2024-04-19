package com.webdev.hotelRes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.webdev.hotelRes.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long>{
    @Query("SELECT image FROM Image image WHERE image.room.id = :roomId")
    List<Image> findImageByRoomId(@Param("roomId") Long roomId);
}
