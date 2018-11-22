package com.cqut.service;

import com.cqut.entity.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    public void register(User user) throws Exception;

    /*传入User做权限校验*/
    public void delete(Long userId, User curUser) throws Exception;

    public void update(User user, User curUser) throws Exception;

    public User selectByUsername(String userName) throws Exception;

    public boolean checkPassword(HttpServletRequest request, String valCode, User user) throws Exception;
}
