package com.rosefinch.example.spring.servcie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author York.Hwang
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
@Service
public class BundleStrategy extends AbstractStrategy{

    @Override
    public String getStrategyType(int type) {
        final String v = String.valueOf(type);
        this.setStrategy(v);
        return v;
    }

}
