package app.dao;

import app.model.Role;
import app.model.User;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
@EntityScan(basePackages = "app.model")
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        //language=hql
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(User user) {
        User deleteUser = entityManager.find(User.class, user.getId());
        entityManager.remove(deleteUser);
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByLogin(String login) {
        //language=JPQL
        List<User> userList = entityManager.createQuery("FROM User user WHERE user.login=:login", User.class)
                .setParameter("login", login).getResultList();
        return !userList.isEmpty() ? userList.get(0) : null;
    }

    @Override
    public List<Role> getAllRole() {
        //language=JPQL
        return entityManager.createQuery("FROM Role", Role.class).getResultList();
    }
}
