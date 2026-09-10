package ru.aston;

import ru.aston.sort.ComparatorFactory;
import ru.aston.sort.SortAlgorithm;
import ru.aston.sort.SortField;
import ru.aston.sort.SortStrategyFactory;
import ru.aston.sort.strategy.BubbleSort;
import ru.aston.sort.strategy.QuickSort;
import ru.aston.sort.strategy.SortStrategy;
import ru.aston.extra.Task4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        ManualTest.runTests();

        Scanner scanner = new Scanner(System.in);
        List<Car> currentList = new ArrayList<>();
        
        String lastActionDescription = "Исходная коллекция";
        CustomList<Car> lastFoundCars = null;

        while (true) {
            System.out.println("=== МЕНЮ УПРАВЛЕНИЯ ===");
            System.out.println("1. Заполнить коллекцию данных");
            System.out.println("2. Отсортировать данные (Базовая / QuickSort)");
            System.out.println("3. Многопоточный поиск вхождений элементов");
            System.out.println("4. Сохранить текущую коллекцию в файл (Добавление)");
            System.out.println("5. Вывести текущую коллекцию в консоль");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                scanner.close();
                System.out.println("Выход из программы выполнен успешно.");
                break;
            }

            switch (choice) {
                case 1:
                    System.out.println("Варианты заполнения:\n1. Вручную\n2. Рандом\n3. Из файла");
                    int fillChoice = scanner.nextInt();
                    System.out.print("Введите длину массива (количество элементов): ");
                    int count = scanner.nextInt();
                    scanner.nextLine();

                    if (fillChoice == 1) {
                        currentList.clear();
                        for (int i = 0; i < count; i++) {
                            System.out.print("Введите данные для машины #" + (i + 1) + " (Модель, Мощность, Год): ");
                            String[] data = scanner.nextLine().split(",");
                            if (data.length == 3) {
                                try {
                                    currentList.add(Car.builder()
                                            .model(data[0].trim())
                                            .power(Integer.parseInt(data[1].trim()))
                                            .productionYear(Integer.parseInt(data[2].trim()))
                                            .build());
                                } catch (Exception e) {
                                    System.out.println("Ошибка валидации ручного ввода. Строка пропущена.");
                                }
                            } else {
                                System.out.println("Неверный формат! Строка должна содержать 2 запятые.");
                            }
                        }
                        lastActionDescription = "Вручную заполненная коллекция";
                        lastFoundCars = null; // Сбрасываем прошлый поиск
                    } else if (fillChoice == 2) {
                        // ИСПРАВЛЕНИЕ: Теперь рандомное заполнение работает через ваш класс
                        // CarInputOutput
                        currentList.clear();
                        currentList.addAll(CarInputOutput.generateRandom(count));
                        System.out.println(
                                "Коллекция успешно заполнена случайными данными (" + currentList.size() + " эл.).");
                        lastActionDescription = "Случайно сгенерированная коллекция";
                        lastFoundCars = null;
                    } else if (fillChoice == 3) {
                        try {
                            CustomList<Car> customLoaded = FileHandler.readFromFile("cars_input.txt");
                            currentList.clear();
                            for (Car car : customLoaded) {
                                currentList.add(car);
                            }
                            System.out.println("Данные прочитаны, валидированы и загружены из файла (загружено "
                                    + currentList.size() + " эл.).");
                            lastActionDescription = "Загружено из файла cars_input.txt";
                            lastFoundCars = null;
                        } catch (IOException e) {
                            System.out.println(
                                    "Ошибка: Не удалось прочитать 'cars_input.txt'. Убедитесь, что файл создан в корне проекта.");
                        }
                    }
                    break;

                case 2:
                    if (currentList.size() == 0) {
                        System.out.println("Коллекция пуста! Сначала заполните её.");
                        break;
                    }

                    System.out.println("Выберите поле для сортировки:\n1. Модель\n2. Мощность\n3. Год");
                    int fieldChoice = scanner.nextInt();

                    SortField sortField = switch (fieldChoice) {
                        case 2 -> SortField.POWER;
                        case 3 -> SortField.PRODUCTION_YEAR;
                        default -> SortField.MODEL;
                    };

                    Comparator<Car> comp = switch (sortField) {
                        case MODEL -> Comparator.comparing(Car::getModel);
                        case POWER -> Comparator.comparingInt(Car::getPower);
                        case PRODUCTION_YEAR -> Comparator.comparingInt(Car::getProductionYear);
                    };

                    System.out.println("Выберите алгоритм:\n1. Пузырек (BubbleSort)\n2. Быстрая (QuickSort)");
                    int algoChoice = scanner.nextInt();

                    SortAlgorithm algorithm = (algoChoice == 2) ? SortAlgorithm.QUICK : SortAlgorithm.BUBBLE;
                    SortStrategy<Car> strategy = SortStrategyFactory.create(algorithm);

                    strategy.sort(currentList, comp);

                    lastActionDescription = "Отсортировано по " + sortField + " с помощью " + algorithm;
                    lastFoundCars = null;

                    System.out.println("Сортировка успешно завершена!");
                    break;

                case 3:
                    if (currentList.size() == 0) {
                        System.out.println("Коллекция пуста!");
                        break;
                    }
                    System.out.print("Введите параметры искомого авто для подсчета (Модель, Мощность, Год): ");
                    String[] sParts = scanner.nextLine().split(",");
                    if (sParts.length == 3) {
                        try {
                            Car target = Car.builder()
                                    .model(sParts[0].trim())
                                    .power(Integer.parseInt(sParts[1].trim()))
                                    .productionYear(Integer.parseInt(sParts[2].trim()))
                                    .build();

                            Task4.calcSameObjectsCountAndPrint(target, currentList);

                            lastFoundCars = new CustomList<>();
                            for (Car car : currentList) {
                                if (target.equals(car)) {
                                    lastFoundCars.add(car);
                                }
                            }
                            lastActionDescription = "Найденные совпадения для объекта: [" + target.getModel() + ", "
                                    + target.getPower() + " л.с.]";

                        } catch (Exception e) {
                            System.out.println("Некорректный формат искомого элемента или ошибка вычислений.");
                        }
                    } else {
                        System.out.println("Неверный формат ввода! Ожидалось 3 значения через запятую.");
                    }
                    break;

                case 4:
                    if (currentList.size() == 0) {
                        System.out.println("Коллекция пуста! Нечего сохранять.");
                        break;
                    }
                    try {
                        CustomList<Car> customToSave;

                        if (lastFoundCars != null && lastFoundCars.size() > 0) {
                            customToSave = lastFoundCars;
                            System.out.println(
                                    "Сохраняем результаты последнего поиска (" + lastFoundCars.size() + " эл.)...");
                        } else {
                            customToSave = new CustomList<>();
                            for (Car car : currentList) {
                                customToSave.add(car);
                            }
                            System.out.println("Сохраняем текущую коллекцию автомобилей...");
                        }

                        FileHandler.appendToFile("cars_output.txt", customToSave, lastActionDescription);
                        System.out.println("Данные успешно дозаписаны в файл 'cars_output.txt'");

                        lastFoundCars = null;
                    } catch (IOException e) {
                        System.out.println("Ошибка записи в файл: " + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.println("\n--- СОДЕРЖИМОЕ КОЛЛЕКЦИИ (" + currentList.size() + " эл.) ---");
                    for (Car c : currentList) {
                        System.out.println("Машина: " + c.getModel() + " | Мощность: " + c.getPower() + " л.с. | Год: "
                                + c.getProductionYear());
                    }
                    System.out.println("-----------------------------------------\n");
                    break;
                default:
                    System.out.println("Неверный пункт меню.");
            }
        }
    }
}
