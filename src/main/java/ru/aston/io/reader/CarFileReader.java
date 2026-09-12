package ru.aston.io.reader;

import ru.aston.model.Car;
import ru.aston.CustomCollection.ListFactory;
import ru.aston.io.parser.CarParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class CarFileReader {

    public static List<Car> read(String fileName) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            List<Car> cars = ListFactory.create(ListFactory.ListType.LINKED);

            lines.filter(line -> !line.trim().isEmpty())
                    .map(CarParser::parseLine)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .forEach(cars::add);

            return cars;
        }
    }
}