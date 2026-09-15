package ru.aston.ui.actions;

import ru.aston.ui.state.ApplicationContext;

@FunctionalInterface
public interface MenuAction {
    void execute(ApplicationContext context);
}
