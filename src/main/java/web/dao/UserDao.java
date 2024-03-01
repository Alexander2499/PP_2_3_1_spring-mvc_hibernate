package web.dao;

import web.model.User;

import java.util.List;


public interface UserDao {
    List<User> showUsers();

    public void addUser(User user);

    public User findUserById(int id);

    public void update(int id, User updatedUser);

    public void delete(int id);
}
