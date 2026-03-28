package com.javitech.dindinapi.service.role;

import com.javitech.dindinapi.model.Role;
import com.javitech.dindinapi.repository.RoleRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(UUID id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role update(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteById(UUID id) {
        roleRepository.deleteById(id);
    }
}
