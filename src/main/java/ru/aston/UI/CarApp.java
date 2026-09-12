package ru.aston.UI;

import ru.aston.UI.menu.MenuFactory;
import ru.aston.UI.menu.MenuItem;
import ru.aston.UI.state.ApplicationContext;

public class CarApp {

    public static void main(String[] args) {
        try {
            System.out.println("=== CAR APPLICATION ===");

            ApplicationContext context = new ApplicationContext();

            MenuItem rootMenu = MenuFactory.createMainMenu();

            rootMenu.execute(context);
        } catch (Exception e) {
            System.err.println("Critical error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}