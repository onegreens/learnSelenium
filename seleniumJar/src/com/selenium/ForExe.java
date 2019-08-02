package com.selenium;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

public class ForExe {
    public static void main(String[] args) {
        screenshot("http://localhost:8088/helloWorld", 1800, 1600, "F:\\project\\learn\\seleniumweb\\src\\main\\resources\\static\\");
    }

    public static String screenshot(String url, int width, int height, String path) {
        WebDriver driver = null;
        String fileName = "mapScreenShot" + System.currentTimeMillis() + ".png";

        try { //设置不弹出浏览器窗口
            System.setProperty("webdriver.chrome.driver", "F:\\project\\learn\\seleniumJar\\lib\\chromedriver.exe");//chromedriver服务地址
            ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless","--disable-gpu");
            driver = new ChromeDriver(options);
            //设置打开的地址
            driver.get(url);
            //设置浏览器窗口大小
            driver.manage().window().setSize(new Dimension(width, height));
            //判断是否加载完成
//            WebDriverWait wait = new WebDriverWait(driver, 10);
//            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("initEnd")));
            //截屏
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(srcFile, new File(path + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
        return "http://localhost:8088/" + fileName;

    }
}
