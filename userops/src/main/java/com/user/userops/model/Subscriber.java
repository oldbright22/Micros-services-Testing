package com.user.userops.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Data
public class Subscriber {

    private int id;
    private String email;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String LastName;
    private String Avatar;

}
