package com.learn.selenium.seleniumweb.service;

public interface SeleniumService {

    public String getTitle();

    public String screenshot();

    public String screenshot(Integer width, Integer height, Integer zoom,Integer targetZoom, Double lat, Double lng);

    public void redisset();
}
