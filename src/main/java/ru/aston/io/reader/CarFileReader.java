package ru.aston.io.reader;

import ru.aston.Car;
import ru.aston.CustomList;
import ru.aston.io.parser.CarParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

public class CarFileReader {

    public static CustomList<Car> read(String fileName) throws IOException {
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
}