package ru.aston.ui.actions.manage;

import ru.aston.ui.state.ApplicationContext;
import ru.aston.ui.actions.MenuAction;

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