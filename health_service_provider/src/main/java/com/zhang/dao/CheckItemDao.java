package com.zhang.dao;

import com.github.pagehelper.Page;
import com.zhang.pojo.CheckItem;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import javax.ws.rs.DELETE;

@Mapper
public interface CheckItemDao {
    @Insert("INSERT INTO t_checkitem(code,name,sex,age,price,type,remark,attention) " +
            "VALUES " +
            "(#{code},#{name},#{sex},#{age},#{price},#{type},#{remark},#{attention})")
    void add(CheckItem checkItem);

    @Select({"<script>","SELECT * FROM t_checkitem ",
            "<if test=\"value != null and value.length > 0\">",
                "WHERE code = #{value} or name = #{value}",
            "</if>",
            "</script>"})
    Page<CheckItem> selectByCondition(String value);

    @Select("SELECT count(*) " +
            "FROM t_checkgroup_checkitem " +
            "WHERE checkitem_id = #{id}")
    long findCountByCheckItemId(Integer id);

    @Delete("DELETE FROM t_checkitem where id = #{id}")
    void deleteById(Integer id);
}
