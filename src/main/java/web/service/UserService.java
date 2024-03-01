package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    List<User> showUsers();

    public void save(User user);

    public User findUserById(int id);

    public void update(int id, User updatedUser);

    public void delete(int id);
}
