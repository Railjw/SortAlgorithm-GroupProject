package ru.aston.io.generator;

import ru.aston.model.Car;
import ru.aston.CustomCollection.ListFactory;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CarGenerator {

    private static final String[] MODELS = {
            "Lada Granta", "Lada Vesta", "Lada Iskra",
            "BMW X5", "BMW M5", "BMW X3",
            "Audi TT", "Audi A4", "Audi R8",
            "Toyota RAV4", "Toyota Camry",
            "Honda CR-V", "Honda Civic"
    };

    private static final int MIN_POWER = 50;
    private static final int MAX_POWER = 549;
    private static final int MIN_YEAR = 1886;
    private static final int MAX_YEAR = 2026;

    public static List<Car> generate(int count) {

        List<Car> result = ListFactory.create(ListFactory.ListType.LINKED);

        if (count <= 0) {
            return result;
        }

        Random rand = new Random();

        Supplier<Car> supplier = () -> Car.builder()
                .power(rand.nextInt(MAX_POWER - MIN_POWER + 1) + MIN_POWER)
                .model(MODELS[rand.nextInt(MODELS.length)])
                .productionYear(rand.nextInt(MAX_YEAR - MIN_YEAR + 1) + MIN_YEAR)
                .build();


        return Stream.generate(supplier)
                .limit(count)
                .collect(Collectors.toCollection(ListFactory::create));
    }
}