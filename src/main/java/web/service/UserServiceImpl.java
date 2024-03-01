package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.dao.UserDao;
import web.dao.UserDaoImpl;
import web.model.User;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> showUsers() {
        return userDao.showUsers();
    }

    public void save(User user) {
        userDao.addUser(user);
    }

    @Override
    public User findUserById(int id) {
        return userDao.findUserById(id);
    }

    @Override
    public void update(int id, User updatedUser) {
        userDao.update(id, updatedUser);
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }
}
