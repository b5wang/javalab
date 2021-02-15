package com.b5wang.javalab.springbootex.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(){
        //配置动态连接池
        //ConnectionProvider provider = ConnectionProvider.elastic("elastic pool");
        //配置固定大小连接池，如最大连接数、连接获取超时、空闲连接死亡时间等
        ConnectionProvider provider = ConnectionProvider
                .builder("webClient-connection-pool")
                .maxConnections(500)
                .maxIdleTime(Duration.ofMillis(10000))
                .pendingAcquireTimeout(Duration.ofMillis(100))
                .build();
        HttpClient httpClient = HttpClient.create(provider).keepAlive(true);
        return WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient)).build();
    }

}
