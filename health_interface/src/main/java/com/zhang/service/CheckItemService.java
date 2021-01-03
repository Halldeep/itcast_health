package com.zhang.service;

import com.zhang.entity.PageResult;
import com.zhang.entity.QueryPageBean;
import com.zhang.pojo.CheckItem;

/**
 * 服务接口
 */
public interface CheckItemService {

    void add(CheckItem checkItem);

    PageResult pageQuery(QueryPageBean queryPageBean);
}
