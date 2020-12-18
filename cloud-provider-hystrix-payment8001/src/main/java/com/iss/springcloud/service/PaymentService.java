package com.iss.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    //正常访问ok
    public String paymentInfo_OK(Integer id){
        return "线程池："+Thread.currentThread().getName()+"  paymentInfo_OK  "+id+"\t"+")))))哈哈哈((((((";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "4000")
    })
    public String paymentInfo_Timeout(Integer id){
        int timeNumber = 1;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+"  paymentInfo_TimeOut  "+id+"\t"+")))))哈哈哈((((((耗时:"+timeNumber+"秒钟";
    }

    public String paymentInfo_TimeoutHandler(Integer id){
        return "线程池："+Thread.currentThread().getName()+"  paymentInfo_TimeOutHandler,id  "+id+"\t"+"wuwuwuwuwuwuw";
    }


    //------服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircutitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),  //是否开启断路
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),   //请求次
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),  //时间范
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "10"), //失败率达到多少后跳
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if( id < 0 ){
            throw new RuntimeException("#############id不能为复数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return  Thread.currentThread().getName()+"\t"+"调用成功，流水号："+serialNumber;

    }
    public String paymentCircutitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能负数，请稍后再试，id:"+id;
    }
}
