package com.simple.core;

import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface MyMapper<T> extends Mapper<T>, ConditionMapper<T>,
        IdsMapper<T>, MySqlMapper<T> {
}
