package com.motor.carops.controller;

import com.motor.carops.model.Car;
import com.motor.carops.service.CarService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api")
public class CarController {

    @Autowired
    CarService carService;


    //get all cars
    @GetMapping(path = "/cars", produces = "application/json")
    public ResponseEntity<List<Car>> getAllCars(){

        return new ResponseEntity<List<Car>>(carService.retrieveAllCars(), OK);
    }

    @GetMapping(path = "/cars/random", produces = "application/json")
    public ResponseEntity<?> getCarRandom(){

        Map<String, Object> map = new LinkedHashMap<String, Object>();
        List<Car> carList = new ArrayList<>();
        carList.add(carService.getrandomCar());
        map.put("status", 1);
        map.put("car", carList);
        return new ResponseEntity<>(map, OK);

    }

    // get a specific car
    @GetMapping(path = "/cars/{carId}", produces = "application/json")
    @SneakyThrows
    public ResponseEntity<?> getCarById(@PathVariable int carId) {

        Map<String, Object> map = new LinkedHashMap<String, Object>();
        List<Car> carList = new ArrayList<>();
        carList.add(carService.getCar(carId));
        if (!carList.isEmpty()) {
            map.put("status", 1);
            map.put("car", carList);
            return new ResponseEntity<>(map, OK);
        } else {
            map.clear();
            map.put("status", 0);
            map.put("message", "Car is not found = "+String.valueOf(carId));
            return new ResponseEntity<>(map, NOT_FOUND);
        }

    }


    // create a new car
    @SneakyThrows
    @PostMapping(path = "/create/car", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Car> newCar(@RequestBody Car car) {

        if (car==null){
            return ResponseEntity.internalServerError().body(car);
        }
        Car newCar = carService.newCar(car);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newCar.getId())
                .toUri();
        //return ResponseEntity.created(uri).build();
        return newCar == null ? ResponseEntity.internalServerError().body(newCar) : new ResponseEntity<Car>(newCar, CREATED);
    }


    // update a car
    @SneakyThrows
    @PutMapping (path = "/cars/update/{carId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updateCar(@PathVariable int carId, @RequestBody Car car) {

        Map<String, Object> map = new LinkedHashMap<String, Object>();
        List<Car> userList = new ArrayList<>();

        //no body found, nothing to update
        if (car==null){
            return ResponseEntity.internalServerError().body(car);
        }

        //confirm a valid carId, was passed in and if so, proceed to update user
        Car foundUser = carService.getCar(carId);
        if (foundUser != null) {
            Car updatedUser = carService.updateCar(carId,car);
            map.put("status", 1);
            map.put("user", updatedUser);
            return new ResponseEntity<>(map, OK);
        } else {
            map.clear();
            map.put("status", 0);
            map.put("message", "Car is not found = "+String.valueOf(carId));
            return new ResponseEntity<>(map, NOT_FOUND);
        }
    }

    // delete a car
    @SneakyThrows
    @DeleteMapping(path = "/cars/delete/{carId}")
    public ResponseEntity<?> deleteCar(@PathVariable int carId){
        Map<String, Object> map = new LinkedHashMap<String, Object>();

        Car foundUser = carService.getCar(carId);
        if (foundUser != null) {
            if (carService.deleteCar(carId) == true) {
                map.put("status", 1);
                map.put("message", "Car deleted successfully = " + String.valueOf(carId));
                return new ResponseEntity<>(map, NO_CONTENT);
            }
        }

        map.put("status", 0);
        map.put("message", "Car not found, not deleted = " + String.valueOf(carId));
        return new ResponseEntity<>(map, NOT_FOUND);
    }


    // upsert design
    @PutMapping("/cars")
    public ResponseEntity<Car> updateUpsert(@RequestBody Car car){

        // find user
        Car returnedCar = carService.getCar(car.getId());
        // if  not exist then create and return
        if(returnedCar==null) {
            ResponseEntity<Car> car1 = newCar(car);
            return new ResponseEntity(car1.getBody(), CREATED);
        }
        // if yes return the updated user
        carService.updateCar(car.getId(),returnedCar);
        return new ResponseEntity<>(returnedCar,OK);
    }
}

