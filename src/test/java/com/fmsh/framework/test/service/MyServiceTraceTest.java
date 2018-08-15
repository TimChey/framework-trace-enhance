package com.fmsh.framework.test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fmsh.framework.test.BaseTest;

public class MyServiceTraceTest extends BaseTest {

    @Autowired
    private MyService myService;

    @Test
    public void testTrace() {
        String res = myService.myServiceMethod();
        assert res != null;
    }

}
