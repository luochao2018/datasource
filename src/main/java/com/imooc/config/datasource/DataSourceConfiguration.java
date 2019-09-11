package com.imooc.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class DataSourceConfiguration {
    private final static String MASTER_DATASOURCE_KEY = "masterDataSource";
    private final static String SLAVE_DATASOURCE_KEY = "slaveDataSource";
    private Logger log = LoggerFactory.getLogger(Object.class);

    /**
     * 对象连接池
     */
    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    /**
     * 数据源1
     *
     * @return
     */
    @Primary
    @Bean(value = MASTER_DATASOURCE_KEY)
    @Qualifier(MASTER_DATASOURCE_KEY)
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        log.info("create master datasource...");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    /**
     * 数据源2
     *
     * @return
     */
//    @Primary
    @Bean(value = SLAVE_DATASOURCE_KEY)
    @Qualifier(SLAVE_DATASOURCE_KEY)
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource() {
        log.info("create slave datasource...");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

}