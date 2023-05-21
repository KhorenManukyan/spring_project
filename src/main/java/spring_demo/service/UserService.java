package spring_demo.service;

import spring_demo.model.User;
import spring_demo.model.enums.Category;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void save(User user);

    User update(User user, int id);

    User getUserById(int id);

    List<User> getAllUsers();

    boolean existsById(int id);

    long count();

    User deleteUserById(int id);

    User delete(User user);

    void deleteAllUsers();

    Optional<User> findByName(String name);

    List<User> findAllByCategory(Category category);
}
