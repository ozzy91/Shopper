package com.ostfeld.shopper.app;

import com.ostfeld.shopper.app.interfaces.ListManager;
import com.ostfeld.shopper.app.model.Item;
import com.ostfeld.shopper.app.tasks.AddItemTask;
import com.ostfeld.shopper.app.tasks.GetItemsTask;
import com.ostfeld.shopper.app.tasks.RemoveItemTask;
import com.ostfeld.shopper.app.tasks.SetCheckedTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas on 24.04.14.
 */
public class DataManager {
    public static final String SERVER_ADDRESS = "http://10.0.2.2:8080";
    public static final String API_ADDRESS = "/_ah/api/";

    public DataManager() {

    }

    public void addItem(Item item) {
        AddItemTask task = new AddItemTask();
        task.execute(item);
    }

    public void deleteItem(Item item) {
        RemoveItemTask task = new RemoveItemTask();
        task.execute(item);
    }

    public void setItemChecked(Item item) {
        SetCheckedTask task = new SetCheckedTask();
        task.execute(item);
    }

    public List<Item> getItems(ListManager listManager) {
        ArrayList<Item> items = new ArrayList<Item>();
        GetItemsTask task = new GetItemsTask(listManager);
        task.execute();

        return items;
    }
}
