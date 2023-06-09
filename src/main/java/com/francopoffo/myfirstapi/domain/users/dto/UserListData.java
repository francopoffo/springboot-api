package com.francopoffo.myfirstapi.domain.users.dto;

import com.francopoffo.myfirstapi.domain.users.User;

import java.math.BigInteger;

public record UserListData(BigInteger id, String name, String email) {

    public UserListData(User user){
        this(user.getId(), user.getName(), user.getEmail());
    }
}
