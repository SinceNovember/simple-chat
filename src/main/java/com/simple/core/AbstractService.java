package com.simple.core;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract  class AbstractService<T> implements Service<T>{
    @Autowired
    protected MyMapper<T> mapper;

    private Class<T> modelClass;

    private String modelName;

    public AbstractService() {
        modelClass = (Class<T>) fetchType(0);
        modelName = modelClass.getSimpleName();
    }
    public Type fetchType(int index) {
        Assert.isTrue(index >= 0 && index <= 1, "type index must be between 0 to 1");
        return ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[index];
    }

    @Override
    public T save(T model) {
        return mapper.insertSelective(model) == 1? model:null;
    }

    @Override
    public Integer save(List<T> models) {
        return mapper.insertList(models);
    }

    @Override
    public Integer deleteById(int id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer deleteByIds(String ids) {
        return mapper.deleteByIds(ids);
    }

    @Override
    public T findById(int id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public T findBy(String fieldName, Object value) throws TooManyResultsException {
        try {
            T model = modelClass.newInstance();
            Field field = modelClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(model, value);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        return null;
    }

//    @Override
//    public List<T> findByIds(String ids) {
//        return mapper.selectByIds(ids);
//    }

    @Override
    public List<T> findByCondition(Condition condition) {
        return mapper.selectByCondition(condition);
    }

    @Override
    public List<T> findAll() {
        return mapper.selectAll();
    }

    @Override
    public List<T> selectByExample(Example example) {
        return mapper.selectByExample(example);
    }

    public int update(T model) {
        return mapper.updateByPrimaryKeySelective(model);
    }

}
