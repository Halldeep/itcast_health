package com.zhang.dao;

import com.github.pagehelper.Page;
import com.zhang.pojo.CheckGroup;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CheckGroupDao {

    void add(CheckGroup checkGroup);
    void setCheckGroupAndCheckItem(Map map);
    Page<CheckGroup> findByCondition(String queryString);
    CheckGroup findById(Integer id);
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);
    void edit(CheckGroup checkGroup);
    void deleteAssocication(Integer id);
}
