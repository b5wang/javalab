package com.b5wang.javalab.springbootex.config;

import com.b5wang.javalab.springbootex.service.BeanNameService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class BeansConfig {

    /**
     * Bean name = method name
     * */
    @Primary
    @Bean
    public BeanNameService beanNameService(){
        return new BeanNameService("Default name");
    }

    /**
     * Bean name = 'getBeanServiceName'
     * */
    @Bean("getBeanServiceName")
    public BeanNameService createBeanWithName(){
        return new BeanNameService("Bean has a name");
    }

    /**
     * Bean name = createBeanWithQualifier
     *
     * @Qualifier does not work
     *
     * */
    @Bean
    @Qualifier("getBeanServiceQualifier")
    public BeanNameService createBeanWithQualifier(){
        return new BeanNameService("Bean has a qualifier");
    }


}
