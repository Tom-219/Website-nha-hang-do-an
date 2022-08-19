package com.appfood.hung.service;

import com.appfood.hung.model.User;
import com.appfood.hung.payload.dto.UserDto;
import com.appfood.hung.payload.request.UserRegistrationReq;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationReq registration);

    User save(UserDto userDto);
}
