package com.wuyong.web;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wuyong.domain.Article;
import com.wuyong.domain.Category;
import com.wuyong.domain.PageBean;
import com.wuyong.service.ArticleService;
import lombok.Setter;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class WebAction extends ActionSupport {
    @Setter
    private ArticleService articleService;
    @Setter
    private Integer currPage=1;
    @Setter
    private Integer parentid;
    @Setter
    private Integer cid;
    //前端分页
    public void getPageList() throws IOException {
        System.out.println("web------");
        System.out.println(parentid);
        //离线查询条件
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Article.class);
        if(parentid !=null){
            List<Category> category = articleService.getCategory(parentid);
            //构建一个数组
            Object[] cidArrays = new Object[category.size()];
            for (int i=0;i<category.size();i++){
                cidArrays[i]=category.get(i).getCid();
            }
            //设置条件
            System.out.println(Arrays.toString(cidArrays));
            detachedCriteria.add(Restrictions.in("category.cid",cidArrays));
        }
        if(cid!=null){
            detachedCriteria.add(Restrictions.eq("category.cid",cid));
        }
        //调用业务层
        PageBean pageBean=articleService.getPageData(detachedCriteria,currPage,2);
        //把查询的结果转成json
        JsonConfig jsonConfig = new JsonConfig();
        //文章里面关联了分类，懒加载先不会查出来，下面是把所有内容加载出来
        jsonConfig.setExcludes(new String[]{"handler","hibernateLazyInitializer"});
        JSONObject jsonArray= JSONObject.fromObject(pageBean,jsonConfig);
        System.out.println(jsonArray);
        //响应给页面设置编码
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
    }
    //根据id获取文章
    @Setter
    private Integer id;
    public void getDetail() throws IOException {
        Article oneArticle = articleService.getOneArticle(id);
        //把查询的结果转成json
        JsonConfig jsonConfig = new JsonConfig();
        //文章里面关联了分类，懒加载先不会查出来，下面是把所有内容加载出来
        jsonConfig.setExcludes(new String[]{"handler","hibernateLazyInitializer"});
        JSONObject jsonArray= JSONObject.fromObject(oneArticle,jsonConfig);
        //响应给页面设置编码
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
    }

}
