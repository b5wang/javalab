package com.b5wang.javalab.sbexpartner.api;

import com.b5wang.javalab.sbexpartner.model.Booking;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/booking")
public class TicketingController {

    @RequestMapping(value="/tickets",method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Booking>> bookTickets(@RequestBody List<Booking> bookingList){
        log.info("book tickets: {}", Arrays.toString(bookingList.toArray()));
        return ResponseEntity.ok(bookingList);
    }

}
