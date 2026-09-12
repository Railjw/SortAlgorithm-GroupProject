package ru.aston.io.writer;

import ru.aston.model.Car;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.List;

public class CarFileWriter {

    public static void write(String fileName, List<Car> cars) throws IOException {
        write(fileName, cars, 0, cars.size());
    }

    public static void write(String fileName, List<Car> cars, int limit) throws IOException {
        write(fileName, cars, 0, Math.min(limit, cars.size()));
    }

    public static void write(String fileName, List<Car> cars, int startIndex, int endIndex) throws IOException {
        int start = Math.max(0, startIndex);
        int end = Math.min(cars.size(), endIndex);

        if (start >= cars.size() || start >= end) {
            throw new IllegalArgumentException("Invalid range. Available: " + cars.size());
        }

        try (BufferedWriter bw = Files.newBufferedWriter(
                Paths.get(fileName),
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING)) {

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

    public static void append(String fileName, List<Car> cars, String comment) throws IOException {
        append(fileName, cars, comment, 0, cars.size());
    }

    public static void append(String fileName, List<Car> cars, String comment, int limit) throws IOException {
        append(fileName, cars, comment, 0, Math.min(limit, cars.size()));
    }

    public static void append(String fileName, List<Car> cars, String comment, int startIndex, int endIndex) throws IOException {
        int start = Math.max(0, startIndex);
        int end = Math.min(cars.size(), endIndex);

        if (start >= cars.size() || start >= end) {
            throw new IllegalArgumentException("Invalid range. Available: " + cars.size());
        }

        try (BufferedWriter bw = Files.newBufferedWriter(
                Paths.get(fileName),
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {

            bw.write("\n=== " + comment + " ===\n");
            bw.write("Date: " + new Date() + "\n");
            bw.write("Appended: " + (end - start) + " cars (from collection of " + cars.size() + ")\n");
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