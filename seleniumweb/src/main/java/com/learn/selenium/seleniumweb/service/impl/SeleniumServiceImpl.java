package com.learn.selenium.seleniumweb.service.impl;

import com.learn.selenium.seleniumweb.service.SeleniumService;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

@Service
public class SeleniumServiceImpl implements SeleniumService {
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public String getTitle() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8088/helloworld.html");
        driver.manage().window().setSize(new Dimension(480, 800));
        String title = driver.getTitle();
        driver.close();
        return title;
    }

    @Override
    public String screenshot() {
        //设置不弹出浏览器窗口
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless","--disable-gpu");
        WebDriver driver = new ChromeDriver(options);
        //设置打开的地址
        driver.get("http://localhost:8088/helloWorld");
        //设置浏览器窗口大小
        driver.manage().window().setSize(new Dimension(1800, 1600));
        //判断是否加载完成
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("initEnd")));
        //截屏
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String fileName = "mapScreenShot" + System.currentTimeMillis() + ".png";
        try {
            FileUtils.copyFile(srcFile, new File("F:\\project\\learn\\seleniumweb\\src\\main\\resources\\static\\" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        driver.quit();
        return "http://localhost:8088/" + fileName;
    }

    @Override
    public String screenshot(Integer width, Integer height, Integer zoom, Integer targetZoom, Double lat, Double lng) {
        //设置不弹出浏览器窗口
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu");
        WebDriver driver = new ChromeDriver(options);
        driver.get("http://localhost:8088/helloWorld?targetZoom=" + targetZoom);
        driver.manage().window().setSize(new Dimension(width, height));

        return null;
    }

    @Override
    public void redisset() {
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set("test", "test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
