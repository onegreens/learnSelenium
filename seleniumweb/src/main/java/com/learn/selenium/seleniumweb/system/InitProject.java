package com.learn.selenium.seleniumweb.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

@Component
public class InitProject implements ApplicationRunner {
    private static final Logger LOG = LoggerFactory.getLogger(InitProject.class);
    @Autowired
    Resources resources;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOG.info("==========系统启动==============");
        String path = ClassUtils.getDefaultClassLoader().getResource(resources.getChromeDriver()).getPath();
        System.setProperty(resources.getChromeDriverName(), path);//chromedriver服务地址
    }
}
