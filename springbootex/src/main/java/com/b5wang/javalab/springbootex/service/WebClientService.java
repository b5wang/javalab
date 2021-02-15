package com.b5wang.javalab.springbootex.service;

import com.b5wang.javalab.springbootex.config.Constant;
import com.b5wang.javalab.springbootex.model.Booking;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class WebClientService {

    private WebClient sbexPartnerClient;

    @PostConstruct
    public void postConstruct(){
        //配置动态连接池
        //ConnectionProvider provider = ConnectionProvider.elastic("elastic pool");
        //配置固定大小连接池，如最大连接数、连接获取超时、空闲连接死亡时间等
        ConnectionProvider provider = ConnectionProvider
                .builder("sbexPartnerClient-connection-pool")
                .maxConnections(500)
                .maxIdleTime(Duration.ofMillis(10000))
                .pendingAcquireTimeout(Duration.ofMillis(100))
                .build();

        HttpClient httpClient = HttpClient.create(provider).keepAlive(true);

        sbexPartnerClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }


    @Async("asyncTasksPool")
    public void asyncPost(int no){
        URI uri = URI.create(Constant.URL_BOOK_TICKETS);

        List<Booking> bookingList = new ArrayList<>();
        bookingList.add(new Booking("beijing-shenyang",1));
        bookingList.add(new Booking("nanjing-shanghai",1));

        List<Mono<Booking[]>> respList = new LinkedList<>();
        long startTime = Instant.now().toEpochMilli();
        for(int i=0; i<no;i++){
            Mono<Booking[]> resp = sbexPartnerClient.post()
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

}
