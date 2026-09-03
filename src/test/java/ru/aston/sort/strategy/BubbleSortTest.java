package ru.aston.sort.strategy;

import org.junit.jupiter.api.Test;
import ru.aston.Car;
import ru.aston.sort.ComparatorFactory;
import ru.aston.sort.SortAlgorithm;
import ru.aston.sort.SortField;
import ru.aston.sort.SortStrategyFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BubbleSortTest {
    @Test
    void shouldSortCarsByPower() {
        List<Car> cars = TestCarsFactory.createCars();

        SortStrategy strategy = SortStrategyFactory.create(SortAlgorithm.BUBBLE);

        strategy.sort(
                cars,
                ComparatorFactory.create(SortField.POWER)
        );

        assertEquals(90, cars.get(0).getPower());
        assertEquals(100, cars.get(1).getPower());
        assertEquals(150, cars.get(2).getPower());
        assertEquals(200, cars.get(3).getPower());
        assertEquals(300, cars.get(4).getPower());
    }

    @Test
    void shouldSortCarsByModel() {
        List<Car> cars = TestCarsFactory.createCars();

        SortStrategy strategy = SortStrategyFactory.create(SortAlgorithm.BUBBLE);

        strategy.sort(
                cars,
                ComparatorFactory.create(SortField.MODEL)
        );

        assertEquals("Audi", cars.get(0).getModel());
        assertEquals("BMW", cars.get(1).getModel());
        assertEquals("Mercedes", cars.get(2).getModel());
        assertEquals("Toyota", cars.get(3).getModel());
        assertEquals("Volkswagen", cars.get(4).getModel());
    }

    @Test
    void shouldSortCarsByProductionYear() {
        List<Car> cars = TestCarsFactory.createCars();

        SortStrategy strategy = SortStrategyFactory.create(SortAlgorithm.BUBBLE);

        strategy.sort(
                cars,
                ComparatorFactory.create(SortField.PRODUCTION_YEAR)
        );

        assertEquals(1992, cars.get(0).getProductionYear());
        assertEquals("BMW", cars.get(0).getModel());
        assertEquals(1992, cars.get(1).getProductionYear());
        assertEquals("Volkswagen", cars.get(1).getModel());
        assertEquals(2003, cars.get(2).getProductionYear());
        assertEquals(2008, cars.get(3).getProductionYear());
        assertEquals(2015, cars.get(4).getProductionYear());
    }
}