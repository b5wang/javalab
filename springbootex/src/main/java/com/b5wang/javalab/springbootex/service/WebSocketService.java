package com.b5wang.javalab.springbootex.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 原生 websocket: https://zhuanlan.zhihu.com/p/30546381
 * WebSocket协议：5分钟从入门到精通: https://www.cnblogs.com/chyingp/p/websocket-deep-in.html
 * WebSocket 教程: https://www.ruanyifeng.com/blog/2017/05/websocket.html
 * */
@Slf4j
@ServerEndpoint(value = "/websocket")
@Service
public class WebSocketService {

    private static final ConcurrentHashMap<String,Session> SESSION_MAP = new ConcurrentHashMap<>();

    public WebSocketService(){
        log.info("init a WebSocketService instance");
    }

    @OnOpen
    public void onOpen(Session session) {
        log.info("WebSocketService.onOpen");
        // pathParameters
        Map<String,String> pathParameters = session.getPathParameters();
        for(Map.Entry<String,String> parameter: pathParameters.entrySet()){
            log.info("path parameter - {} : {}", parameter.getKey(),parameter.getValue());
        }
        // requestParameterMap
        Map<String,List<String>> requestParameterMap = session.getRequestParameterMap();

        // protocolVersion
        String protocolVersion = session.getProtocolVersion();
        log.info("protocolVersion - {}",protocolVersion);
        // queryString
        String queryString = session.getQueryString();
        log.info("queryString - {}",queryString);



    }

    @OnClose
    public void onClose(Session session) {
        log.info("WebSocketService.onClose");
    }


}
