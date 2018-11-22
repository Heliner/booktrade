package MybatisTest;

import com.cqut.dao.UserMapper;
import com.cqut.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.IOException;

public class UserTest {
    @Test
    public void testSelectById() {

    }

    @Test
    public void testInsert() throws IOException {
        User user = new User();
        user.setId(1L);
        user.setName("hfl");
        user.setUsername("这是我的用户名");
        user.setPassword("password");
        SqlSession sqlSession = SqlSessionFactoryUtil.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//        userMapper.insert(user);
    }
}
