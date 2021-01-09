package com.zhang.service;

import com.zhang.entity.PageResult;
import com.zhang.entity.QueryPageBean;
import com.zhang.pojo.CheckItem;

import java.util.List;

/**
 * 服务接口
 */
public interface CheckItemService {

    void add(CheckItem checkItem);

    PageResult pageQuery(QueryPageBean queryPageBean);

    List<CheckItem> findAll();

    void deleteById(Integer id);

    void edit(CheckItem checkItem);

    CheckItem findById(Integer id);
}
