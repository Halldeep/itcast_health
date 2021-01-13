package com.zhang.dao;


import com.github.pagehelper.Page;
import com.zhang.pojo.Setmeal;

import java.util.Map;

public interface SetmealDao {
    void add(Setmeal setmeal);
    void setSetmealAndCheckGroup(Map map);
    Page<Setmeal> findByCondition(String queryString);
}
