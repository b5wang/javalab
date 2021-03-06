package com.b5wang.javalab.springbootex;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
@Slf4j
public class SpringbootExApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootExApplication.class, args);
    }

    /**
     * CommandLineRunner and ApplicationRunner
     * 两个接口的作用相同，不同的是run方法接收的参数，
     * Ref: https://www.jianshu.com/p/de7b0e124248
     * */
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Chance to do something just after application started");

            String[] beanNames = ctx.getBeanDefinitionNames();
            log.info("-------All beans-------");
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                log.info("{}",beanName);
            }
        };
    }
}
