package com.zhang.dao;

import com.github.pagehelper.Page;
import com.zhang.pojo.CheckGroup;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;
import java.util.Map;

@Mapper
public interface CheckGroupDao {

    @Insert("insert into t_checkgroup(code,name,sex,helpCode,remark,attention) " +
            "values " +
            "(#{code},#{name},#{sex},#{helpCode},#{remark},#{attention})")
    @SelectKey(keyProperty = "id", resultType = int.class, before = false, statement = "select LAST_INSERT_ID()")
    void add(CheckGroup checkGroup);

    @Insert("insert into t_checkgroup_checkitem(checkgroup_id,checkitem_id) " +
            "values" +
            "(#{checkgroupId},#{checkitemId})")
    void setCheckGroupAndCheckItem(Map map);

    @Select({"<script>","SELECT * FROM t_checkgroup ",
            "<if test=\"value != null and value.length > 0\">",
                "WHERE code = #{value} or name = #{value} or helpCode = #{value}",
            "</if>",
            "</script>"})
    Page<CheckGroup> findByCondition(String value);

    CheckGroup findById(Integer id);
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);
    void edit(CheckGroup checkGroup);
    void deleteAssocication(Integer id);
}
