package ru.aston.UI.actions.manage;

import ru.aston.UI.state.ApplicationContext;
import ru.aston.UI.actions.MenuAction;

public class ShowHistoryAction implements MenuAction {

    @Override
    public void execute(ApplicationContext context) {
        context.showHistory();
    }
}