package com.selenium;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 需要先启动java -jar selenium-server-standalone-3.14.0.jar
 * chromedriver.exe为同级
 */
public class ForServer {


    public static String screenshot(String serverUrl, String url, int width, int height, String dir) {
        WebDriver driver = null;
        String sessionId = null;

        try {
            DesiredCapabilities cap = DesiredCapabilities.chrome();
            cap.setPlatform(Platform.ANY);
            ChromeOptions options = new ChromeOptions();
            // 设置不打开浏览器
            options.addArguments("--headless", "--disable-gpu");
            cap.setCapability(ChromeOptions.CAPABILITY, options);
            // 连接chorme服务
            driver = new RemoteWebDriver(new URL(serverUrl), cap);
            // 存储sessionId
//            sessionId = ((RemoteWebDriver) driver).getSessionId().toString();

            // 设置页面地址
            driver.get(url);
            // 添加cookie
            // driver.manage().addCookie(new Cookie("OayhmCookieValue",
            // "440763404"));
            // 执行js

            // 设置浏览器窗口大小
            driver.manage().window().setSize(new Dimension(width, height));
            ((RemoteWebDriver) driver).executeScript("setTimeout(function () {var d = document.createElement('div');d.id = \"initEnd\";document.body.appendChild(d);}, 4000);");
            // 判断是否加载完成
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("initEnd")));

            // 截屏
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String fileName = "mapScreenShot" + System.currentTimeMillis() + ".png";
            File dirFile = new File(dir);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }

            FileUtils.copyFile(srcFile, new File(dir + "/" + fileName));
            return dir + "/" + fileName;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (driver != null)
                driver.close();
        }
        return null;
    }
}
