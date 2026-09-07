package ru.aston.UI.actions.search;

import ru.aston.CarInputOutput;
import ru.aston.UI.state.ApplicationContext;
import ru.aston.UI.actions.MenuAction;
import ru.aston.extra.Task4;
import ru.aston.Car;

import java.util.List;
import java.util.Scanner;

public class CountOccurrencesAction implements MenuAction {

    @Override
    public void execute(ApplicationContext context) {
        List<Car> carList = context.getCars();

        if (carList == null || carList.isEmpty()) {
            System.out.println("The car list is empty. Please add cars to the list first.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter car details to search:");

        Car searchCar;
        try {
            searchCar = CarInputOutput.inputFromConsole(scanner);
        } catch (IllegalArgumentException e) {
            System.out.println("Error while reading car data: " + e.getMessage());
            return;
        }

        System.out.println("\nSearching for car: " + searchCar.getModel() +
                ", " + searchCar.getPower() + " hp, " +
                searchCar.getProductionYear() + " yr.");

        Task4.calcSameObjectsCountAndPrint(searchCar, carList);
    }
}