package spring_demo.service;

import spring_demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void save(User user);

    void update(User user, int id);

    User getById(int id);

    List<User> getAllUsers();

    boolean existsById(int id);

    long count();

    User deleteById(int id);

    User delete(User user);

    void deleteAll();

}
