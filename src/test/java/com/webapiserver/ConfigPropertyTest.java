package com.webapiserver;

import com.webapiserver.config.ConfigProperty;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest
@Slf4j
public class ConfigPropertyTest {
    @Autowired
    ConfigProperty configProperty;

    @Autowired
    Environment environment;

    @Test
    void envUserHome() {
       /* log.info("user.home:{}",configProperty.getLogfile());
        log.info("user.dir:{}",configProperty.getUserDir());
        log.info("user.logfile:{}",configProperty.getLogfile());
        log.info("global.config:{}",configProperty.getGlobalConfigMessage());*/

    }
}
