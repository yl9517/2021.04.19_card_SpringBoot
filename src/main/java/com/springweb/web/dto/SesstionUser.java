package com.springweb.web.dto;

import com.springweb.domain.user.Role;
import com.springweb.domain.user.User;
import lombok.Getter;

@Getter
public class SesstionUser {
    private String name;
    private String email;
    private Role Role;

    public SesstionUser(User user){
        this.name=user.getName();
        this.email= user.getEmail();
        this.Role = user.getRole();
    }

}
