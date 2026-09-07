package ru.aston.io.writer;

import ru.aston.Car;
import ru.aston.CustomList;

import java.util.stream.IntStream;

public class ConsoleWriter {

    private static final String BORDER = "|-----------------------------------------------------|";
    private static final String HEADER = "|  №  | Model               | Power  | Production Year|";

    public static void printAll(CustomList<Car> cars) {
        printRange(cars, 0, cars.size());
    }

    public static void printFirst(CustomList<Car> cars, int limit) {
        printRange(cars, 0, Math.min(limit, cars.size()));
    }

    public static void printRange(CustomList<Car> cars, int startIndex, int endIndex) {
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

        printHeader();
        IntStream.range(start, end)
                .mapToObj(i -> formatCar(i + 1, cars.get(i)))
                .forEach(System.out::println);
        printFooter(end - start, cars.size(), start + 1, end);
    }

    private static void printHeader() {
        System.out.println(BORDER);
        System.out.println(HEADER);
        System.out.println(BORDER);
    }

    private static void printFooter(int shown, int total, int start, int end) {
        System.out.println(BORDER);
        System.out.printf("Showing: %d of %d cars (positions %d-%d)%n%n", shown, total, start, end);
    }

    private static String formatCar(int index, Car car) {
        return String.format("| %3d | %-29s | %8d | %18d |",
                index, car.getModel(), car.getPower(), car.getProductionYear());
    }
}