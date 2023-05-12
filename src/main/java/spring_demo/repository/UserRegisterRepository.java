package spring_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_demo.model.UserRegister;

public interface UserRegisterRepository extends JpaRepository<UserRegister, Integer> {
}
