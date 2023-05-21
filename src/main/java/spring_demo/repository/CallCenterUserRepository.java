package spring_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_demo.model.CallCenterUser;

import java.time.LocalDate;
import java.util.List;

public interface CallCenterUserRepository extends JpaRepository<CallCenterUser, Integer> {
    List<CallCenterUser> findAllByDate(LocalDate date);

    List<CallCenterUser> findAll();

}
