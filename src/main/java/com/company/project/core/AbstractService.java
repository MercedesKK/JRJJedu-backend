package com.company.project.core;


import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * 基于通用MyBatis Mapper插件的Service接口的实现
 */
public abstract class AbstractService<T> implements Service<T> {

    @Autowired
    protected Mapper<T> mapper;

    private Class<T> modelClass;    // 当前泛型真实类型的Class

    public AbstractService() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<T>) pt.getActualTypeArguments()[0];
    }

    @Override
    public void save(T model) {
        mapper.insertSelective(model);
    }

    @Override
    public void save(List<T> models) {
        mapper.insertList(models);
    }

    @Override
    public void deleteById(Integer id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByIds(String ids) {
        mapper.deleteByIds(ids);
    }

    @Override
    public void update(T model) {
        mapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public T findById(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public T findBy(String fieldName, Object value) throws TooManyResultsException {
        try {
            T model = modelClass.newInstance();
            Field field = modelClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(model, value);
            return mapper.selectOne(model);
        } catch (ReflectiveOperationException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<T> findByIds(String ids) {
        return mapper.selectByIds(ids);
    }

    @Override
    public List<T> findByCondition(Condition condition) {
        return mapper.selectByCondition(condition);
    }

    @Override
    public List<T> findAll() {
        return mapper.selectAll();
    }

    @Override
    public T findByIdAndValidDelete(Long id) {
        Condition condition = new Condition(modelClass);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("id", id);
        criteria.andEqualTo("isDelete", false);
        if (null != mapper.selectByCondition(condition) && mapper.selectByCondition(condition).size() > 0){
            T t = mapper.selectByCondition(condition).get(0);
            return t;
        }else {
            return null;
        }
    }

    @Override
    public List<T> findByModel(T model) throws TooManyResultsException {
        try {
            Condition condition = new Condition(modelClass);
            Example.Criteria criteria = condition.createCriteria();
            boolean isDelete=false;
            if (model != null) {
                Field[] fields = modelClass.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    if ("isDelete".equals(field.getName())) {
                        isDelete=true;
                        continue;
                    }
                    if(field.get(model)!=null){
                        if (field.getType() == String.class){
                            String fieldValue = (String) field.get(model);
                            if (StringUtils.isNotBlank(fieldValue)) {
                                if (fieldValue.contains("%")) {
                                    criteria.andLike(field.getName(), fieldValue);
                                } else {
                                    criteria.andEqualTo(field.getName(), fieldValue);
                                }
                            }
                        }else{
                            criteria.andEqualTo(field.getName(), field.get(model));
                        }
                    }
                }
            }else{
                isDelete=true;
            }
            if(isDelete){
                criteria.andEqualTo("isDelete", false);
            }
            return mapper.selectByCondition(condition);
        } catch (ReflectiveOperationException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<T> findValidDeleteAll(Page<T> page) {
        try {
            T model = page.getModel();
            Condition condition = new Condition(modelClass);
            Example.Criteria criteria = condition.createCriteria();
            boolean isDelete=false;
            if (model != null) {
                Field[] fields = modelClass.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    if ("isDelete".equals(field.getName())) {
                        isDelete=true;
                        continue;
                    }
                    if(field.get(model)!=null){
                        if (field.getType() == String.class){
                            String fieldValue = (String) field.get(model);
                            if (StringUtils.isNotBlank(fieldValue)) {
                                if (fieldValue.contains("%")) {
                                    criteria.andLike(field.getName(), fieldValue);
                                } else {
                                    criteria.andEqualTo(field.getName(), fieldValue);
                                }
                            }
                        }else{
                            criteria.andEqualTo(field.getName(), field.get(model));
                        }
                    }
                }
            }else{
                isDelete=true;
            }
            Map<String, String> params = page.getParams();
            if(params!=null){
                for (String key : params.keySet()) {
                    if("orderBy".equals(key)){
                        condition.setOrderByClause(params.get(key));
                    }else if(key.contains("or ")) {
                        criteria.orCondition(key.replace("or ",""), params.get(key));
                    }else{
                        criteria.andCondition(key, params.get(key));
                    }
                }
            }
            if(isDelete){
                criteria.andEqualTo("isDelete", false);
            }
            return mapper.selectByCondition(condition);
        } catch (ReflectiveOperationException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
