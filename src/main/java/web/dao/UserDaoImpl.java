package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.config.DatabaseConfig;
import web.model.User;
import web.model.UsersList;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    private final EntityManager em;

    public UserDaoImpl() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
        em = emf.createEntityManager();
    }

//    @Override
//    public List<User> showUsers() {
//        try {
//            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
//            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
//            Root<User> root = criteriaQuery.from(User.class);
//            criteriaQuery.select(root);
//
//            TypedQuery<User> query = em.createQuery(criteriaQuery);
//            return query.getResultList();
//        } catch (Exception e) {
//            // Handle exceptions (e.g., logging, rollback, etc.)
//            e.printStackTrace();
//            return Collections.emptyList();
//        }
//    }

    @Override
    public List<User> showUsers() {
        return em.createQuery("from User",User.class).getResultList();
    }

    @Override
    public void addUser(User user) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
// Save the object to the database
        em.persist(user);

        transaction.commit();
       // databaseConfig.entityManagerFactory().createNativeEntityManager().persist(user);
    }

    @Override
    public User findUserById(int id) {
        User user = em.find(User.class , id);
        return user;
    }

    @Override
    @Transactional
    public void update(int id, User updatedUser) {
        User userToBeUpdated = findUserById(id);
        userToBeUpdated.setName(updatedUser.getName());
        userToBeUpdated.setLastName(updatedUser.getLastName());
        userToBeUpdated.setSalary(updatedUser.getSalary());

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

// Save the object to the database
        em.persist(userToBeUpdated);

        transaction.commit();
    }

    public void delete(int id){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        User user = findUserById(id);
        em.remove(user);

        transaction.commit();
    }
}
