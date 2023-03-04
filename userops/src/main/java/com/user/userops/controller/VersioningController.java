package com.user.userops.controller;

import com.user.userops.model.User;
import com.user.userops.model.UserV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class VersioningController {

    //VERSION based
    @GetMapping("/users/v1")
    public User getv1User(){
        return User.builder().id(1).name("Michael").
                dob(LocalDate.now()).build();
    }

    @GetMapping("/users/v2")
    public UserV2 getv2User(){
        UserV2.Name name = UserV2.Name.builder().
                firstName("Michael").lastName("Old").build();

        return UserV2.builder().id(1).name(name).
                dob(LocalDate.now().minusYears(18)).build();
    }

    //PARAM based
    @GetMapping(path = "/users", params = "version=v1")
    public User getv1UserParam(){
        return getv1User();
    }

    @GetMapping(path = "/users",params = "version=v2")
    public UserV2 getv2UserParam(){
        return getv2User();
    }


}
