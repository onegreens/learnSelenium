package com.learn.selenium.seleniumweb.system;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Resources {
    @Value("${chromeDriver}")
    @Getter
    private String chromeDriver;
    @Value("${chromeDriverName}")
    @Getter
    private String chromeDriverName;
}
