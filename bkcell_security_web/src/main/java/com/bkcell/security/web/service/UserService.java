package com.bkcell.security.web.service;

import com.bkcell.security.generator.pojo.User;
import com.bkcell.security.web.vo.user.UserQuery;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * get*获取一个 find*获取列表 save* 新增 update*更新 list*获取全部列表 page 获取分页列表 其他写成具体业务意义的方法
 */
public interface UserService {
    User getByUserName(String username);

    PageInfo<Map<String, Object>> page(UserQuery query);

    boolean login(String username, String password);

    User getById(Integer userId);

    boolean validUserName(Integer userId, String userName);

    boolean save(User user, Integer[] roleId);

    boolean deleteById(Integer userId);

    boolean changePw(Integer userId, String password);

    boolean validPw(String username, String password);
}
