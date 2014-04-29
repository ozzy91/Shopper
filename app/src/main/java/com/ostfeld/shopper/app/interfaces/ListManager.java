package com.ostfeld.shopper.app.interfaces;

import com.ostfeld.shopper.backend.listApi.model.ListItem;

import java.util.List;

/**
 * Created by thomas on 25.04.14.
 */
public interface ListManager {
    public void receiveList(List<ListItem> items);
}
