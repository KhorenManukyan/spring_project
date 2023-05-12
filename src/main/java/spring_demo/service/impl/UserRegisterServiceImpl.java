package spring_demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring_demo.model.UserRegister;
import spring_demo.repository.UserRegisterRepository;
import spring_demo.service.UserRegisterService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRegisterServiceImpl implements UserRegisterService {
    private final UserRegisterRepository userRegisterRepository;

    @Override
    public void save(UserRegister userRegister) {
        userRegisterRepository.save(userRegister);
    }

    @Override
    public UserRegister update(UserRegister userRegister, int id) {
        Optional<UserRegister> userById = userRegisterRepository.findById(id);
        if (userById.isPresent()) {
            UserRegister updatedUserRegister = userById.get();
            if (userRegister.getName() != null) {
                updatedUserRegister.setName(userRegister.getName());
            }
            if (userRegister.getPassword() != null) {
                updatedUserRegister.setPassword(userRegister.getPassword());
            }
            return userRegisterRepository.save(updatedUserRegister);
        } else {
            return null;
        }
    }

    @Override
    public UserRegister getUserById(int id) {
        return userRegisterRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserRegister> getAllUsers() {
        return userRegisterRepository.findAll();
    }

    @Override
    public UserRegister deleteUserById(int id) {
        UserRegister userRegister = getUserById(id);
        if (userRegister != null) {
            userRegisterRepository.delete(userRegister);
        }
        return userRegister;
    }

    @Override
    public void deleteAllUsers() {
        userRegisterRepository.deleteAll();
    }
}
