/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.zmn.census.common.core.service;

import cn.hutool.core.date.DateUtil;
import com.zmn.census.common.core.dao.MyMapper;
import com.zmn.census.common.core.model.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 通用接口
 * zhaozhirong
 * @param <T>
 */
public abstract class BaseService<T  extends BaseEntity> implements IService<T> {

    @Autowired
    protected MyMapper<T> myMapper;

    public Mapper<T> getMyMapper() {
        return myMapper;
    }



    @Override
    public T getById(Object key) {
        return myMapper.selectByPrimaryKey(key);
    }

    @Override
    public int save(T entity) {
        entity.setCreatedDate(DateUtil.date());
        entity.setModifiedDate(DateUtil.date());
        entity.setDeleted(0);
        return myMapper.insert(entity);
    }

    @Override
    public int saveNotNull(T entity) {
        entity.setDeleted(0);
        return myMapper.insertSelective(entity);
    }

    @Override
    public int delete(Integer id) {
        T baseEntity = this.getById(id);
        baseEntity.setDeleted(1);
        return myMapper.updateByPrimaryKeySelective(baseEntity);
    }

    @Override
    public int update(T entity) {
        return myMapper.updateByPrimaryKey(entity);
    }

    @Override
    public int updateNotNull(T entity) {
        return myMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public List<T> selectByExample(Object example) {
        return myMapper.selectByExample(example);
    }

    @Override
    public List<T> selectAll() {
        return myMapper.selectAll();
    }

    @Override
    public void saveList(List<T> entityList) {
        for (T t : entityList) {
            save(t);
        }
    }
}
