package ru.aston.UI.menu;

import ru.aston.UI.state.ApplicationContext;

public interface MenuItem {

    void display();

    void execute(ApplicationContext context);

    MenuItem getParent();

    void setParent(MenuItem parent);

    String getTitle();

    default MenuItem addChild(MenuItem child) {
        throw new UnsupportedOperationException("Cannot add child to leaf");
    }

    default MenuItem removeChild(MenuItem child) {
        throw new UnsupportedOperationException("Cannot remove child from leaf");
    }

    default MenuItem getChild(int index) {
        throw new UnsupportedOperationException("Leaf has no children");
    }

    default int getChildrenCount() {
        return 0;
    }
}