package com.ostfeld.shopper.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.NotFoundException;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(name = "listApi", version = "v1", namespace = @ApiNamespace(ownerDomain = "backend.shopper.ostfeld.com", ownerName = "backend.shopper.ostfeld.com", packagePath=""))
public class MyEndpoint {

    public static List<ListItem> items = new ArrayList<ListItem>();

    @ApiMethod(name = "add")
    public ListItem addItem(@Named("id") long id, @Named("title") String title) throws NotFoundException {
        if (items.contains(new ListItem(id))) {
            throw new NotFoundException("ListItem with this ID already exists.");
        }
        ListItem item = new ListItem(id, title);
        items.add(item);

        return item;
    }

    @ApiMethod(name = "remove")
    public void removeItem(@Named("id") long id) throws NotFoundException {
        int index = items.indexOf(new ListItem(id));
        if (index == -1) {
            throw new NotFoundException("ListItem does not exists.");
        }
        items.remove(index);
    }

    @ApiMethod(name = "update")
    public ListItem setItemChecked(@Named("id") long id, @Named("checked") boolean checked) throws NotFoundException {
        int index = items.indexOf(new ListItem(id));
        if (index == -1) {
            throw new NotFoundException("ListItem does not exists.");
        }
        ListItem item = items.get(index);
        item.setChecked(checked);

        return item;
    }

    @ApiMethod(name = "list")
    public List<ListItem> getItems() {
        return items;
    }

}