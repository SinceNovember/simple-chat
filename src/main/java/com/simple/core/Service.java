package com.simple.core;

import org.apache.ibatis.exceptions.TooManyResultsException;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public interface Service<T> {
    T save(T model);

    Integer save(List<T> models);

    Integer deleteById(int id);

    Integer deleteByIds(String ids);

    T findById(int id);

    T findBy(String fieldName, Object value) throws TooManyResultsException;

//    List<T> findByIds(String ids);

    List<T> findByCondition(Condition condition);

    List<T> findAll();

    List<T> selectByExample(Example example);

    int update(T model);


 }
