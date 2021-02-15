package com.b5wang.javalab.springbootex.service;

import com.b5wang.javalab.springbootex.config.Constant;
import com.b5wang.javalab.springbootex.model.Booking;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class RestTemplateService {

    @Autowired
    private RestTemplate restTemplate;

    public void syncCall(int no){
        List<Booking> bookingList = new ArrayList<>();
        bookingList.add(new Booking("beijing-shenyang",1));
        bookingList.add(new Booking("nanjing-shanghai",1));

        HttpHeaders headers = new HttpHeaders();
        //List<String> cookies = new ArrayList<>();
        //cookies.add("JSESSIONID=" + Strings.nullToEmpty(jsessionId));
        //cookies.add("token=" + Strings.nullToEmpty(token));
        //headers.put(HttpHeaders.COOKIE,cookies);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<List<Booking>> request = new HttpEntity(bookingList,headers);

        long t0 = System.currentTimeMillis();
        for(int i=0; i<no;i++){
            ResponseEntity<Booking[]> responseEntity =
                    restTemplate.postForEntity(Constant.URL_BOOK_TICKETS,request,Booking[].class);
            Booking[] bookings = responseEntity.getBody();

            log.info("Result: {}",Arrays.toString(bookings));
        }
        long t1 = System.currentTimeMillis();
        long time = t1 - t0;
        log.info("RestTemplate http call: {}, take: {}",no,time);
    }

}
