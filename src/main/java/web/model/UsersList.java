package web.model;

import java.util.ArrayList;
import java.util.List;

public class UsersList {
    public static List<User> users;
    private static int USERS_COUNT;
    public static List<User> createUsers() {
        User user1 = new User("Alexander","Korotkov", 124000);
        User user2 = new User("Vladimir","Krivenkov", 220000);
        User user3 = new User("Evgeny","Molochnikov", 230000);
        users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);

        return users;
    }
}
