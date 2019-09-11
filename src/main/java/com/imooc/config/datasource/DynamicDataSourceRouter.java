package com.imooc.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 当前数据源
 */
@Slf4j
public class DynamicDataSourceRouter extends AbstractRoutingDataSource {
    private Logger log = LoggerFactory.getLogger(Object.class);
    @Override
    protected Object determineCurrentLookupKey() {
        String dataSource = DataSourceContextHolder.getDataSource();
        log.info("当前数据源:"+dataSource);
        return dataSource;
    }
}
