package com.wuyong.web;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.wuyong.domain.User;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;

public class LoginInterceptor extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        System.out.println("拦截器生效");
        //判断用户是否登录
        User user =(User) ServletActionContext.getRequest().getSession().getAttribute("curUser");
        if(user==null){
            ActionSupport action =(ActionSupport) actionInvocation.getAction();
            action.addActionError("你还没有登录");
            return "login";
            //没有登录
        }else {
            //对请求的方法进行放行
            return actionInvocation.invoke();
        }

    }
}
