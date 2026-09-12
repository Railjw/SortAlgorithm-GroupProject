package ru.aston.UI.state;

import ru.aston.CustomCollection.ListFactory;

import java.util.List;

public class StateManager {
    private static final int MAX_HISTORY_SIZE = 20;
    private final List<CarsStateMemento> history;
    private int currentIndex;

    public StateManager() {
        this.history = ListFactory.create();
        this.currentIndex = -1;
    }

    public void addState(CarsStateMemento memento) {
        if (currentIndex < history.size() - 1) {
            history.subList(currentIndex + 1, history.size()).clear();
        }

        history.add(memento);
        currentIndex = history.size() - 1;

        if (history.size() > MAX_HISTORY_SIZE) {
            history.removeFirst();
            currentIndex--;
        }
    }

    public boolean canUndo() {
        return currentIndex > 0;
    }

    public boolean canRedo() {
        return currentIndex < history.size() - 1;
    }

    public CarsStateMemento undo() {
        if (!canUndo()) {
            return null;
        }
        currentIndex--;
        return history.get(currentIndex);
    }

    public CarsStateMemento redo() {
        if (!canRedo()) {
            return null;
        }
        currentIndex++;
        return history.get(currentIndex);
    }

    public CarsStateMemento getCurrentState() {
        if (currentIndex >= 0 && currentIndex < history.size()) {
            return history.get(currentIndex);
        }
        return null;
    }

    public void clear() {
        history.clear();
        currentIndex = -1;
    }

    public String getHistorySummary() {
        if (history.isEmpty()) {
            return "No actions in history";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Action History (").append(history.size()).append(" actions):\n");

        for (int i = 0; i < history.size(); i++) {
            String marker = (i == currentIndex) ? "-> " : "   ";
            sb.append(marker).append(i + 1).append(". ").append(history.get(i)).append("\n");
        }

        return sb.toString();
    }

    public List<CarsStateMemento> getHistory() {
        return ListFactory.create();
    }
}