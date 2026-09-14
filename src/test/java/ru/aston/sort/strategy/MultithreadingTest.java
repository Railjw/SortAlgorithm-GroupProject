package ru.aston.sort.strategy;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;
import ru.aston.model.Car;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import ru.aston.extra.MultithreadedCounter;

public class MultithreadingTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    private static List<Car> createCars() {
        List<Car> cars = new ArrayList<>();

        cars.add(createCar(200, "BMW", 1992));
        cars.add(createCar(100, "Mercedes", 2003));
        cars.add(createCar(150, "Audi", 2015));
        cars.add(createCar(150, "Audi", 2015));
        cars.add(null);
        cars.add(createCar(300, "Volkswagen", 1992));
        cars.add(createCar(300, "Opel", 1992));
        cars.add(createCar(90, "Toyota", 2008));

        return cars;
    }

    private static Car createCar(int power, String model, int productionYear) {
        return Car.builder().power(power).model(model).productionYear(productionYear).build();
    }   

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void testThatResultsAreCorrect() {
        List<Car> cars = createCars();
        Car zeroCar = createCar(123, "Opel", 1986);
        Car twoCars = createCar(150, "Audi", 2015);
        
        MultithreadedCounter.calcSameObjectsCountAndPrint(zeroCar, cars);
        assertEquals("0" + System.lineSeparator(), outputStreamCaptor.toString());
        
        outputStreamCaptor.reset();
        
        MultithreadedCounter.calcSameObjectsCountAndPrint(twoCars, cars);
        assertEquals("2" + System.lineSeparator(), outputStreamCaptor.toString());       
    }
    
    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }
    
    @Test
    void testBeingCrashless() {
        List<Car> cars = createCars();
        Car testCar = createCar(123, "Opel", 1986);
        
        assertDoesNotThrow(() -> MultithreadedCounter.calcSameObjectsCountAndPrint(null, cars));
        assertDoesNotThrow(() -> MultithreadedCounter.calcSameObjectsCountAndPrint(testCar, null));
        assertDoesNotThrow(() -> MultithreadedCounter.calcSameObjectsCountAndPrint(null, null));
    }
}