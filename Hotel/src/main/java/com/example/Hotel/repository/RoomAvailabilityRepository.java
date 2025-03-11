package com.example.Hotel.repository;

import com.example.Hotel.model.Room;
import com.example.Hotel.model.RoomAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomAvailabilityRepository extends JpaRepository<RoomAvailability, String> {

    @Query("SELECT MAX(ra.date) FROM RoomAvailability ra WHERE ra.room = :room")
    Optional<LocalDate> findLatestDateByRoom(@Param("room") Room room);

    @Query("SELECT MIN(ra.date) FROM RoomAvailability ra WHERE ra.room = :room")
    Optional<LocalDate> findEarliestDateByRoom(@Param("room") Room room);

    void deleteByRoomAndDate(Room room, LocalDate date);

    Optional<RoomAvailability> findByRoomAndDate(Room room, LocalDate date);

    List<RoomAvailability> findByRoom(Room room);
}
