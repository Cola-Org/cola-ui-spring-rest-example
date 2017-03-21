package com.colaui.example.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.colaui.example.dao.MenuDao;
import com.colaui.example.model.Menu;
import com.colaui.example.model.Message;
import com.colaui.example.model.User;

@RestController
public class FrameController {
    @Autowired
    private MenuDao menuDao;

    @RequestMapping(value = "/menus", method = RequestMethod.GET)
    public Collection<Menu> menus() {
        Criteria criteria = menuDao.createCriteria();
        Criterion lastRest = Restrictions.isNull("parent");
        criteria.add(lastRest);
        return menuDao.find(criteria);
    }

    @RequestMapping("message/pull")
    public List<Message> pull() {
        List<Message> messages = new ArrayList<Message>();
        Message message = new Message();
        message.setContent("20");
        message.setType("message");
        messages.add(message);

        message = new Message();
        message.setContent("26");
        message.setType("task");
        messages.add(message);
        return messages;
    }

    @RequestMapping("user/detail")
    public User userDetail() {
        User user = new User();
        user.setAvatar("./resources/images/avatars/alex.png");
        return user;
    }
}
