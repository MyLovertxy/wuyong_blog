package com.wuyong.web;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wuyong.domain.User;
import com.wuyong.service.LoginService;

public class LoginAction extends ActionSupport implements ModelDriven<User> {
    private User user=new User();
    @Override
    public User getModel() {
        return user;
    }
    //注入业务层
    private LoginService loginService;

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    public String login(){
        System.out.println("来啦");
        System.out.println(user);
        User resUser = loginService.login(user);
        if(resUser==null){
            this.addActionError("用户名或密码错误");
            return LOGIN;
        }else {
            ActionContext.getContext().getSession().put("curUser",resUser);
            return SUCCESS;
        }
    }
    public String loginout(){
        System.out.println("loginout----");
        ActionContext.getContext().getSession().remove("curUser");
        return "login_out";
    }
}
