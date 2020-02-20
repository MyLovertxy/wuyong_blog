package com.wuyong.service.impl;

import com.wuyong.dao.ArticleDao;
import com.wuyong.domain.Article;
import com.wuyong.domain.Category;
import com.wuyong.domain.PageBean;
import com.wuyong.service.ArticleService;
import lombok.Setter;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Setter
    private ArticleDao articleDao;
    @Override
    public List<Article> getAllArticle() {
        System.out.println("articleservice-------");
        List<Article> list = articleDao.getAllArticle();
        return list;
    }

    @Override
    public PageBean getPageData(DetachedCriteria detachedCriteria, Integer currPage, int pageSize) {
        PageBean<Article> pageBean=new PageBean<>();
        //设置当前页
        pageBean.setCurrentPage(currPage);
        //一页多少数据
        pageBean.setPageSize(pageSize);
        //获取总记录数
        //查数据库
        Integer totalCount=articleDao.getTotalCount(detachedCriteria);
        pageBean.setTotalCount(totalCount);
        //设置总页数
        pageBean.setTotalPage(pageBean.getTotalPage());
        //设置数据库当前数据
        //查数据库
        List<Article> list=articleDao.getPageData(detachedCriteria,pageBean.getIndex(),pageBean.getPageSize());
        pageBean.setList(list);
        System.out.println(pageBean);
        //计算
        return pageBean;
    }

    @Override
    public void delete(Article article) {
        articleDao.delete(article);
    }

    @Override
    public List<Category> getCategory(Integer parentid) {
        List<Category>list=articleDao.getCategory(parentid);
        return list;
    }

    @Override
    public void save(Article article) {
        articleDao.save(article);
    }

    @Override
    public Article getOneArticle(Integer article_id) {
        Article resarticle=articleDao.getOneArticle(article_id);
        return resarticle;
    }

    @Override
    public void update(Article article) {
        articleDao.update(article);
    }
}
