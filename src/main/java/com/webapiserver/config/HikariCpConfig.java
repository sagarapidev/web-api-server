package com.webapiserver.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;

@Slf4j
@Configuration
@PropertySources({
        @PropertySource(value = "classpath:h2-db-config.yml",factory = YamlPropertySourceFactory.class)    ,
        @PropertySource(value = "classpath:mysql-db-config.yml",factory = YamlPropertySourceFactory.class)
})
public class HikariCpConfig extends HikariConfig {

    @Profile("dev")
    @Bean
    @ConfigurationProperties("dev.datasource")
    public HikariDataSource devDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Profile("prod")
    @Bean
    @ConfigurationProperties("prod.datasource")
    public HikariDataSource prodDataSource() {
        log.info("Prod database connected:{}","prod");
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }




}
