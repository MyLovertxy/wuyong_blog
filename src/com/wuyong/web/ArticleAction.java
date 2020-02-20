package com.wuyong.web;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wuyong.domain.Article;
import com.wuyong.domain.Category;
import com.wuyong.domain.PageBean;
import com.wuyong.service.ArticleService;
import lombok.Setter;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ArticleAction extends ActionSupport implements ModelDriven<Article> {
    @Setter
    private Article article=new Article();
    @Override
    public Article getModel() {
        return article;
    }
    @Setter
    private ArticleService articleService;
    public String list(){
        System.out.println("articlelist-----");
        //调用业务层
        List<Article> allArticle = articleService.getAllArticle();
        System.out.println(allArticle);
        //把数值存到值栈当中，转发到jsp
        ActionContext.getContext().getValueStack().set("allArticle",allArticle);
        return "list";
    }
    @Setter
    private Integer currPage=1;
    @Setter
    private String keyWord;
    public String pageList(){
        System.out.println("pageList------");
        System.out.println(currPage);
        System.out.println(keyWord);
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Article.class);
        //设置条件
        if(keyWord!=null){
            detachedCriteria.add(Restrictions.like("article_title","%"+keyWord+"%"));
        }
        //调用业务层
        PageBean pageBean=articleService.getPageData(detachedCriteria,currPage,4);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "list";
    }
    //删除
    public String delete(){
        articleService.delete(article);
        return "listres";
    }
    @Setter
    private Integer parentid;
    public String getCategory() throws IOException {
        System.out.println("getCategory------"+parentid);
        List<Category> list=articleService.getCategory(parentid);
        System.out.println(list);
        //把查询的结果转成json
        JSONArray jsonArray=JSONArray.fromObject(list,new JsonConfig());
        System.out.println(jsonArray);
        //响应给页面设置编码
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
        return null;
    }
    //添加文章
    /**
     * 文件上传提供的三个属性:
     */
    @Setter
    private String uploadFileName; // 文件名称
    @Setter
    private File upload; // 上传文件
    @Setter
    private String uploadContentType; // 文件类型
    public String add() throws IOException {
        if(upload!=null){
            //上传文件、确定上传的路径
            String path=ServletActionContext.getServletContext().getRealPath("/upload");
            //随机生成文件名称
            //1.先获取文件的扩展名
            int index = uploadFileName.lastIndexOf(".");
            String etx = uploadFileName.substring(index);
            System.out.println(etx);
            //随机生成文件名拼接扩展名    避免文件名重复
            String uuid = UUID.randomUUID().toString();
            System.out.println(uuid);
            String uuidFileName = uuid.replace("-", "") + etx;
            System.out.println(uuidFileName);
            //确保有上传的路径
            File file=new File(path);
            if(!file.exists()){
                file.mkdirs();
            }
            //拼接新文件路径
            File desFile = new File(path + "/" + uuidFileName);
            //文件上传
            FileUtils.copyFile(upload,desFile);
            //设置图片
            article.setArticle_pic(uuidFileName);
        }
        article.setArticle_time(new Date().getTime());
        System.out.println(article);
        articleService.save(article);
        return "listres";
    }
    public String edit(){
        System.out.println(article.getArticle_id());
        Article resarticle=articleService.getOneArticle(article.getArticle_id());
        System.out.println(resarticle);
        //把查询的数据放到值栈中
        ActionContext.getContext().getValueStack().push(resarticle);
        return "edit";
    }
    public String update() throws IOException {
        System.out.println("--------");
        //判断有没有新上传的图片
        if(upload!=null){
            //获取图片名称
            String picname = article.getArticle_pic();
            //上传文件、确定上传的路径
            String path=ServletActionContext.getServletContext().getRealPath("/upload");
            //删除原有的图片
            if (picname != null || "".equals(picname)) {
                File file = new File(path + picname);
                file.delete();
            }

            //随机生成文件名称
            //1.先获取文件的扩展名
            int index = uploadFileName.lastIndexOf(".");
            String etx = uploadFileName.substring(index);
            System.out.println(etx);
            //随机生成文件名拼接扩展名    避免文件名重复
            String uuid = UUID.randomUUID().toString();
            System.out.println(uuid);
            String uuidFileName = uuid.replace("-", "") + etx;
            System.out.println(uuidFileName);
            //确保有上传的路径
            File file=new File(path);
            if(!file.exists()){
                file.mkdirs();
            }
            //拼接新文件路径
            File desFile = new File(path + "/" + uuidFileName);
            //文件上传
            FileUtils.copyFile(upload,desFile);
            //设置图片
            article.setArticle_pic(uuidFileName);
        }
        article.setArticle_time(System.currentTimeMillis());
        articleService.update(article);
        return "listres";
    }
}
