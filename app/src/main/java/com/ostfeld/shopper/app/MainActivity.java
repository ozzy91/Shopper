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
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListFragmentInteraction(Uri uri) {

    }

    private ShoppingList createTestList() {
        ShoppingList list = new ShoppingList("Lidl");
        list.addItem(new Item("Wasser"));
        list.addItem(new Item("Toast"));
        list.addItem(new Item("Hackfleisch"));
        list.addItem(new Item("Mais"));
        list.addItem(new Item("Käse"));
        list.addItem(new Item("Milch"));
        list.addItem(new Item("Kaffee"));
        Item item1 = new Item("Wasser");
        item1.setChecked(true);
        list.addItem(item1);
        Item item2 = new Item("Toast");
        item2.setChecked(true);
        list.addItem(item2);
        Item item3 = new Item("Hackfleisch");
        item3.setChecked(true);
        list.addItem(item3);
        list.addItem(new Item("Mais"));
        list.addItem(new Item("Käse"));
        list.addItem(new Item("Milch"));
        list.addItem(new Item("Kaffee"));

        return list;
    }
}
