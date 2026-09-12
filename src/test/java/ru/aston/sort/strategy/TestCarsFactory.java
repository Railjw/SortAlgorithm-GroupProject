package ru.aston.sort.strategy;

import ru.aston.Car;

import java.util.ArrayList;
import java.util.List;

public class TestCarsFactory {
    public static List<Car> createCars() {
        List<Car> cars = new ArrayList<>();

        cars.add(createCar(200, "BMW", 1992));
        cars.add(createCar(100, "Mercedes", 2003));
        cars.add(createCar(150, "Audi", 2015));
        cars.add(createCar(300, "Volkswagen", 1992));
        cars.add(createCar(90, "Toyota", 2008));

        return cars;
    }

    public static List<Car> createCarsWithOddFields() {
        List<Car> cars = new ArrayList<>();

        cars.add(createCar(207, "BMW", 1992));
        cars.add(createCar(100, "Mercedes", 2003));
        cars.add(createCar(150, "Audi", 2015));
        cars.add(createCar(309, "Volkswagen", 1990));
        cars.add(createCar(90, "Toyota", 2008));
        cars.add(createCar(125, "Nissan", 2017));
        cars.add(createCar(100, "Ford", 2000));

        return cars;
    }

    public static Car createCar(int power, String model, int productionYear) {
        return Car.builder().power(power).model(model).productionYear(productionYear).build();
    }
}
