package ru.aston.io.writer;

import ru.aston.Car;
import ru.aston.CustomList;

public class ConsoleWriter {

    private static final String BORDER = "----------------------------------------------------------";
    private static final String HEADER = "|  №  | Model                | Power    | Production Year|";

    public static void print(CustomList<Car> cars) {
        print(cars, 0, cars.size());
    }

    public static void print(CustomList<Car> cars, int limit) {
        print(cars, 0, Math.min(limit, cars.size()));
    }

    public static void print(CustomList<Car> cars, int startIndex, int endIndex) {
        if (cars.size() == 0) {
            System.out.println("Collection is empty.");
            return;
        }

        int start = Math.max(0, startIndex);
        int end = Math.min(cars.size(), endIndex);

        if (start >= cars.size() || start >= end) {
            System.out.println("Invalid range. Available elements: " + cars.size());
            return;
        }

        System.out.println(BORDER);
        System.out.println(HEADER);
        System.out.println(BORDER);

        for (int i = start; i < end; i++) {
            Car car = cars.get(i);
            System.out.printf("| %3d | %-29s | %8d | %18d |%n",
                    i + 1, car.getModel(), car.getPower(), car.getProductionYear());
        }

        System.out.println(BORDER);
        System.out.printf("Showing: %d of %d cars (positions %d-%d)%n%n",
                end - start, cars.size(), start + 1, end);
    }
}