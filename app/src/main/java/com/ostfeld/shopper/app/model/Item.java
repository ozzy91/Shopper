package com.ostfeld.shopper.app.model;

import java.io.Serializable;

/**
 * Created by thomas on 24.04.14.
 */
public class Item implements Serializable {

    private long id;
    private String title;
    private String description;
    private boolean checked;

    public Item(long id, String title) {
        this.id = id;
        this.title = title;
        this.checked = false;
    }

    public Item(long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.checked = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
