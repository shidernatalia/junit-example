package parameterized;

import java.util.List;

public class TodoList {
    private String technology;
    private List<String> itemsToAdd;
    private List<String> itemsToCheck;
    private int expectedItemsLeft;

    public TodoList(String technology, List<String> itemsToAdd, List<String> itemsToCheck, int expectedItemsLeft) {
        this.technology = technology;
        this.itemsToAdd = itemsToAdd;
        this.itemsToCheck = itemsToCheck;
        this.expectedItemsLeft = expectedItemsLeft;
    }

    public void setItemsToAdd(List<String> itemsToAdd) {
        this.itemsToAdd = itemsToAdd;
    }

    public void setItemsToCheck(List<String> itemsToCheck) {
        this.itemsToCheck = itemsToCheck;
    }

    public void setExpectedItemsLeft(int expectedItemsLeft) {
        this.expectedItemsLeft = expectedItemsLeft;
    }

    public String getTechnology() {
        return technology;
    }

    public List<String> getItemsToAdd() {
        return itemsToAdd;
    }

    public List<String> getItemsToCheck() {
        return itemsToCheck;
    }

    public int getExpectedItemsLeft() {
        return expectedItemsLeft;
    }
}
