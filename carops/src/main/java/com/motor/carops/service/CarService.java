package com.motor.carops.service;

import com.motor.carops.model.Car;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//	- Car (id,makeModel, company, price, makeYear, type=SUV,Sedan, hatchback)
@Service
public class CarService {

    public static ArrayList<Car> cars=new ArrayList<>();
    public static int id=0;

    static {
        cars.add(new Car(++id,"Explorer","Ford",20000,2004, "SUV", true ));
        cars.add(new Car(++id,"F150",    "Ford",40000,2010, "Truck", true ));
        cars.add(new Car(++id,"Bronco",  "Ford",30000,2023, "Terrain", false ));
    }

    public List<Car> retrieveAllCars() {
        return cars;
    }

    public Car getCar(int carId){

        //for (Car carr:cars){
        //    if(carr.getId()==carId) return carr;
        //}
        //return null;

        return cars.stream()
                .filter(a-> a.getId() == carId)
                .findFirst()
                .orElse(null);
    }

    public Car getrandomCar(){

        return cars.stream()
                .skip(new Random().nextInt(cars.size()))
                .findFirst()
                .orElse(null);
    }


    public Car newCar(Car car){

        //how to handle validations more accurately
        //Car newcar = new Car(++id,makeModel,company,price,makeYear,cartype,hatchback);
        Car newCar = Car.builder()
                .id(++id)
                .makeModel(car.getMakeModel())
                .company(car.getCompany())
                .price(car.getPrice())
                .makeYear(car.getMakeYear())
                .carType(car.getCarType())
                .hatchback(car.isHatchback()).build();

            cars.add(newCar);

            return newCar;
        }


    public Car updateCar(int carId, Car car){

        Car carr = getCar(carId);
        if (carr != null) {
            carr.setMakeModel(car.getMakeModel());
            carr.setCompany(car.getCompany());
            carr.setPrice(car.getPrice());
            carr.setMakeYear(car.getMakeYear());
            carr.setCarType(car.getCarType());
            carr.setHatchback(car.isHatchback());
            return carr;
        }

        return null;
    }

    public boolean deleteCar(int carId){

        return cars.removeIf(car -> car.getId() == carId);
        //for (Car carr:cars){
        //    if(carr.getId() == carId) {
        //        cars.remove(carr);
        //        return true;
        //    }
        //}
        //return false;
    }

}