package ru.aston.io;

import ru.aston.model.Car;
import ru.aston.customcollection.ListFactory;
import ru.aston.io.reader.ConsoleReader;
import ru.aston.io.reader.CarFileReader;
import ru.aston.io.writer.ConsoleWriter;
import ru.aston.io.writer.CarFileWriter;
import ru.aston.io.generator.CarGenerator;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CarInputOutput {

    private static final ListFactory.ListType DEFAULT_LIST_TYPE = ListFactory.ListType.LINKED;

    public static Optional<Car> inputFromConsole(Scanner scanner) {
        return ConsoleReader.readOne(scanner);
    }

    public static List<Car> readFromFile(String fileName) throws IOException {
        try (Stream<Car> stream = CarFileReader.read(fileName)) {
            return stream.collect(Collectors.toCollection(
                    () -> ListFactory.create(DEFAULT_LIST_TYPE)));
        }
    }

    public static List<Car> generateRandom(int count) {
        return CarGenerator.generate(count)
                .collect(Collectors.toCollection(
                        () -> ListFactory.create(DEFAULT_LIST_TYPE)));
    }

    public static List<Car> createCarList() {
        return ListFactory.create(DEFAULT_LIST_TYPE);
    }

    public static List<Car> copyCarList(List<Car> source) {
        return ListFactory.create(source);
    }

    public static void print(List<Car> cars) {
        ConsoleWriter.print(cars);
    }

    public static void print(List<Car> cars, int limit) {
        ConsoleWriter.print(cars, limit);
    }

    public static void print(List<Car> cars, int startIndex, int endIndex) {
        ConsoleWriter.print(cars, startIndex, endIndex);
    }

    public static void write(String fileName, List<Car> cars) throws IOException {
        CarFileWriter.write(fileName, cars);
    }

    public static void write(String fileName, List<Car> cars, int limit) throws IOException {
        CarFileWriter.write(fileName, cars, limit);
    }

    public static void write(String fileName, List<Car> cars, int startIndex, int endIndex) throws IOException {
        CarFileWriter.write(fileName, cars, startIndex, endIndex);
    }

    public static void append(String fileName, List<Car> cars, String comment) throws IOException {
        CarFileWriter.append(fileName, cars, comment);
    }

    public static void append(String fileName, List<Car> cars, String comment, int limit) throws IOException {
        CarFileWriter.append(fileName, cars, comment, limit);
    }

    public static void append(String fileName, List<Car> cars, String comment, int startIndex, int endIndex) throws IOException {
        CarFileWriter.append(fileName, cars, comment, startIndex, endIndex);
    }
}