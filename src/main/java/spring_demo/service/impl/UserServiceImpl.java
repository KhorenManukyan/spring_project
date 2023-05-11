package spring_demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring_demo.model.User;
import spring_demo.repository.UserRepository;
import spring_demo.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User update(User user, int id) {

        Optional<User> userById = userRepository.findById(id);
        if (userById.isPresent()) {
            User updatedUser = userById.get();
            if (user.getFirstName() != null) {
                updatedUser.setFirstName(user.getFirstName());
            }
            if (user.getLastName() != null) {
                updatedUser.setLastName(user.getLastName());
            }
            if (user.getEmail() != null) {
                updatedUser.setEmail(user.getEmail());
            }
            return userRepository.save(updatedUser);
        } else {
            return null;
        }
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).orElseGet(() -> null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean existsById(int id) {
        return userRepository.existsById(id);
    }

    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public User deleteUserById(int id) {
        User user = getUserById(id);
        if (user != null) {
            userRepository.delete(user);
        }
        return user;
    }

    @Override
    public User delete(User user) {
        if (user != null) {
            userRepository.delete(user);
        }
        return user;
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
}
