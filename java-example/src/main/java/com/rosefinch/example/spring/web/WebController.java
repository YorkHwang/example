package com.rosefinch.example.spring.web;

import com.rosefinch.example.spring.servcie.AbstractStrategy;
import com.rosefinch.example.spring.servcie.BundleStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author York.Hwang
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("/web")
public class WebController {

    private final Random random = new Random();

    @Autowired
    AbstractStrategy timeSaleStrategy;


    @RequestMapping("/get")
    public String getStrategy(){
        final String r = this.timeSaleStrategy.getStrategyType(this.random.nextInt());
        this.timeSaleStrategy.print();
        return r;
    }

}
