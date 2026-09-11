package ru.aston.io.generator;

import ru.aston.Car;
import ru.aston.CustomList;

import java.util.Random;

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

    public static CustomList<Car> generate(int count) {
        if (count <= 0) {
            return new CustomList<>();
        }

        Random rand = new Random();

        return CustomList.generate(() -> Car.builder()
                .power(rand.nextInt(MAX_POWER - MIN_POWER + 1) + MIN_POWER)
                .model(MODELS[rand.nextInt(MODELS.length)])
                .productionYear(rand.nextInt(MAX_YEAR - MIN_YEAR + 1) + MIN_YEAR)
                .build(), count);
    }
}