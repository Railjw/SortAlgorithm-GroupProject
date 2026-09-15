package ru.aston.ui.actions.manage;

import ru.aston.ui.state.ApplicationContext;
import ru.aston.ui.actions.MenuAction;

public class ResetAction implements MenuAction {
    @Override
    public void execute(ApplicationContext context) {
        context.resetToOriginal();
    }
}
