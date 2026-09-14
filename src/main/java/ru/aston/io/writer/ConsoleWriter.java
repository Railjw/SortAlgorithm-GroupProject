package ru.aston.io.writer;

import ru.aston.model.Car;

import java.util.List;

public class ConsoleWriter {

    public static void print(List<Car> cars) {
        print(cars, 0, cars.size());
    }

    public static void print(List<Car> cars, int limit) {
        print(cars, 0, Math.min(limit, cars.size()));
    }

    public static void print(List<Car> cars, int startIndex, int endIndex) {
        if (cars.isEmpty()) {
            System.out.println("Collection is empty.");
            return;
        }

        int start = Math.max(0, startIndex);
        int end = Math.min(cars.size(), endIndex);

        if (start >= cars.size() || start >= end) {
            System.out.println("Invalid range. Available elements: " + cars.size());
            return;
        }

        int colNo = Math.max(2, String.valueOf(end).length());   // "№" или номер
        int colModel = "Model".length();
        int colPower = "Power".length();
        int colYear = "Production Year".length();

        for (int i = start; i < end; i++) {
            Car car = cars.get(i);
            colModel = Math.max(colModel, car.getModel().length());
            colPower = Math.max(colPower, String.valueOf(car.getPower()).length());
            colYear = Math.max(colYear, String.valueOf(car.getProductionYear()).length());
        }

        String border = "-".repeat(colNo + colModel + colPower + colYear + 13);

        String header = String.format(
                "| %-" + colNo + "s | %-" + colModel + "s | %-" + colPower + "s | %-" + colYear + "s |",
                "№", "Model", "Power", "Production Year");

        System.out.println(border);
        System.out.println(header);
        System.out.println(border);

        for (int i = start; i < end; i++) {
            Car car = cars.get(i);
            System.out.printf(
                    "| %-" + colNo + "d | %-" + colModel + "s | %" + colPower + "d | %" + colYear + "d |%n",
                    i + 1,
                    car.getModel(),
                    car.getPower(),
                    car.getProductionYear());
        }

        System.out.println(border);
        System.out.printf("Showing: %d of %d cars (positions %d-%d)%n%n",
                end - start, cars.size(), start + 1, end);
    }
}