package com.example.Hotel.scheduler;

import com.example.Hotel.service.impl.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RoomAvailabilityScheduler {
    @Autowired
    RoomService roomService;

    @Scheduled(cron = "0 43 16 * * ?", zone = "UTC")
    public void updateRoomAvailabilitiesDaily() {
        log.info("Updating room availability daily");
        roomService.cronUpdateRoomAvailabilities();
    }
}
