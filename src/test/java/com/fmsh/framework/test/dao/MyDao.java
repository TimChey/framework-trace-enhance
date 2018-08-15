package com.fmsh.framework.test.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Repository;

@Repository
public class MyDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    Tracer sleuthTracer;

    public String myDaoMethod() {
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
