package com.b5wang.javalab.springbootex.web;

import com.b5wang.javalab.springbootex.service.WebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestCallController {

    @Autowired
    private WebClientService webClientService;

    @GetMapping("/restTemplate/async")
    @ResponseBody
    public String asyncRestTemplate(){
        return "ok";
    }

    @GetMapping("/webClient/async")
    @ResponseBody
    public String asyncWebClient(){
        webClientService.asyncPost(1000);
        return "ok";
    }

}
