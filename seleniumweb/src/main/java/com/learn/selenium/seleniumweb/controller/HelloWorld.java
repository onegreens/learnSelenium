package com.learn.selenium.seleniumweb.controller;

import com.learn.selenium.seleniumweb.service.SeleniumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloWorld {
    @Autowired
    SeleniumService seleniumService;

    @GetMapping("/getConnect")
    public String getConnect() {
        return "success";
    }

    @PostMapping("/postMessage")
    public Map<String, String> postMessage(String userName, String password) {
        Map<String, String> map = new HashMap<>();
        map.put("username", userName);
        map.put("password", password);
        return map;
    }

    @GetMapping("/getTitle")
    public String getTitle() {
        return seleniumService.getTitle();
    }

    @GetMapping("/screenshot")
    public String screenshot() {
        return seleniumService.screenshot();
    }

    @PostMapping("/screenshot")
    public String screenshot(Integer width, Integer height, Integer zoom, Integer targetZoom, Double lat, Double lng) {
        return seleniumService.screenshot(width, height, zoom, targetZoom, lat, lng);
    }

    @GetMapping("/setRedis")
    public String setRedis() {
        seleniumService.redisset();
        return "success";
    }

}
