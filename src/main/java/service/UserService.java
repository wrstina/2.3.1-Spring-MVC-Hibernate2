package service;

import org.springframework.stereotype.Service;
import repo.UserRepository;
import model.User;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void deleteUserById(long id) {
        userRepository.deleteUserById(id);
    }

    @Transactional
    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    @Transactional(readOnly = true)
    public User findUserById(long id) {
        return userRepository.findUserById(id);
    }
}
