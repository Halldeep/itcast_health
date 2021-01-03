package com.zhang.dao;

import com.github.pagehelper.Page;
import com.zhang.pojo.CheckItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CheckItemDao {
    @Insert("INSERT INTO t_checkitem(code,name,sex,age,price,type,remark,attention) " +
            "values " +
            "(#{code},#{name},#{sex},#{age},#{price},#{type},#{remark},#{attention})")
    void add(CheckItem checkItem);

    @Select({"<script>","SELECT * FROM t_checkitem ",
            "<if test=\"value != null and value.length > 0\">",
                "where code = #{value} or name = #{value}",
            "</if>",
            "</script>"})
    Page<CheckItem> selectByCondition(String value);
}
