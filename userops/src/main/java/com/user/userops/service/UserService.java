package com.user.userops.service;

import com.user.userops.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    public static ArrayList<User> users=new ArrayList<>();
    public static int id=0;

    static {
        users.add(new User(++id, "Roger", LocalDate.now().minusYears(20)));
        users.add(new User(++id, "Rafa", LocalDate.now().minusYears(18)));
        users.add(new User(++id, "Novak", LocalDate.now().minusYears(15)));
    }



    public List<User> retrieveAllUsers() {
        return users;
    }

    public User getUser(int userId){

       for (User user:users){
           if(user.getId()==userId)return user;
       }
       return null;
    }

    public boolean IsUserFound(String userName){

        for (User user:users){
            if( (user.getName() == userName) )
                return true;
        }
        return false;
    }
    public User newUser(User user){

        //ensure dob is correct formatting and not a future date
        //ensure name is not empty or of a certain length
        //ensure coming new-user do not exists already
        User newUser = User.builder().id(++id).name(user.getName()).dob(user.getDob()).build();
        users.add(newUser);
        return newUser;
    }

    public User updateUser(int userId, User user){

        //how to handle additional validations like name is not empty OR a certain length
        //hot to handle additional validations like dob is not empty OR proper format OR not in the future.
        if (user.getName().isEmpty()) return null;
        if (user.getName().length() > 20) return null;
        if (user.getDob() == null) return null;
        if (user.getDob().toString().isEmpty()) return null;
        if (user.getDob().isAfter(LocalDate.now())) return null;

        User findUser = getUser(userId);
        if (findUser!=null) {
            findUser.setName(user.getName());
            findUser.setDob(user.getDob());
            return findUser;
        }
        return null;
    }

    public boolean deleteUser(int userId){

        for (User user:users){
            if(user.getId()==userId) {
                users.remove(user);
                return true;
            }
        }
        return false;
    }


}
