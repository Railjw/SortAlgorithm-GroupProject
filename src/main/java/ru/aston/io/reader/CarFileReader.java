package ru.aston.io.reader;

import ru.aston.Car;
import ru.aston.CustomList;
import ru.aston.io.parser.CarParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class CarFileReader {

    public static CustomList<Car> read(String fileName) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            CustomList<Car> cars = new CustomList<>();
            AtomicInteger lineNumber = new AtomicInteger(0);

            lines.filter(line -> !line.trim().isEmpty())
                    .map(line -> new NumberedLine(lineNumber.incrementAndGet(), line))
                    .map(nl -> CarParser.parseLine(nl.line())
                            .or(() -> {
                                System.err.println("Skipping line " + nl.number() + ": " + nl.line());
                                return Optional.empty();
                            }))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .forEach(cars::add);

            return cars;
        }
    }

    private record NumberedLine(int number, String line) {}
}