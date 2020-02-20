package com.wuyong.service;

import com.wuyong.domain.Category;

import java.util.List;

public interface CategoryService {
    //保存分类
    public void save(Category category);
    //获取所有分类的业务信息
    List<Category> getAllCategory();
    //根据id查询分类
    Category getOneCategory(Integer cid);
    //修改分类
    void update(Category category);
    //删除分类
    void delete(Category category);
}
