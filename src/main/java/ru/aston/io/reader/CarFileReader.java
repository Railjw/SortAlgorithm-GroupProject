package ru.aston.io.reader;

import ru.aston.Car;
import ru.aston.CustomList;
import ru.aston.io.parser.CarParser;

import java.io.*;
import java.nio.file.*;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class CarFileReader {

    public static CustomList<Car> read(String fileName) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            CustomList<Car> cars = new CustomList<>();

            lines.filter(line -> !line.trim().isEmpty())
                    .forEach(line -> CarParser.parseLine(line).ifPresent(cars::add));

            return cars;
        }
    }

    public static CustomList<Car> readWithStream(String fileName) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            CustomList<Car> cars = new CustomList<>();

            lines.filter(line -> !line.trim().isEmpty())
                    .map(CarParser::parseLine)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .forEach(cars::add);

            return cars;
        }
    }

    public static CustomList<Car> readWithErrorHandling(String fileName) throws IOException {
        CustomList<Car> cars = new CustomList<>();
        AtomicInteger lineNumber = new AtomicInteger(0);

        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                lineNumber.incrementAndGet();
                if (line.trim().isEmpty()) continue;

                String currentLine = line;
                int currentNumber = lineNumber.get();

                CarParser.parseLine(line).ifPresentOrElse(
                        cars::add,
                        () -> System.err.println("Skipping line " + currentNumber + ": " + currentLine)
                );
            }
        }

        return cars;
    }
}