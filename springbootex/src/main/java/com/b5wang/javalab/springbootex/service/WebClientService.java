package com.b5wang.javalab.springbootex.service;

import com.b5wang.javalab.springbootex.config.Constant;
import com.b5wang.javalab.springbootex.model.Booking;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.net.URI;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class WebClientService {

    @Autowired
    private WebClient webClient;

    @Async("asyncTasksPool")
    public void asyncPost(int no){
        URI uri = URI.create(Constant.URL_BOOK_TICKETS);

        List<Booking> bookingList = new ArrayList<>();
        bookingList.add(new Booking("beijing-shenyang",1));
        bookingList.add(new Booking("nanjing-shanghai",1));

        List<Mono<Booking[]>> respList = new LinkedList<>();
        long startTime = Instant.now().toEpochMilli();
        for(int i=0; i<no;i++){
            Mono<Booking[]> resp = webClient.post()
                    .uri(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Mono.just(bookingList),List.class)
                    .retrieve()
                    .bodyToMono(Booking[].class);

            respList.add(resp);
        }

        int errorCounter = 0;
        for(Mono<Booking[]> resp:respList){
            Booking[] result = resp.block();
            if(result.length != 2){
                errorCounter++;
            }
        }
        long endTime = Instant.now().toEpochMilli();
        long totalTime = endTime - startTime;

        log.info("asyncPost, Total call: {}, Failed: {}, Total time: {}",no,errorCounter,totalTime);
    }

    public void syncPost(int no){
        List<Booking> bookingList = new ArrayList<>();
        bookingList.add(new Booking("beijing-shenyang",1));
        bookingList.add(new Booking("nanjing-shanghai",1));

        URI uri = URI.create(Constant.URL_BOOK_TICKETS);
        long t0 = Instant.now().toEpochMilli();
        for(int i=0; i<no; i++){
            Mono<Booking[]> resp = webClient.post()
                    .uri(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Mono.just(bookingList),List.class)
                    .retrieve()
                    .bodyToMono(Booking[].class);
            log.info("Result: {}", Arrays.toString(resp.block()));
        }
        long t1 = Instant.now().toEpochMilli();
        long time = t1 - t0;
        log.info("WebClient http call: {}, take: {}",no,time);
    }

}
