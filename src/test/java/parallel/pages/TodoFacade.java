package parallel.pages;

import java.util.List;

public class TodoFacade {
    private final MainPage mainPage;
    private final TodoAppPage todoAppPage;

    public TodoFacade(MainPage mainPage, TodoAppPage todoAppPage) {
        this.mainPage = mainPage;
        this.todoAppPage = todoAppPage;
    }
    public void verifyTodoListCreatedSuccessfully(String technology, List<String> itemsToAdd, List<String> itemsToCheck, int expectedItemsLeft){
        mainPage.open();
        mainPage.openTechnologyApp(technology);
        itemsToAdd.forEach(item -> todoAppPage.addNewTodoItem(item));
        itemsToCheck.forEach(item -> todoAppPage.getItemCheckBox(item).click());
        todoAppPage.assertLeftItems(expectedItemsLeft);
    }
}