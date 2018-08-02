package com.bkcell.security.web.service.impl;

import com.bkcell.security.generator.pojo.User;
import com.bkcell.security.shiro.ShiroCrypt;
import com.bkcell.security.web.dao.UserDao;
import com.bkcell.security.web.service.UserService;
import com.bkcell.security.web.vo.user.UserQuery;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User getByUserName(String username) {
        return userDao.selectByUserName(username);
    }

    @Override
    public PageInfo<Map<String, Object>> page(UserQuery query) {
        PageHelper.startPage(query.getPageNumber(), query.getPageSize());
        List<Map<String, Object>> users = userDao.selectByQuery(query);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }

    @Override
    public boolean login(String username, String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(false);
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
            return true;
        } catch (AuthenticationException e) {
            token.clear();
            return false;
        }
    }

    @Override
    public User getById(Integer userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    @Override
    public boolean validUserName(Integer userId, String userName) {
        int count = userDao.validUserName(userName, userId);
        return count == 0;
    }

    @Transactional
    @Override
    public boolean save(User user, Integer[] roleId) {
        if (user.getUserid() != null && user.getUserid() != 0) {
            userDao.updateByPrimaryKeySelective(user);
        } else {
            user.setPassword(ShiroCrypt.encrypt("000000"));
            userDao.insertSelective(user);
        }
        assginRole(user.getUserid(), roleId);
        return true;
    }

    private void assginRole(Integer userId, Integer[] roleId) {
        userDao.deleteRole(userId);
        if (roleId != null && roleId.length > 0) {
            userDao.insertRole(userId, roleId);
        }
    }

    @Transactional
    @Override
    public boolean deleteById(Integer userId) {
        userDao.deleteRole(userId);
        int delete = userDao.deleteByPrimaryKey(userId);
        return delete == 1;
    }

    /**
     * 修改密码
     *
     * @param userId
     * @param password
     * @return
     */
    @Override
    public boolean changePw(Integer userId, String password) {
        User user = getById(userId);
        if (user != null) {
            user.setPassword(ShiroCrypt.encrypt(password));
            return userDao.updateByPrimaryKeySelective(user) == 1;
        }
        return false;
    }

    @Override
    public boolean validPw(String username, String password) {
        User user = userDao.selectByUserName(username);
        String encrypt = ShiroCrypt.encrypt(password);
        return encrypt.equals(user.getPassword());
    }
}