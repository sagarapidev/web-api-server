package com.webapiserver.config;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Configuration
@PropertySources({
        @PropertySource("classpath:global.config.properties"),
        @PropertySource("classpath:config.properties")
})
public class ConfigProperty {
    @Value("${ENV_USER_HOME}")
    String userHome;
    @Value("${ENV_USER_DIR}")
    String userDir;
    @Value("${LOG_FILENAME}")
    String logfile;

    @Value("${DOWNLOAD_FILE_LOCATION}")
    String downloadLocation;

    @Value("${DOWNLOAD_FILE_EXTENSION}")
    String extension;

    @Value("${GLOBAL_CONFIG_MESSAGE}")
    String globalConfigMessage;
}
