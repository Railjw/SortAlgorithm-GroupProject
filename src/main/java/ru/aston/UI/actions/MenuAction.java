package ru.aston.UI.actions;

import ru.aston.UI.state.ApplicationContext;

@FunctionalInterface
public interface MenuAction {
    void execute(ApplicationContext context);
}
