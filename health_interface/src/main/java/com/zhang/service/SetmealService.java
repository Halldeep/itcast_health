package com.zhang.service;


import com.zhang.entity.PageResult;
import com.zhang.entity.QueryPageBean;
import com.zhang.pojo.Setmeal;

public interface SetmealService {
    public void add(Setmeal setmeal, Integer[] checkgroupIds);
    public PageResult pageQuery(QueryPageBean queryPageBean);
}
