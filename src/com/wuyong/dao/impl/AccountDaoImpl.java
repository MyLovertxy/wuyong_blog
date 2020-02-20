package com.wuyong.dao.impl;

import com.wuyong.dao.AccountDao;
import com.wuyong.domain.User;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class AccountDaoImpl extends HibernateDaoSupport implements AccountDao {

    @Override
    public List<User> getAllAccount() {
        System.out.println("userDao");
        DetachedCriteria detachedCriteria=DetachedCriteria.forClass(User.class);
        List<User> list = (List<User>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
        return list;
    }

    @Override
    public void save(User user) {
        this.getHibernateTemplate().save(user);
    }

    @Override
    public void delete(User user) {
        this.getHibernateTemplate().delete(user);
    }

    @Override
    public void update(User user) {
        this.getHibernateTemplate().update(user);
    }
}
