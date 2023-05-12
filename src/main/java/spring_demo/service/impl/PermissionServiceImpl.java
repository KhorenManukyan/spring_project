package spring_demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring_demo.model.Permission;
import spring_demo.model.UserRegister;
import spring_demo.repository.PermissionRepository;
import spring_demo.service.PermissionService;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;

    @Override
    public void save(Permission permission) {
        permissionRepository.save(permission);
    }
}
