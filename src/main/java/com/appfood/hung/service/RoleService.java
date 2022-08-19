package com.appfood.hung.service;


import com.appfood.hung.model.Role;

import com.appfood.hung.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Role save(Role o) {
        return roleRepository.save(o);
        /*new ResponseDto(1, o.getId() == null ? "Tạo mới thành công" : "Cập nhật thành công");*/
    }

    public List<Role> findAll() {

        return roleRepository.findAll();
    }
}
