package com.cqut.dao;

import com.cqut.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    //TODO Something confusing
    public User selectUserByName(String username) throws Exception;

    public User selectById(User user) throws Exception;

    public List<User> selectUserByInfo(User user) throws Exception;

    public void deleteUserByIdOrName(User user) throws Exception;

    public void update(User user) throws Exception;

    //TODO return long id
    public void insert(User user) throws Exception;

    public Long count() throws Exception;


}
