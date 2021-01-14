package com.b5wang.javalab.springbootex;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Arrays;
import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
@Slf4j
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
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
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                log.info("{}",beanName);
            }

//            GetBeanService bean1 = (GetBeanService)ctx.getBean("getBeanService");
//            log.info("Get bean: {}",bean1.hello());
//
//            GetBeanService bean2 = (GetBeanService)ctx.getBean("getBeanServiceName");
//            log.info("Get bean: {}",bean2.hello());
//
//            GetBeanService bean3 = (GetBeanService)ctx.getBean("getBeanServiceQualifier");
//            log.info("Get bean: {}",bean3.hello());
//
//            GetBeanService bean4 = ctx.getBean(GetBeanService.class);
//            log.info("Get bean: {}",bean4.hello());


        };
    }

    @Bean("asynTasksPool")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("async-");
        executor.initialize();
        return executor;
    }
}
