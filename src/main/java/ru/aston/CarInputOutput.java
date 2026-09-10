package ru.aston;

import ru.aston.io.reader.ConsoleReader;
import ru.aston.io.reader.CarFileReader;
import ru.aston.io.writer.ConsoleWriter;
import ru.aston.io.writer.CarFileWriter;
import ru.aston.io.generator.CarGenerator;

import java.io.IOException;
import java.util.Scanner;

public class CarInputOutput {

    public static Car inputFromConsole(Scanner scanner) {
        return ConsoleReader.readOne();
    }

    public static CustomList<Car> inputMultipleFromConsole(Scanner scanner, int count) {
        return ConsoleReader.readMultiple(count);
    }

    public static CustomList<Car> inputSingleFromConsole(Scanner scanner, int count) {
        return ConsoleReader.readOneByOne(count);
    }

    public static CustomList<Car> readFromFile(String fileName) throws IOException {
        return CarFileReader.read(fileName);
    }

    public static CustomList<Car> generateRandom(int count) {
        return CarGenerator.generate(count);
    }

    public static void print(CustomList<Car> cars) {
        ConsoleWriter.print(cars);
    }

    public static void print(CustomList<Car> cars, int limit) {
        ConsoleWriter.print(cars, limit);
    }

    public static void print(CustomList<Car> cars, int startIndex, int endIndex) {
        ConsoleWriter.print(cars, startIndex, endIndex);
    }

    public static void write(String fileName, CustomList<Car> cars) throws IOException {
        CarFileWriter.write(fileName, cars);
    }

    public static void write(String fileName, CustomList<Car> cars, int limit) throws IOException {
        CarFileWriter.write(fileName, cars, limit);
    }

    public static void write(String fileName, CustomList<Car> cars, int startIndex, int endIndex) throws IOException {
        CarFileWriter.write(fileName, cars, startIndex, endIndex);
    }

    public static void append(String fileName, CustomList<Car> cars, String comment) throws IOException {
        CarFileWriter.append(fileName, cars, comment);
    }

    public static void append(String fileName, CustomList<Car> cars, String comment, int limit) throws IOException {
        CarFileWriter.append(fileName, cars, comment, limit);
    }

    public static void append(String fileName, CustomList<Car> cars, String comment, int startIndex, int endIndex) throws IOException {
        CarFileWriter.append(fileName, cars, comment, startIndex, endIndex);
    }
}