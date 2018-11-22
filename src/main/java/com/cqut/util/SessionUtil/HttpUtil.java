package com.cqut.util.SessionUtil;

import com.cqut.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class HttpUtil {
    static int count = 0;
    static String path = "";

    public static User getCurUser(HttpServletRequest request) {
        User user = new User();
        user.setId(1L);
        //fixme 测试完后修改
//        return (User) getSession(request).getAttribute("user");
        return user;
    }

    public static HttpSession getSession(HttpServletRequest request) {
        return request.getSession();
    }


    public static String updateProjectBasePath(HttpServletRequest request) {
        if (count % 128 == 0) {
            count = 0;
            path = request.getServletContext().getRealPath("");
        }
        count++;
        return path;
    }

    public static String getProjectBasePath() {
        return path;
    }
}
