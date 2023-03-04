package com.user.userops;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@RestController
public class GreetingsController {

    @RequestMapping(path = "/hello",method = RequestMethod.GET)
    public String sayHello(){
        return "hello, welcome to the session..!";
    }

    @RequestMapping(path = "/phones",method = RequestMethod.GET)
    public List<Phone> getPhones(){
        Phone phone1 = new Phone("SM-39",
                "Samsung", "v1");
        Phone phone2 = new Phone("model",
                "one plus", "v1");
        return Arrays.asList(phone1,phone2);
    }

}
