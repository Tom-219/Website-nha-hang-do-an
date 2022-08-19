package com.appfood.hung.payload.dto;
import com.appfood.hung.model.Role;
import com.appfood.hung.model.User;
import lombok.Data;


@Data
public class UserDto {

    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private String confirmEmail;

    private String confirmPassword;

    private Boolean terms;

}
