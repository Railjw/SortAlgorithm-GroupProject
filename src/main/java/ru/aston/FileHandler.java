package ru.aston;

import java.io.*;

public class FileHandler {
    public static CustomList<Car> readFromFile(String filename) throws IOException {
        CustomList<Car> list = new CustomList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    try {
                        String model = parts[0].trim();
                        int power = Integer.parseInt(parts[1].trim());
                        int productionYear = Integer.parseInt(parts[2].trim());
                        
                        if (!model.isEmpty() && power > 0 && productionYear > 1800 && productionYear <= 2026) {
                            list.add(new Car.Builder().model(model).power(power).productionYear(productionYear).build());
                        }
                    } catch (NumberFormatException ignored) {}
                }
            }
        }
        return list;
    }

    public static void appendToFile(String filename, CustomList<Car> list, String comment) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
            bw.write("--- " + comment + " ---\n");
            for (Car car : list) {
                bw.write(String.format("%s, %d, %d\n", car.getModel(), car.getPower(), car.getProductionYear()));
            }
            bw.newLine();
        }
    }
}
