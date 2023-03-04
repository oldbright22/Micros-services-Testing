package com.user.userops.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Data
public class User {

    private int id;
    private String name;
    private LocalDate dob;
}
