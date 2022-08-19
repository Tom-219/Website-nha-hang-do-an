package com.appfood.hung.payload.request;

import lombok.Data;

@Data
public class UserRegistrationReq {

    private String firstName;

    private String lastName;

    private String password;

    private String confirmEmail;

    private String confirmPassword;

    private Boolean terms;

    private String email;




}
