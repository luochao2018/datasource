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

/**
 * 数据源配置
 */
@Slf4j
@Configuration
public class DataSourceConfiguration {
    public final static String MASTER = "masterDataSource";//数据源1
    public final static String SLAVE = "slaveDataSource";//数据源2
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
    @Primary//主数据源标识(必须指定一个)
    @Bean(value = MASTER)//别名
    @Qualifier(MASTER)//别名
    @ConfigurationProperties(prefix = "spring.datasource.master")//数据源信息
    public DataSource masterDataSource() {
        log.info("create master datasource...");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    /**
     * 数据源2
     *
     * @return
     */
    @Bean(value = SLAVE)
    @Qualifier(SLAVE)
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource() {
        log.info("create slave datasource...");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

}
