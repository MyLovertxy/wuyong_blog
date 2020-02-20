package com.wuyong.web;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wuyong.domain.User;
import com.wuyong.service.AccountService;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;

import java.util.List;

public class AccountAction extends ActionSupport implements ModelDriven<User> {
    @Setter
    private User user=new User();
    @Override
    public User getModel() {
        return user;
    }
    @Setter
    private AccountService accountService;
    public String list(){
        System.out.println("account-list--------");
        List<User> list= accountService.getAllAccount();
        ActionContext.getContext().getValueStack().set("userLsit",list);
        System.out.println(list);
        return "list";
    }
    public String add(){
        System.out.println("userAdd--------");
        System.out.println(user);
        accountService.sava(user);
        return "listAction";
    }
    public String delete(){
        accountService.delete(user);
        return "listAction";
    }
    public String update(){
        System.out.println("account_update------");
        System.out.println(user);
        accountService.update(user);
        return "listAction";
    }

}
