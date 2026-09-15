package ru.aston.ui.actions.manage;

import ru.aston.ui.state.ApplicationContext;
import ru.aston.ui.actions.MenuAction;

public class RedoAction implements MenuAction {

    @Override
    public void execute(ApplicationContext context) {
        if (!context.canRedo()) {
            System.out.println("Nothing to redo");
            return;
        }
        context.redo();
    }
}