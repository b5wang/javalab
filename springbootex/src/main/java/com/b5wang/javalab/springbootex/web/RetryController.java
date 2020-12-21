package com.b5wang.javalab.springbootex.web;

import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
public class RetryController {

    private static final int MAX_ATTEMPTS = 5;

    @RequestMapping(value = "/traditionalRetry", method = RequestMethod.GET)
    @ResponseBody
    public String traditionalRetry(@RequestParam("retry") int retry){
        String result = null;
        int attempts = 1;

        while(attempts <= MAX_ATTEMPTS){
            Boolean jobDone = tryToDo(attempts,retry);

            // Success
            if(jobDone){
                result = "Job done!";
                break;
            }

            attempts++;

            if(attempts > MAX_ATTEMPTS){
                result = "Reach max attempts, job failed!";
                break;
            }

            // Sleep
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    private Boolean tryToDo(int attempts,int retry){

        System.out.println(String.format("%d time....... try to do something!",attempts));

        if(attempts < retry){
            return Boolean.FALSE;
        }else{
            return Boolean.TRUE;
        }

    }

    @RequestMapping(value = "/springRetry", method = RequestMethod.GET)
    @ResponseBody
    public String springRetry(@RequestParam("retry") int retry)throws Exception{
        RetryTemplate retryTemplate = new RetryTemplate();

        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
        simpleRetryPolicy.setMaxAttempts(MAX_ATTEMPTS);
        retryTemplate.setRetryPolicy(simpleRetryPolicy);

        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod(100L);
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);

        String result = retryTemplate.execute(new RetryCallback<String, Exception>() {

            /**
             * RetryCallback<String,Exception>
             * String - return value
             * Exception - business failure, it will exist retry flow
             * RuntimeException - trigger next retry
             */
            @Override
            public String doWithRetry(RetryContext retryContext) throws Exception {
                Boolean success = tryToDo(retryContext.getRetryCount() + 1, retry);
                if (!success) {
                    //Trigger next retry
                    throw new RuntimeException("Retry failed, continue next retry......");
                }
                return "Job done!";
            }

        }, new RecoveryCallback<String>() {

            /**
             * Reach max attampts
             * */
            @Override
            public String recover(RetryContext retryContext) throws Exception {
                return String.format("Reach max attempts %d, job failed!",retryContext.getRetryCount());
            }

        });

        return result;
    }

}
