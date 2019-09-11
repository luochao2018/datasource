package com.imooc.controller;

import com.alibaba.fastjson.JSONObject;
import com.imooc.aop.DBSource;
import com.imooc.config.datasource.DataSourceConfiguration;
import com.imooc.config.datasource.DataSourceContextHolder;
import com.imooc.dao.mapper.TestMapper;
import com.imooc.util.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {
    @Resource
    private TestMapper testMapper;

    @PostMapping("login")
    @ResponseBody
//    @DBSource(DataSourceConfiguration.SLAVE)//注解方式(方法体均采用)
    public Result test() {
        Map map = new HashMap();
        DataSourceContextHolder.setDataSource(DataSourceConfiguration.SLAVE);//只针对该接口
        List<JSONObject> select1 = testMapper.selectAll();
        DataSourceContextHolder.clear();
        map.put("select1", select1);
        DataSourceContextHolder.setDataSource(DataSourceConfiguration.MASTER);
        List<JSONObject> select2 = testMapper.selectAll();
        DataSourceContextHolder.clear();
        map.put("select2", select2);
        List<JSONObject> select3 = testMapper.selectAll();
        map.put("select3", select3);
        return Result.success(map);
    }
}
