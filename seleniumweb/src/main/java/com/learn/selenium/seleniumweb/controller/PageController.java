package com.learn.selenium.seleniumweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("/helloWorld")
    public String helloWorld() {
        return "helloworld";
    }
}
