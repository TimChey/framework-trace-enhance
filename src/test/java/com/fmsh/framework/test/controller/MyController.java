package com.fmsh.framework.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fmsh.framework.test.service.MyService;

@RestController
@RequestMapping("/api")
public class MyController {

    @Autowired
    public MyService myService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public String get() {
        return myService.myServiceMethod();
        
    }
}
