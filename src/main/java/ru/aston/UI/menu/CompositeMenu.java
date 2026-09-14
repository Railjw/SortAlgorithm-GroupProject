package ru.aston.UI.menu;

import java.util.List;

import ru.aston.CustomCollection.ListFactory;
import ru.aston.UI.state.ApplicationContext;

public class CompositeMenu implements MenuItem {

    private final String title;
    private final List<MenuItem> children;
    private MenuItem parent;

    public CompositeMenu(String title) {
        this.title = title;
        this.children = ListFactory.create();
    }

    @Override
    public MenuItem addChild(MenuItem child) {
        if (child == null) {
            throw new IllegalArgumentException("Child cannot be null");
        }
        child.setParent(this);
        children.add(child);
        return this;
    }

    @Override
    public MenuItem removeChild(MenuItem child) {
        if (child != null) {
            children.remove(child);
            child.setParent(null);
        }
        return this;
    }

    @Override
    public MenuItem getChild(int index) {
        if (index >= 0 && index < children.size()) {
            return children.get(index);
        }
        return null;
    }

    @Override
    public int getChildrenCount() {
        return children.size();
    }

    @Override
    public void display() {
        System.out.println("\n=== " + title.toUpperCase() + " ===");

        if (children.isEmpty()) {
            System.out.println("  (empty)");
        }

        for (int i = 0; i < children.size(); i++) {
            MenuItem child = children.get(i);
            System.out.println((i + 1) + ". " + child.getTitle());
        }

        System.out.println("0. Back");
        System.out.println("-1. Exit");
        System.out.print("\nYour choice: ");
    }

    @Override
    public void execute(ApplicationContext context) {
        context.setCurrentMenu(this);

        while (context.isRunning()) {
            display();
            try {
                String input = context.getScanner().nextLine().trim();
                int choice = Integer.parseInt(input);

                if (choice == -1) {
                    context.setRunning(false);
                    System.out.println("Exiting application...");
                    break;
                } else if (choice == 0) {
                    if (parent != null) {
                        parent.execute(context);
                        break;
                    } else {
                        System.out.println("You are already in the main menu");
                    }
                } else if (choice > 0 && choice <= children.size()) {
                    MenuItem selected = children.get(choice - 1);
                    if (selected instanceof CompositeMenu) {
                        selected.execute(context);
                    } else {
                        selected.execute(context);
                        if (context.isRunning()) {
                            context.waitForEnter();
                        }
                    }
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    @Override
    public MenuItem getParent() {
        return parent;
    }

    @Override
    public void setParent(MenuItem parent) {
        this.parent = parent;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title + " (" + children.size() + " items)";
    }
}