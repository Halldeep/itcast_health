package com.zhang.dao;

import com.github.pagehelper.Page;
import com.zhang.pojo.CheckItem;
import org.apache.ibatis.annotations.*;

import javax.ws.rs.DELETE;
import java.util.List;

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

    @Select("SELECT * " +
            "FROM t_checkitem " +
            "WHERE id = #{id}")
    CheckItem findById(Integer id);

    @Select("SELECT * " +
            "FROM t_checkitem ")
    List<CheckItem> findAll();

    @Update({"<script>","update t_checkitem ",
            "<set>",
                "<if test=\" name != null \">",
                    "name = #{name},",
                "</if>",
                "<if test=\" sex != null \">",
                    "sex = #{sex},",
                "</if>",
                "<if test=\" code != null \">",
                    "code = #{code},",
                "</if>",
                "<if test=\" age != null \">",
                    "age = #{age},",
                "</if>",
                "<if test=\" price != null \">",
                    "price = #{price},",
                "</if>",
                "<if test=\" type != null \">",
                    "type = #{type},",
                "</if>",
                "<if test=\" attention != null \">",
                    "attention = #{attention},",
                "</if>",
                "<if test=\" remark != null \">",
                    "remark = #{remark},",
                "</if>",
            "</set>",
            "where id = #{id} ",
            "</script>"})
    void edit(CheckItem checkItem);
}
