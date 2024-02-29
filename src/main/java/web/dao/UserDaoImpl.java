package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import web.config.DatabaseConfig;
import web.model.User;
import web.model.UsersList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {


    private DatabaseConfig databaseConfig = new DatabaseConfig();

//    public UserDaoImpl(DatabaseConfig databaseConfig) {
//        this.databaseConfig = databaseConfig;
//    }

    public UserDaoImpl() {

    }

    @Override
    public List<User> showUsers() {
        return databaseConfig.showUsers();
    }

    @Override
    public void addUser(User user) {
        databaseConfig.saveUser(user);

       // databaseConfig.entityManagerFactory().createNativeEntityManager().persist(user);
    }

    @Override
    public User refactorUser(int id) {
        return databaseConfig.refactorUser(id);
    }
}
