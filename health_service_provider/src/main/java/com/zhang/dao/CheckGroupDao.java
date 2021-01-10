package com.zhang.dao;

import com.github.pagehelper.Page;
import com.zhang.pojo.CheckGroup;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface CheckGroupDao {

    @Insert("INSERT INTO t_checkgroup(code,name,sex,helpCode,remark,attention) " +
            "VALUES " +
            "(#{code},#{name},#{sex},#{helpCode},#{remark},#{attention})")
    @SelectKey(keyProperty = "id", resultType = int.class, before = false, statement = "select LAST_INSERT_ID()")
    void add(CheckGroup checkGroup);

    @Insert("INSERT INTO t_checkgroup_checkitem(checkgroup_id,checkitem_id) " +
            "VALUES" +
            "(#{checkgroupId},#{checkitemId})")
    void setCheckGroupAndCheckItem(Map map);

    @Select({"<script>","SELECT * FROM t_checkgroup ",
            "<if test=\"value != null and value.length > 0\">",
                "WHERE code = #{value} or name = #{value} or helpCode = #{value}",
            "</if>",
            "</script>"})
    Page<CheckGroup> findByCondition(String value);

    @Select("SELECT * " +
            "FROM t_checkgroup " +
            "WHERE id = #{id}")
    CheckGroup findById(Integer id);

    @Select("SELECT checkitem_id " +
            "FROM t_checkgroup_checkitem " +
            "WHERE checkgroup_id = #{id}")
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    @Update({"<script>","update t_checkgroup ",
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
                    "<if test=\" helpCode != null \">",
                        "helpCode = #{helpCode},",
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
    void edit(CheckGroup checkGroup);

    @Delete("delete from t_checkgroup_checkitem where checkgroup_id = #{id}")
    void deleteAssocication(Integer id);
}
