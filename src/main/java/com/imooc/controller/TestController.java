package com.imooc.controller;

import com.alibaba.fastjson.JSONObject;
import com.imooc.aop.DBSource;
import com.imooc.dao.mapper.TestMapper;
import com.imooc.util.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TestController {
    @Resource
    private TestMapper testMapper;

    @PostMapping("login")
    @ResponseBody
    @DBSource("slaveDataSource")
    public Result test() {
        List<JSONObject> selectAll = testMapper.selectAll();
        return Result.success(selectAll);
    }
}
