package ru.aston.ui.actions.search;

import ru.aston.io.CarInputOutput;
import ru.aston.ui.state.ApplicationContext;
import ru.aston.ui.actions.MenuAction;
import ru.aston.extra.MultithreadedCounter;
import ru.aston.model.Car;

import java.util.List;
import java.util.Optional;

public class CountOccurrencesAction implements MenuAction {

    @Override
    public void execute(ApplicationContext context) {
        List<Car> carList = context.getCars();

        if (carList == null || carList.isEmpty()) {
            System.out.println("The car list is empty. Please add cars to the list first.");
            return;
        }

        System.out.println("\nEnter car details to search:");

        Optional<Car> maybeCar = CarInputOutput.inputFromConsole(context.getScanner());

        if (maybeCar.isEmpty()) {
            System.out.println("Search cancelled.");
            return;
        }

        Car searchCar = maybeCar.get();

        System.out.println("\nSearching for car: " + searchCar.getModel() +
                ", " + searchCar.getPower() + " hp, " +
                searchCar.getProductionYear() + " yr.");

        MultithreadedCounter.calcSameObjectsCountAndPrint(searchCar, carList);
    }
}