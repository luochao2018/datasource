package com.imooc.config.datasource;

import lombok.extern.slf4j.Slf4j;

/**
 * 数据源管理
 */
@Slf4j
public class DataSourceContextHolder {
    private static final ThreadLocal<String> holder = new ThreadLocal<>();

    /**
     * 创建
     *
     * @param type
     */
    public static void setDataSource(String type) {
        holder.set(type);
    }

    /**
     * 返回
     *
     * @return
     */
    public static String getDataSource() {
        String lookUpKey = holder.get();
        return lookUpKey == null ? DataSourceConfiguration.MASTER : lookUpKey;//默认主数据
    }

    /**
     * 销毁
     */
    public static void clear() {
        holder.remove();
    }
}
