package spring_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_demo.model.Permission;
import spring_demo.model.UserRegister;

public interface PermissionRepository extends JpaRepository<UserRegister, Integer> {

    public void save(Permission permission);

}
