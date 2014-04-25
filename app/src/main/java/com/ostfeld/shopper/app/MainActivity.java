package com.ostfeld.shopper.app;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

import com.ostfeld.shopper.app.fragments.ShoppingListFragment;
import com.ostfeld.shopper.app.model.Item;
import com.ostfeld.shopper.app.model.ShoppingList;
import com.ostfeld.shopper.app.tasks.AddItemTask;
import com.ostfeld.shopper.app.tasks.GetItemsTask;


public class MainActivity extends Activity implements ShoppingListFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            ShoppingListFragment fragment = ShoppingListFragment.newInstance();
            fragment.setShoppingList(createTestList());
            getFragmentManager().beginTransaction()
                    .add(R.id.container, fragment, ShoppingListFragment.TAG)
                    .commit();
        }

        new AddItemTask().execute(new Item(3, "Milch"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            new GetItemsTask().execute();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListFragmentInteraction(Uri uri) {

    }

    private ShoppingList createTestList() {
        ShoppingList list = new ShoppingList("Lidl");
        list.addItem(new Item(1, "Wasser"));
        list.addItem(new Item(2, "Toast"));
        list.addItem(new Item(3, "Hackfleisch"));
        list.addItem(new Item(4, "Mais"));
        list.addItem(new Item(5, "Käse"));
        list.addItem(new Item(6, "Milch"));
        list.addItem(new Item(7, "Kaffee"));
        Item item1 = new Item(8, "Wasser");
        item1.setChecked(true);
        list.addItem(item1);
        Item item2 = new Item(9, "Toast");
        item2.setChecked(true);
        list.addItem(item2);
        Item item3 = new Item(10, "Hackfleisch");
        item3.setChecked(true);
        list.addItem(item3);
        list.addItem(new Item(11, "Mais"));
        list.addItem(new Item(12, "Käse"));
        list.addItem(new Item(13, "Milch"));
        list.addItem(new Item(14, "Kaffee"));

        return list;
    }
}
