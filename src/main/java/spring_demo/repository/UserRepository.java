package spring_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_demo.model.User;
import spring_demo.model.enums.Category;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByCategory(Category category);

    Optional<User> findByName(String name);
}
