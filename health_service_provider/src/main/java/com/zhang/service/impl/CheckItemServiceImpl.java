package com.zhang.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhang.dao.CheckItemDao;
import com.zhang.entity.PageResult;
import com.zhang.entity.QueryPageBean;
import com.zhang.pojo.CheckItem;
import com.zhang.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 检查项服务
 */
@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {

    //注入DAO对象
    @Autowired
    private CheckItemDao checkItemDao;

    //新增检查项
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    //检查项分页查询
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        //完成分页查询是基于Mybatis框架提供的分页助手插件完成
        //此方法底层是基于线程绑定的形式实现
        PageHelper.startPage(currentPage,pageSize);
        Page<CheckItem> page = checkItemDao.selectByCondition(queryString);
        long total = page.getTotal();
        List<CheckItem> rows = page.getResult();
        return new PageResult(total,rows);
    }

    //根据id删除检查项
    public void deleteById(Integer id) {
        //判断当前检查项是否关联到检查组
        long countByCheckItemId = checkItemDao.findCountByCheckItemId(id);
        if(countByCheckItemId > 0){
            //当前检查项已经关联到检查组，不允许删除
            throw new RuntimeException("当前检查项已经关联到检查组，不允许删除");
        }
        checkItemDao.deleteById(id);
    }

    public void edit(CheckItem checkItem) {
        checkItemDao.edit(checkItem);
    }

    public CheckItem findById(Integer id) {
        CheckItem byId = checkItemDao.findById(id);
        return byId;
    }
}
