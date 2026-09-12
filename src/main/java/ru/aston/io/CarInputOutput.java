package ru.aston.io;

import ru.aston.model.Car;
import ru.aston.io.reader.ConsoleReader;
import ru.aston.io.reader.CarFileReader;
import ru.aston.io.writer.ConsoleWriter;
import ru.aston.io.writer.CarFileWriter;
import ru.aston.io.generator.CarGenerator;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class CarInputOutput {

    public static Car inputFromConsole(Scanner scanner) {
        return ConsoleReader.readOne(scanner);
    }

    public static List<Car> readFromFile(String fileName) throws IOException {
        return CarFileReader.read(fileName);
    }

    public static List<Car> generateRandom(int count) {
        return CarGenerator.generate(count);
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