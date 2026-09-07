package ru.aston.UI.actions.manage;

import ru.aston.UI.state.ApplicationContext;
import ru.aston.UI.actions.MenuAction;

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