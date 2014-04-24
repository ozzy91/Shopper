package com.ostfeld.shopper.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas on 24.04.14.
 */
public class ShoppingList implements Serializable {

    private String title;
    private String description;
    private boolean checked;
    private List<Item> items;

    public ShoppingList(String title) {
        this.title = title;
        this.items = new ArrayList<Item>();
        this.checked = false;
    }

    public ShoppingList(String title, String description) {
        this.title = title;
        this.description = description;
        this.items = new ArrayList<Item>();
        this.checked = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

}
