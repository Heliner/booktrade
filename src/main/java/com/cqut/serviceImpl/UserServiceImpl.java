package com.cqut.serviceImpl;

import com.cqut.common.OperationTip;
import com.cqut.dao.UserMapper;
import com.cqut.entity.User;
import com.cqut.exception.CodeMsg;
import com.cqut.exception.GlobalException;
import com.cqut.exception.IllegalOperationException;
import com.cqut.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import static com.cqut.util.SessionUtil.HttpUtil.getSession;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    public boolean checkPassword(String username, String password) throws Exception {
        User user = userMapper.selectUserByName(username);
        return password.equals(user.getPassword());
    }

    public void register(User user) throws Exception {
        userMapper.insert(user);
        System.out.println("user id :" + user.getId());
    }

    //TODO 加入管理权限校验
    public void delete(Long userId, User curUser) throws Exception {
        if (curUser.getId() != userId)
            throw new IllegalOperationException(OperationTip.LIMITS_OF_AUTHORITY);
        userMapper.deleteUserByIdOrName(curUser);
    }

    public void update(User user, User curUser) throws Exception {
        if (!(curUser.getId() == curUser.getId()))
            throw new IllegalOperationException(OperationTip.ILLEGAL_OPERATION);
        userMapper.update(user);
    }

    public User selectByUsername(String userName) throws Exception {
        User user = userMapper.selectUserByName(userName);
        if (user == null)
            throw new GlobalException(CodeMsg.USER_NOT_EXITS);
        return user;
    }

    @Override
// TODO   ERROR maybe
    public boolean checkPassword(HttpServletRequest request, String valCode, User user) throws Exception {
        User re = userMapper.selectUserByName(user.getUsername());
        if (valCode == null || !valCode.equals(getSession(request).getAttribute("imgVal")))
            throw new GlobalException(CodeMsg.IMG_VAL_ERROR);
        if (re == null || re.getPassword() == null || !re.getPassword().equals(user.getPassword()))
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        return true;
    }
}
