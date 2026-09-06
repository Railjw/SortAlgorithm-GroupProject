package ru.aston.UI.menu;

import ru.aston.UI.actions.MenuAction;
import ru.aston.UI.state.ApplicationContext;

public class ActionMenuItem implements MenuItem {

    private final String title;

    private final MenuAction action;

    private MenuItem parent;

    public ActionMenuItem(String title, MenuAction action) {
        this.title = title;
        this.action = action;
    }

    @Override
    public void display() {
        // Leaf element is not displayed separately
    }

    @Override
    public void execute(ApplicationContext context) {
        System.out.println("\n-> Executing: " + title);
        action.execute(context);
    }

    @Override
    public MenuItem getParent() {
        return parent;
    }

    @Override
    public void setParent(MenuItem parent) {
        this.parent = parent;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}