package com.appfood.hung.repository.facade;


import com.appfood.hung.model.User;

public interface AuthenticationFacade {

    long getCurrentUserId();

    User getCurrentUser();
}
