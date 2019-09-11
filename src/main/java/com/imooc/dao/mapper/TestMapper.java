package com.imooc.dao.mapper;

import com.alibaba.fastjson.JSONObject;
import com.imooc.aop.DBSource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    List<JSONObject> selectAll();
}
