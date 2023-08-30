package com.webdev.hotelRes.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webdev.hotelRes.entity.Room;
import com.webdev.hotelRes.repository.RoomRepository;

@Service
public class RoomServiceImpl implements RoomService{

    @Autowired
    RoomRepository roomRepository;

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
    }

    @Override
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room updateRoom(Long id, Room room) {
        Room existingRoom = getRoomById(id);

        
        existingRoom.setName(room.getName());
        existingRoom.setType(room.getType());
        existingRoom.setOccupants(room.getOccupants());
        existingRoom.setUnitPrice(room.getUnitPrice());

        return roomRepository.save(existingRoom);
    }

    @Override
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }
}
