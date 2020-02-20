package com.wuyong.dao;

import com.wuyong.domain.Article;
import com.wuyong.domain.Category;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface ArticleDao {
    //查询所有文章
    List<Article>getAllArticle();
    //获取总记录数
    Integer getTotalCount(DetachedCriteria detachedCriteria);

    List<Article> getPageData(DetachedCriteria detachedCriteria, Integer index, Integer pageSize);
    //删除文章
    void delete(Article article);

    List<Category> getCategory(Integer parentid);

    void save(Article article);

    Article getOneArticle(Integer article_id);

    void update(Article article);
}
