package repo;

import model.User;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserRepo implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    public User findUserById(long id) {
        return entityManager.find(User.class, id);
    }

    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    public void deleteUserById(long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    public void updateUser(User user) {
        entityManager.merge(user);
    }
}



