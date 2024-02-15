package web.service;

import web.model.User;
import web.model.UsersList;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public List<User> showUsers() {
        List<User> users = UsersList.createUsers();
        return users;
    }

}
