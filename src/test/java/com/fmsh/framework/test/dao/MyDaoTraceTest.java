package com.fmsh.framework.test.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fmsh.framework.test.BaseTest;

public class MyDaoTraceTest extends BaseTest {

    @Autowired
    private MyDao myDao;

    @Test
    public void testTrace() {
        String res = myDao.myDaoMethod();
        assert res != null;
    }

}
