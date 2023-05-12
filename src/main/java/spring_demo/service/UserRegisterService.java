package spring_demo.service;

import spring_demo.model.UserRegister;

import java.util.List;

public interface UserRegisterService {
    void save(UserRegister userRegister);

    UserRegister update(UserRegister userRegister, int id);

    UserRegister getUserById(int id);

    List<UserRegister> getAllUsers();

    UserRegister deleteUserById(int id);

    void deleteAllUsers();
}
