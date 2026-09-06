package ru.aston.UI.actions.manage;

import ru.aston.UI.state.ApplicationContext;
import ru.aston.UI.actions.MenuAction;

public class UndoAction implements MenuAction {

    @Override
    public void execute(ApplicationContext context) {
        if (!context.canUndo()) {
            System.out.println("Nothing to undo");
            return;
        }
        context.undo();
    }
}