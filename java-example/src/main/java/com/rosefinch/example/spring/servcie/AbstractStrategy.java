package com.rosefinch.example.spring.servcie;

import lombok.extern.slf4j.Slf4j;

/**
 * @author York.Hwang
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
public abstract class AbstractStrategy {


    private String strategy;

    /**
     * 策略类型
     * @param type
     * @return
     */
    public abstract String getStrategyType(int type);


    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public void print() {
        log.info(strategy);
    }
}
