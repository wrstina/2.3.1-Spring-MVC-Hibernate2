package repo;

import model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    User save(User user);
    User findUserById(long id);
    List<User> findAll();
    void deleteUserById(long id);
    void updateUser(User user);
}

