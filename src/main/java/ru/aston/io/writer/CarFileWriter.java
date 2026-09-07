package ru.aston.io.writer;

import ru.aston.Car;
import ru.aston.CustomList;

import java.io.*;
import java.util.Date;

public class CarFileWriter {

    public static void write(String fileName, CustomList<Car> cars) throws IOException {
        write(fileName, cars, 0, cars.size());
    }

    public static void write(String fileName, CustomList<Car> cars, int limit) throws IOException {
        write(fileName, cars, 0, Math.min(limit, cars.size()));
    }

    public static void write(String fileName, CustomList<Car> cars, int startIndex, int endIndex) throws IOException {
        int start = Math.max(0, startIndex);
        int end = Math.min(cars.size(), endIndex);

        if (start >= cars.size() || start >= end) {
            throw new IllegalArgumentException("Invalid range. Available: " + cars.size());
        }

        try (BufferedWriter bw = new BufferedWriter(new java.io.FileWriter(fileName))) {
            bw.write("=== Cars (positions " + (start + 1) + "-" + end + ") ===\n");
            bw.write("Date: " + new Date() + "\n");
            bw.write("Total: " + cars.size() + " | Written: " + (end - start) + "\n\n");

            for (int i = start; i < end; i++) {
                Car car = cars.get(i);
                bw.write(String.format("%d. %s, %d, %d%n",
                        i + 1, car.getModel(), car.getPower(), car.getProductionYear()));
            }
            bw.write("\n--- End of record ---\n");
        }
    }

    public static void append(String fileName, CustomList<Car> cars, String comment) throws IOException {
        append(fileName, cars, comment, 0, cars.size());
    }

    public static void append(String fileName, CustomList<Car> cars, String comment, int limit) throws IOException {
        append(fileName, cars, comment, 0, Math.min(limit, cars.size()));
    }

    public static void append(String fileName, CustomList<Car> cars, String comment, int startIndex, int endIndex) throws IOException {
        int start = Math.max(0, startIndex);
        int end = Math.min(cars.size(), endIndex);

        if (start >= cars.size() || start >= end) {
            throw new IllegalArgumentException("Invalid range. Available: " + cars.size());
        }

        try (BufferedWriter bw = new BufferedWriter(new java.io.FileWriter(fileName, true))) {
            bw.write("\n=== " + comment + " ===\n");
            bw.write("Date: " + new Date() + "\n");
            bw.write("Total: " + cars.size() + " | Written: " + (end - start) + "\n");
            bw.write("Positions: " + (start + 1) + "-" + end + "\n\n");

            for (int i = start; i < end; i++) {
                Car car = cars.get(i);
                bw.write(String.format("%d. %s, %d, %d%n",
                        i + 1, car.getModel(), car.getPower(), car.getProductionYear()));
            }
            bw.write("--- End of record ---\n");
        }
    }
}