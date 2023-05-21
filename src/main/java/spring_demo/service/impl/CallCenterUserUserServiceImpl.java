package spring_demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring_demo.model.CallCenterUser;
import spring_demo.repository.CallCenterUserRepository;
import spring_demo.service.CallCenterUserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CallCenterUserUserServiceImpl implements CallCenterUserService {
    private final CallCenterUserRepository callCenterUserRepository;

    @Override
    public void save(CallCenterUser callCenterUser) {
        callCenterUserRepository.save(callCenterUser);
    }

    @Override
    public CallCenterUser edit(CallCenterUser callCenterUser, int id) {
        Optional<CallCenterUser> callerId = callCenterUserRepository.findById(id);
        if (callerId.isPresent()) {
            CallCenterUser updatedCaller = callerId.get();
            if (callCenterUser.getTime() != null) {
                updatedCaller.setTime(callCenterUser.getTime());
            }

            if (callCenterUser.getDate() != null) {
                updatedCaller.setDate(callCenterUser.getDate());
            }

            if (callCenterUser.getName() != null) {
                updatedCaller.setName(callCenterUser.getName());
            }
            return callCenterUserRepository.save(updatedCaller);
        } else {
            return null;
        }
    }

    public Optional<CallCenterUser> getUserById(int id) {
        return callCenterUserRepository.findById(id);
    }

    @Override
    public CallCenterUser delete(CallCenterUser callCenterUser) {
        if (callCenterUser != null) {
            callCenterUserRepository.delete(callCenterUser);
        }
        return callCenterUser;
    }

    @Override
    public List<CallCenterUser> findAllByLocalDate(LocalDate date) {
        return callCenterUserRepository.findAllByDate(date);
    }

    @Override
    public List<CallCenterUser> findAll() {
        return callCenterUserRepository.findAll();
    }
}
