package com.b5wang.javalab.springbootex.web;

import com.b5wang.javalab.springbootex.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class AsynController {

    @Autowired
    private AsyncService asyncService;

    @RequestMapping(value = "/async", method = RequestMethod.GET)
    @ResponseBody
    public String async() throws InterruptedException {
        System.out.println("Create 10 async jobs!");
        List<CompletableFuture<Boolean>> futures = new LinkedList<>();
        for(int i = 0; i < 10; i++){
            futures.add(asyncService.process(i));
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        System.out.println("10 async jobs finished!");
        return "Done!";
    }


}
