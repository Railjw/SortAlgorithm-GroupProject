package ru.aston.UI.menu;

import ru.aston.UI.actions.search.CountOccurrencesAction;
import ru.aston.UI.actions.display.DisplayCarsAction;
import ru.aston.UI.actions.display.SaveToFileAction;
import ru.aston.UI.actions.fill.LoadFromFileAction;
import ru.aston.UI.actions.fill.ManualFillAction;
import ru.aston.UI.actions.fill.RandomFillAction;
import ru.aston.UI.actions.manage.*;
import ru.aston.UI.actions.sort.SelectAlgorithmAction;
import ru.aston.UI.actions.sort.SortAction;
import ru.aston.sort.SortField;

public class MenuFactory {

    public static MenuItem createMainMenu() {
        CompositeMenu mainMenu = new CompositeMenu("Main Menu");

        mainMenu.addChild(createFillMenu());
        mainMenu.addChild(createSettingsMenu());
        mainMenu.addChild(createSortMenu());
        mainMenu.addChild(createDisplayMenu());
        mainMenu.addChild(createExtraMenu());
        mainMenu.addChild(createManageMenu());

        return mainMenu;
    }

    private static CompositeMenu createFillMenu() {
        CompositeMenu menu = new CompositeMenu("Fill Collection");
        menu.addChild(new ActionMenuItem("Load from file", new LoadFromFileAction()));
        menu.addChild(new ActionMenuItem("Generate randomly", new RandomFillAction()));
        menu.addChild(new ActionMenuItem("Enter manually", new ManualFillAction()));
        return menu;
    }

    private static CompositeMenu createSettingsMenu() {
        CompositeMenu menu = new CompositeMenu("Sort Settings");
        menu.addChild(new ActionMenuItem("Select sorting algorithm", new SelectAlgorithmAction()));
        return menu;
    }

    private static CompositeMenu createSortMenu() {
        CompositeMenu menu = new CompositeMenu("Sort");

        CompositeMenu powerSortMenu = new CompositeMenu("Sort by Power");
        powerSortMenu.addChild(new ActionMenuItem("Normal sort",
                new SortAction(SortField.POWER, false)));
        powerSortMenu.addChild(new ActionMenuItem("Special sort (even/odd)",
                new SortAction(SortField.POWER, true)));
        menu.addChild(powerSortMenu);

        menu.addChild(new ActionMenuItem("Sort by Model",
                new SortAction(SortField.MODEL, false)));
        menu.addChild(new ActionMenuItem("Sort by Year",
                new SortAction(SortField.PRODUCTION_YEAR, false)));

        return menu;
    }

    private static CompositeMenu createDisplayMenu() {
        CompositeMenu menu = new CompositeMenu("Display Results");
        menu.addChild(new ActionMenuItem("Display in console", new DisplayCarsAction()));
        menu.addChild(new ActionMenuItem("Save to file", new SaveToFileAction()));
        return menu;
    }

    private static CompositeMenu createExtraMenu() {
        CompositeMenu menu = new CompositeMenu("Additional Operations");
        menu.addChild(new ActionMenuItem("Count occurrences (multithreaded)",
                new CountOccurrencesAction()));
        menu.addChild(new ActionMenuItem("Show action history",
                new ShowHistoryAction()));
        return menu;
    }

    private static CompositeMenu createManageMenu() {
        CompositeMenu menu = new CompositeMenu("Manage Collection");
        menu.addChild(new ActionMenuItem("Reset to original", new ResetAction()));
        menu.addChild(new ActionMenuItem("Clear collection", new ClearAction()));
        menu.addChild(new ActionMenuItem("Undo", new UndoAction()));
        menu.addChild(new ActionMenuItem("Redo", new RedoAction()));
        menu.addChild(new ActionMenuItem("Show action history", new ShowHistoryAction()));
        return menu;
    }
}