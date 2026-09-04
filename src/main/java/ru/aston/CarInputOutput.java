package ru.aston;

import java.io.*;
import java.util.*;

public class CarInputOutput {
    public static List<Car> readFromFile(String fileName) throws IOException {
        List<Car> cars = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    try {
                        int power = Integer.parseInt(parts[0].trim());
                        String model = parts[1].trim();
                        int year = Integer.parseInt(parts[2].trim());
                        Car car = Car.builder().power(power).model(model).productionYear(year).build();
                        cars.add(car);
                    } catch (IllegalArgumentException e) {
                        System.err.println("Validation error: " + e.getMessage() + " (Line: " + line + ")");
                    }
                }
            }
        }
        return cars;
    }

    public static List<Car> generateRandom(int count) { //рандомное заполнение
        List<Car> cars = new ArrayList<>();
        Random rand = new Random();
        String[] models = {"Lada Granta", "Lada Vesta", "Lada Iskra", "BMW X5", "BMW M5", "BMW X3", "Audi TT", "Audi A4", "Audi R8", "Toyota RAV4", "Toyota Camry", "Honda CR-V", "Honda Civic"};

        for (int i = 0; i < count; i++) {
            try {
                Car car = Car.builder().power(rand.nextInt(500) + 50) // от 50 до 549
                        .model(models[rand.nextInt(models.length)] + " " + (rand.nextInt(100) + 1)).productionYear(rand.nextInt(2026 - 1886) + 1886).build();
                cars.add(car);
            } catch (IllegalArgumentException ignored) {
            }
        }
        return cars;
    }

    public static Car inputFromConsole(Scanner scanner) {    //ручной ввод
        System.out.print("Enter the power: ");
        int power = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the model: ");
        String model = scanner.nextLine();
        System.out.print("Enter the year of manufacture: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        return Car.builder().power(power).model(model).productionYear(year).build();
    }

    public static void printCars(List<Car> cars) {    //вывод в консоль
        for (Car car : cars) {
            System.out.println(car.getPower() + ", " + car.getModel() + ", " + car.getProductionYear());
        }
    }
}
