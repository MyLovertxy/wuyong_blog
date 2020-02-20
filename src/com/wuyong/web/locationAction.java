package com.wuyong.web;

import com.opensymphony.xwork2.ActionSupport;

public class locationAction extends ActionSupport {
    public String left(){
        return "left";
    }
    public String top(){
        return "top";
    }
    public String addArticle(){
        return "addArticle";
    }
}
