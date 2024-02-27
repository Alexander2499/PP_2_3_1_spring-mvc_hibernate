package web.config;


import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import web.model.User;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@Component
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
public class DatabaseConfig {

    @Autowired
    private Environment env;

    @Transactional
    public void saveUser(User user) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

// Save the object to the database
        em.persist(user);

        transaction.commit();

        em.close();
        emf.close();
    }


//    @Transactional
//    public List<User> showUsers() {
//
//        String sql = "SELECT * FROM USERS";
//
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
//
//        EntityManager em = emf.createEntityManager();
//
//        EntityTransaction transaction = em.getTransaction();
//        transaction.begin();
//
//        Query query = em.createNativeQuery(sql);
//
//        List<User> list = query.getResultList();
//
//        transaction.commit();
//
//        em.close();
//        emf.close();
//
//        return list;
//    }

    @Transactional
    public List<User> showUsers() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager em = emf.createEntityManager();
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root);

            TypedQuery<User> query = em.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (Exception e) {
            // Handle exceptions (e.g., logging, rollback, etc.)
            e.printStackTrace();
            return Collections.emptyList();
        }
    }



    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("web");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(getHibernateProperties());
        return em;
    }


//    public Properties getHibernateProperties () {
//        try {
//            Properties properties = new Properties();
//            InputStream is = getClass().getClassLoader().getResourceAsStream("hibernate.properties");
//            properties.load(is);
//            System.out.println(properties);
//            return properties;
//        } catch (IOException e) {
//            throw new IllegalArgumentException("NE RABOTAET");
//        }
//    }

    public Properties getHibernateProperties() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("hibernate.properties")) {
            Properties properties = new Properties();
            properties.load(is);
            System.out.println(properties);
            is.close();
            return properties;
        } catch (IOException e) {
            throw new IllegalArgumentException("DOESN'T WORK");
        }
    }


    @Bean
    public DataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(env.getRequiredProperty("db.url"));
        ds.setDriverClassName(env.getRequiredProperty("db.driver"));
        ds.setUsername(env.getRequiredProperty("db.username"));
        ds.setPassword(env.getRequiredProperty("db.password"));
        return ds;
    }
}
