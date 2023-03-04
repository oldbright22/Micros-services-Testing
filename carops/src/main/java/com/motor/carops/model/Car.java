package com.motor.carops.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@NoArgsConstructor
//By default is Singleton (one time object creation)
//For multiple instance object creation  = @Scope("prototype")
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@Component
@Scope("prototype")
public class Car {

    public Car(){
        System.out.println("car object created");
    }

    //	- Car (id,makeModel, company, price, makeYear, type=SUV,Sedan, hatchback)
    private int id;

    private String makeModel;

    private String company;

    private float price;

    private int makeYear;

    private String carType;

    private boolean hatchback;
}