package ru.aston;

import ru.aston.io.reader.ConsoleReader;
import ru.aston.io.reader.CarFileReader;
import ru.aston.io.writer.ConsoleWriter;
import ru.aston.io.writer.CarFileWriter;
import ru.aston.io.generator.CarGenerator;

import java.io.IOException;
import java.util.Scanner;

public class CarIOFacade {

    public static Car readFromConsole(Scanner scanner) {
        return ConsoleReader.readOne();
    }

    public static CustomList<Car> readMultipleFromConsole(Scanner scanner, int count) {
        return ConsoleReader.readMultiple(count);
    }

    public static CustomList<Car> readOneByOneFromConsole(Scanner scanner, int count) {
        return ConsoleReader.readOneByOne(count);
    }

    public static CustomList<Car> readFromFile(String fileName) throws IOException {
        return CarFileReader.read(fileName);
    }

    public static CustomList<Car> readFromFileStream(String fileName) throws IOException {
        return CarFileReader.readWithStream(fileName);
    }

    public static CustomList<Car> generateRandom(int count) {
        return CarGenerator.generate(count);
    }

    public static void printAll(CustomList<Car> cars) {
        ConsoleWriter.printAll(cars);
    }

    public static void printFirst(CustomList<Car> cars, int limit) {
        ConsoleWriter.printFirst(cars, limit);
    }

    public static void printRange(CustomList<Car> cars, int start, int end) {
        ConsoleWriter.printRange(cars, start, end);
    }

    public static void writeToFile(String fileName, CustomList<Car> cars) throws IOException {
        CarFileWriter.writeAll(fileName, cars);
    }

    public static void writeFirstToFile(String fileName, CustomList<Car> cars, int limit) throws IOException {
        CarFileWriter.writeFirst(fileName, cars, limit);
    }

    public static void writeRangeToFile(String fileName, CustomList<Car> cars, int start, int end) throws IOException {
        CarFileWriter.writeRange(fileName, cars, start, end);
    }

    public static void appendToFile(String fileName, CustomList<Car> cars, String comment) throws IOException {
        CarFileWriter.appendAll(fileName, cars, comment);
    }

    public static void appendFirstToFile(String fileName, CustomList<Car> cars, String comment, int limit) throws IOException {
        CarFileWriter.appendFirst(fileName, cars, comment, limit);
    }

    public static void appendRangeToFile(String fileName, CustomList<Car> cars, String comment, int start, int end) throws IOException {
        CarFileWriter.appendRange(fileName, cars, comment, start, end);
    }
}