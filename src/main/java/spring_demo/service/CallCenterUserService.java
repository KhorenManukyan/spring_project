package spring_demo.service;

import spring_demo.model.CallCenterUser;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CallCenterUserService {
    void save(CallCenterUser callCenterUser);

    CallCenterUser edit(CallCenterUser callCenterUser, int id);

    Optional<CallCenterUser> getUserById(int id);

    CallCenterUser delete(CallCenterUser callCenterUser);

    List<CallCenterUser> findAllByLocalDate(LocalDate date);

    List<CallCenterUser> findAll();
}
