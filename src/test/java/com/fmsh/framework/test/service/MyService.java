package com.fmsh.framework.test.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;

@Service
public class MyService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    Tracer sleuthTracer;

    public String myServiceMethod() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            logger.info("系统异常");
            Thread.currentThread().interrupt();
        }
        Span currentSpan = sleuthTracer.getCurrentSpan();
        return currentSpan.toString();
    }
}
