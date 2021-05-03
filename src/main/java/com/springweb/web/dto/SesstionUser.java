package com.springweb.web.dto;

import com.springweb.domain.user.User;
import lombok.Getter;

@Getter
public class SesstionUser {
    private String name;
    private String email;

    public SesstionUser(User user){
        this.name=user.getName();
        this.email= user.getEmail();
    }

}
