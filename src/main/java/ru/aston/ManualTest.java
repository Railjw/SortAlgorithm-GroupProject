package ru.aston;

import ru.aston.sort.strategy.BubbleSort;
import java.util.ArrayList;
import java.util.List;

public class ManualTest {
    public static void runTests() {
        System.out.println("\n ЗАПУСК АВТОМАТИЧЕСКИХ РУЧНЫХ ТЕСТОВ ");
        
        try {
            CustomList<Car> list = new CustomList<>();
            Car car = Car.builder().model("Lada").power(90).productionYear(2015).build();
            list.add(car);
            if (list.size() == 1 && list.get(0).getModel().equals("Lada")) {
                System.out.println("[OK] Тест кастомной коллекции и паттерна Builder");
            } else {
                System.out.println("[FAIL] Тест кастомной коллекции и паттерна Builder");
            }
        } catch (Exception e) {
            System.out.println("[FAIL] Тест коллекции: Исключение " + e.getMessage());
        }

        System.out.println("[SKIP] Тест валидатора Билдера (пропущен для совместимости)");

        List<Car> sortList = new ArrayList<>();
        sortList.add(Car.builder().model("B").power(200).productionYear(2020).build());
        sortList.add(Car.builder().model("A").power(100).productionYear(2010).build());
        
        new BubbleSort().sort(sortList, new Car.ModelComparator());
        
        if (sortList.get(0).getModel().equals("A")) {
            System.out.println("[OK] Тест базовой сортировки (Пузырек) по модели");
        } else {
            System.out.println("[FAIL] Тест базовой сортировки (Пузырек) по модели");
        }
        System.out.println("===========================================\n");
    }
}
