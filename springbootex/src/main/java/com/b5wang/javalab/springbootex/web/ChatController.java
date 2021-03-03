package com.b5wang.javalab.springbootex.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class ChatController {

    @RequestMapping(value="/webchat",method = RequestMethod.GET)
    public String webchat(Model model){
        log.info("---chat");
        return "chat";
    }

}
