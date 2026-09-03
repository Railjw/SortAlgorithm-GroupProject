package ru.aston.sort.strategy;

import org.junit.jupiter.api.Test;
import ru.aston.Car;
import ru.aston.sort.ComparatorFactory;
import ru.aston.sort.SortAlgorithm;
import ru.aston.sort.SortField;
import ru.aston.sort.SortStrategyFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EvenFieldOnlySortTest {
    @Test
    void shouldSortOnlyCarsWithEvenPower() {
        List<Car> cars = TestCarsFactory.createCarsWithOddFields();

        SortStrategy strategy = SortStrategyFactory.create(SortAlgorithm.BUBBLE);

        EvenFieldOnlySort evenFieldOnlySort = new EvenFieldOnlySort(strategy, Car::getPower);

        evenFieldOnlySort.sort(
                cars,
                ComparatorFactory.create(SortField.POWER)
        );

        assertEquals(207, cars.get(0).getPower());
        assertEquals(90, cars.get(1).getPower());
        assertEquals(100, cars.get(2).getPower());
        assertEquals("Mercedes", cars.get(2).getModel());
        assertEquals(309, cars.get(3).getPower());
        assertEquals(100, cars.get(4).getPower());
        assertEquals("Ford", cars.get(4).getModel());
        assertEquals(125, cars.get(5).getPower());
        assertEquals(150, cars.get(6).getPower());
    }

    @Test
    void shouldSortOnlyCarsWithEvenProductionYearByPower() {
        List<Car> cars = TestCarsFactory.createCarsWithOddFields();

        SortStrategy strategy = SortStrategyFactory.create(SortAlgorithm.BUBBLE);

        EvenFieldOnlySort evenFieldOnlySort = new EvenFieldOnlySort(strategy, Car::getProductionYear);

        evenFieldOnlySort.sort(
                cars,
                ComparatorFactory.create(SortField.POWER)
        );

        assertEquals(90, cars.get(0).getPower());
        assertEquals(100, cars.get(1).getPower());
        assertEquals("Mercedes", cars.get(1).getModel());
        assertEquals(150, cars.get(2).getPower());
        assertEquals(100, cars.get(3).getPower());
        assertEquals("Ford", cars.get(3).getModel());
        assertEquals(207, cars.get(4).getPower());
        assertEquals(125, cars.get(5).getPower());
        assertEquals(309, cars.get(6).getPower());
    }

    @Test
    void shouldSortOnlyCarsWithEvenProductionYearByModel() {
        List<Car> cars = TestCarsFactory.createCarsWithOddFields();

        SortStrategy strategy = SortStrategyFactory.create(SortAlgorithm.BUBBLE);

        EvenFieldOnlySort evenFieldOnlySort = new EvenFieldOnlySort(strategy, Car::getProductionYear);

        evenFieldOnlySort.sort(
                cars,
                ComparatorFactory.create(SortField.MODEL)
        );

        assertEquals("BMW", cars.get(0).getModel());
        assertEquals("Mercedes", cars.get(1).getModel());
        assertEquals("Audi", cars.get(2).getModel());
        assertEquals("Ford", cars.get(3).getModel());
        assertEquals("Toyota", cars.get(4).getModel());
        assertEquals("Nissan", cars.get(5).getModel());
        assertEquals("Volkswagen", cars.get(6).getModel());
    }
}