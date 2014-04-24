package com.ostfeld.shopper.app.model;

import java.io.Serializable;

/**
 * Created by thomas on 24.04.14.
 */
public class Item implements Serializable {

    private String title;
    private String description;
    private boolean checked;

    public Item(String title) {
        this.title = title;
        this.checked = false;
    }

    public Item(String title, String description) {
        this.title = title;
        this.description = description;
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
}
