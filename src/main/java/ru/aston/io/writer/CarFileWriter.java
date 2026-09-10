package ru.aston.io.writer;

import ru.aston.Car;
import ru.aston.CustomList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            writeRange(bw, cars, start, end);
        }
    }

    public static void append(String fileName, CustomList<Car> cars) throws IOException {
        append(fileName, cars, 0, cars.size());
    }

    public static void append(String fileName, CustomList<Car> cars, int limit) throws IOException {
        append(fileName, cars, 0, Math.min(limit, cars.size()));
    }

    public static void append(String fileName, CustomList<Car> cars, int startIndex, int endIndex) throws IOException {
        int start = Math.max(0, startIndex);
        int end = Math.min(cars.size(), endIndex);

        if (start >= cars.size() || start >= end) {
            throw new IllegalArgumentException("Invalid range. Available: " + cars.size());
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
            writeRange(bw, cars, start, end);
        }
    }

    private static void writeRange(BufferedWriter bw, CustomList<Car> cars, int start, int end) throws IOException {
        for (int i = start; i < end; i++) {
            Car car = cars.get(i);
            bw.write(String.format("%d,%s,%d%n",
                    car.getPower(), car.getModel(), car.getProductionYear()));
        }
    }
}