package com.wuyong.service.impl;

import com.wuyong.dao.CategoryDao;
import com.wuyong.domain.Category;
import com.wuyong.service.CategoryService;
import lombok.Setter;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Setter
    private CategoryDao categoryDao;
    @Override
    public void save(Category category) {
        System.out.println("调用业务层");
//        调用dao
        categoryDao.save(category);
    }

    @Override
    public List<Category> getAllCategory() {
        //调用dao查询所有分类
        List<Category> list=categoryDao.getAllCategory();
        return list;
    }

    @Override
    public Category getOneCategory(Integer cid) {
        Category category=categoryDao.getOneCategory(cid);
        return category;
    }

    @Override
    public void update(Category category) {
        System.out.println("updaye业务层");
        categoryDao.update(category);
    }

    @Override
    public void delete(Category category) {
        categoryDao.delete(category);
    }
}
