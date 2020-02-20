package com.wuyong.dao;

import com.wuyong.domain.Category;

import java.util.List;

public interface CategoryDao {
    //保存分类
    public void save(Category category);
    //获取所有分类
    List<Category> getAllCategory();
    //根据id到数据库中查询分类
    Category getOneCategory(Integer cid);
    //修改分类
    void update(Category category);

    void delete(Category category);
}
