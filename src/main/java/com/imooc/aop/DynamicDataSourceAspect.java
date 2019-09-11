package com.imooc.aop;

import com.imooc.config.datasource.DataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 自定义注解 + AOP的方式实现数据源动态切换。
 * Created by pure on 2018-05-06.
 */
@Aspect
@Component
public class DynamicDataSourceAspect {

    @Before("@annotation(DBSource)")
    public void beforeSwitchDBSource(JoinPoint point) {
        //获得当前访问的class
        Class<?> className = point.getTarget().getClass();
        //获得访问的方法名
        String methodName = point.getSignature().getName();
        //得到方法的参数的类型
        Class[] argClass = ((MethodSignature) point.getSignature()).getParameterTypes();
        String dataSource = DataSourceContextHolder.getDataSource();
        try {
            // 得到访问的方法对象
            Method method = className.getMethod(methodName, argClass);
            // 判断是否存在@DS注解
            if (method.isAnnotationPresent(DBSource.class)) {
                DBSource annotation = method.getAnnotation(DBSource.class);
                // 取出注解中的数据源名
                dataSource = annotation.value();
                System.out.println(dataSource);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 切换数据源
        DataSourceContextHolder.setDataSource(dataSource);
    }

    @After("@annotation(DBSource)")
    public void afterSwitchDBSource(JoinPoint point) {
        DataSourceContextHolder.clear();
    }
}
