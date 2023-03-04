package com.user.userops.controller;

import com.user.userops.model.Subscriber;
import com.user.userops.model.User;
import com.user.userops.service.UserService;
import com.user.userops.service.SubscriberService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api")
public class UserController {

    // dependency injection
    @Autowired
    UserService userService;
    SubscriberService subService;


    @GetMapping(path = "/users", produces = "application/json")
    public ResponseEntity<List<User>> getAllUsers(){

        return new ResponseEntity<List<User>>(userService.retrieveAllUsers(), OK);
    }


    // Assignment 2 :: get a user by query param
    @GetMapping(value = "/users/{userId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserByQueryParam(@PathVariable int userId){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        List<User> userList = new ArrayList<>();
        userList.add(userService.getUser(userId));
        if (!userList.isEmpty()) {
            map.put("status", 1);
            map.put("user", userList);
            return new ResponseEntity<>(map, OK);
        } else {
            map.clear();
            map.put("status", 0);
            map.put("message", "User is not found = "+String.valueOf(userId));
            return new ResponseEntity<>(map, NOT_FOUND);
        }

    }


    // get a specific user
    @GetMapping(path="/users/{userId}", produces = "application/json")
    public ResponseEntity<?> getUserById(@PathVariable int userId){

        Map<String, Object> map = new LinkedHashMap<String, Object>();
        List<User> userList = new ArrayList<>();
        userList.add(userService.getUser(userId));
        if (!userList.isEmpty()) {
            map.put("status", 1);
            map.put("user", userList);
            return new ResponseEntity<>(map, OK);
        } else {
            map.clear();
            map.put("status", 0);
            map.put("message", "User is not found = "+String.valueOf(userId));
            return new ResponseEntity<>(map, NOT_FOUND);
        }
        //if(user==null) throw new UserNotFoundException("user with id"+ userId+" not found");
        //return new ResponseEntity<>(user,HttpStatus.OK);
    }



    // create a new user
    // request body, context, url
    @SneakyThrows
    @PostMapping(path = "/create/user", consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> newUser(@RequestBody User user) {

            if (user==null) {
                return ResponseEntity.internalServerError().body(user);
            }

            if (user.getDob() == null || user.getName() == null) {
                return ResponseEntity.badRequest().build();
            }

            User newUser = userService.newUser(user);

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(newUser.getId())
                    .toUri();
            //return ResponseEntity.created(uri).build();
            return newUser == null ? ResponseEntity.internalServerError().body(newUser) : new ResponseEntity<User>(newUser, CREATED);
    }


    // update a user
    @PutMapping(path = "/users/update/{userId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updateUser(@PathVariable int userId, @RequestBody User user) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        List<User> userList = new ArrayList<>();

        //No body found, nothing to update
        if (user == null) {
            return ResponseEntity.internalServerError().body(user);
        }

        if (user.getDob() == null || user.getName() == null){
            return ResponseEntity.badRequest().build();
        }

        //confirm a valid userId, was passed in and if so, proceed to update user
        User foundUser = userService.getUser(userId);
        if (foundUser != null) {
            User updatedUser = userService.updateUser(userId,user);
            map.put("status", 1);
            map.put("user", updatedUser);
            return new ResponseEntity<>(map, OK);
        } else {
            map.clear();
            map.put("status", 0);
            map.put("message", "User is not found = "+String.valueOf(userId));
            return new ResponseEntity<>(map, NOT_FOUND);
        }
    }

    // delete a user
    @DeleteMapping(path = "/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable int userId) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();

        User foundUser = userService.getUser(userId);
        if (foundUser != null) {
            if (userService.deleteUser(userId) == true) {
                map.put("status", 1);
                map.put("message", "User deleted successfully = " + String.valueOf(userId));
                return new ResponseEntity<>(map, NO_CONTENT);
            }
        }

        map.put("status", 0);
        map.put("message", "User not found, not deleted = " + String.valueOf(userId));
        return new ResponseEntity<>(map, NOT_FOUND);
    }


    // upsert design
    @PutMapping("/users/upsert")
    public ResponseEntity<User> updateUpsert(@RequestBody User user){

        if (user.getDob() == null || user.getName() == null){
            return ResponseEntity.badRequest().build();
        }

        // find user
        User returnedUser = userService.getUser(user.getId());
        // if  not exist then create and return
        if(returnedUser==null) {
            ResponseEntity<User> user1 = newUser(user);
            return new ResponseEntity(user1.getBody(), CREATED);
        }
        // if yes return the updated user
        userService.updateUser(user.getId(),returnedUser);
        return new ResponseEntity<>(returnedUser,OK);
    }

    //Assignment 2 :: create Patch Method Implementation
    @PatchMapping(path = "/users/update/{userId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> patchUser(@PathVariable int userId,@RequestBody User user){

        Map<String, Object> map = new LinkedHashMap<String, Object>();
        List<User> userList = new ArrayList<>();

        if (user.getDob() == null || user.getName() == null){
            return ResponseEntity.badRequest().build();
        }

        //confirm a valid userId, was passed in and if so, proceed to update user
        User foundUser = userService.getUser(userId);
        if (foundUser != null) {
            User updatedUser = userService.updateUser(userId,user);
            map.put("status", 1);
            map.put("user", updatedUser);
            return new ResponseEntity<>(map, OK);
        } else {
            map.clear();
            map.put("status", 0);
            map.put("message", "User is not found = "+String.valueOf(userId));
            return new ResponseEntity<>(map, NOT_FOUND);
        }

    }


    //Assignment #2 :: Call, newly created POJO class
    @SneakyThrows
    @PostMapping(path = "/create/subscriber", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Subscriber> newSubscriber(@RequestBody Subscriber user) {

        if (user==null) {
            return ResponseEntity.internalServerError().body(user);
        }

        if (user.getFirstName() == null || user.getLastName() == null || user.getEmail() == null || user.getAvatar() == null) {
            return ResponseEntity.badRequest().build();
        }

        Subscriber newUser = subService.newSubscriber(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();
        //return ResponseEntity.created(uri).build();
        return newUser == null ? ResponseEntity.internalServerError().body(newUser) : new ResponseEntity<Subscriber>(newUser, CREATED);
    }
}
