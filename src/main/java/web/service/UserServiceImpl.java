package web.service;

import org.springframework.stereotype.Component;
import web.dao.UserDao;
import web.dao.UserDaoImpl;
import web.model.User;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoImpl();


    @Override
    public List<User> showUsers() {
        return userDao.showUsers();
    }

    public void save(User user) {
        userDao.addUser(user);
    }

    @Override
    public User refactorUser(int id) {
        return userDao.refactorUser(id);
    }
}
