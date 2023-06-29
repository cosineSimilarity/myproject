package com.cosine.myweb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myweb")
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello,myweb!";
    }
}
