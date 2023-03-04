package com.user.userops.service;

import com.user.userops.model.Subscriber;
import com.user.userops.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SubscriberService {

    public static ArrayList<Subscriber> users=new ArrayList<>();
    public static int id=0;

    static {
        users.add(new Subscriber(++id, "milton.thomas@gmail.com", "Milton", "Thomas","dog"));
        users.add(new Subscriber(++id, "alex.lobo@gmail.com", "Alex", "Lobo","cat"));
    }

    public List<Subscriber> retrieveAllSubscribers() {
        return users;
    }

    public Subscriber newSubscriber(Subscriber user){

        //ensure dob is correct formatting and not a future date
        //ensure name is not empty or of a certain length
        //ensure coming new-user do not exists already
        Subscriber newUser = Subscriber.builder()
                .id(++id)
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .LastName(user.getLastName())
                .Avatar(user.getAvatar())
                .build();

        users.add(newUser);
        return newUser;
    }


}
