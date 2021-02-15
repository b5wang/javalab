package com.b5wang.javalab.springbootex.web;

import com.b5wang.javalab.springbootex.service.RestTemplateService;
import com.b5wang.javalab.springbootex.service.WebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestCallController {

    @Autowired
    private WebClientService webClientService;

    @Autowired
    private RestTemplateService restTemplateService;

    @GetMapping("/restTemplate/async")
    @ResponseBody
    public String asyncRestTemplate(){
        /**
         * AsyncRestTemplate has been deprecated
         * */
        return "ok";
    }

    @GetMapping("/webClient/async")
    @ResponseBody
    public String asyncWebClient(){
        webClientService.asyncPost(1000);
        return "ok";
    }

    @GetMapping("/restTemplate/sync")
    @ResponseBody
    public String restTemplate(){
        restTemplateService.syncCall(1000);
        return "ok";
    }

}
