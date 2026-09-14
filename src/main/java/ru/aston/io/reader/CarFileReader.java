package ru.aston.io.reader;

import ru.aston.model.Car;
import ru.aston.io.parser.CarParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class CarFileReader {

    public static Stream<Car> read(String fileName) throws IOException {
        return Files.lines(Paths.get(fileName))
                .filter(line -> !line.trim().isEmpty())
                .map(CarParser::parseLine)
                .filter(Optional::isPresent)
                .map(Optional::get);
    }
}