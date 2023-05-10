package spring_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_demo.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
