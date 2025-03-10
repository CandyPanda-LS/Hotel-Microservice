package com.example.Hotel.repository;

import com.example.Hotel.model.Hotel;
import com.example.Hotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, String> {
    boolean existsByRoomNameAndHotel(String roomName, Hotel hotel);
}
