package com.user.userops.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@AllArgsConstructor
@Builder(toBuilder = true)
@NoArgsConstructor
@Data
public class UserV2 {

    private int id;
    private Name name;
    private LocalDate dob;

    @AllArgsConstructor
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @Data
    public static class Name{
        private String firstName;
        private String lastName;
    }

}
