package com.ostfeld.shopper.app.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ostfeld.shopper.app.DataManager;
import com.ostfeld.shopper.app.R;
import com.ostfeld.shopper.app.adapter.ShoppingListAdapter;
import com.ostfeld.shopper.app.interfaces.ListManager;
import com.ostfeld.shopper.app.model.Item;
import com.ostfeld.shopper.app.model.ShoppingList;
import com.ostfeld.shopper.app.tasks.AddItemTask;
import com.ostfeld.shopper.app.views.ListItemView;
import com.ostfeld.shopper.backend.listApi.model.ListItem;

import java.util.List;

/**
 * A simple {@link android.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.ostfeld.shopper.app.fragments.ShoppingListFragment.OnListFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShoppingListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShoppingListFragment extends Fragment implements View.OnClickListener, ListManager, AdapterView.OnItemClickListener, TextView.OnEditorActionListener {
    public static final String TAG = ShoppingListFragment.class.getSimpleName();

    private OnListFragmentInteractionListener mListener;

    private DataManager dataManager;
    private ShoppingList shoppingList;
    private ShoppingListAdapter adapter;

    private ProgressBar pgbLoading;
    private ListView listView;
    private TextView txtTitle;
    private EditText editName;
    private ImageButton btnAdd;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShoppingListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShoppingListFragment newInstance() {
        ShoppingListFragment fragment = new ShoppingListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            this.shoppingList = (ShoppingList) savedInstanceState.getSerializable("list");
        }
        dataManager = new DataManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_shopping_list, container, false);
        listView = (ListView) fragmentView.findViewById(R.id.list_shopping);
        txtTitle = (TextView) fragmentView.findViewById(R.id.txt_list_title);
        editName = (EditText) fragmentView.findViewById(R.id.edit_text_item_name);
        btnAdd = (ImageButton) fragmentView.findViewById(R.id.btn_add);
        pgbLoading = (ProgressBar) fragmentView.findViewById(R.id.pgb_loading);

        adapter = new ShoppingListAdapter(getActivity(), R.layout.itm_shopping_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        btnAdd.setOnClickListener(this);
        editName.setOnEditorActionListener(this);
        editName.clearFocus();

        refresh();

        return fragmentView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("list", shoppingList);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnListFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void setShoppingList(ShoppingList list) {
        this.shoppingList = list;

        if (txtTitle != null) {
            txtTitle.setText(list.getTitle());
        }
        if (adapter != null) {
            adapter.setShoppingList(list);
            adapter.notifyDataSetChanged();
        }
    }

    public void refresh() {
        Log.i(TAG, "refresh called");
        dataManager.getItems(this);
    }

    private void addItemFromEditText() {
        String name = editName.getText().toString().trim();
        if (name == "" || name.isEmpty()) {
            return;
        }

        Item item = new Item((long) (Math.random() * 10000), name);
        shoppingList.addItem(item);
        adapter.notifyDataSetChanged();

        editName.setText("");

        listView.setSelection(adapter.getCount() - 1);

        new AddItemTask().execute(item);
    }

    @Override
    public void receiveList(List<ListItem> items) {
        if (items == null) {
            getActivity().invalidateOptionsMenu();
            Toast.makeText(getActivity(), "Fehler beim Laden", Toast.LENGTH_LONG).show();
            return;
        }

        ShoppingList list = new ShoppingList("Einkauf");
        for (ListItem item : items) {
            Item convertedItem = new Item(item.getId(), item.getTitle());
            convertedItem.setChecked(item.getChecked());
            list.addItem(convertedItem);
        }
        pgbLoading.setVisibility(View.GONE);
        setShoppingList(list);
        getActivity().invalidateOptionsMenu();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                addItemFromEditText();
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (view instanceof ListItemView) {
            ListItemView listItemView = (ListItemView) view;
            listItemView.toggleButtonsVisibility();
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            addItemFromEditText();
            return true;
        }
        return false;
    }

    public interface OnListFragmentInteractionListener {
        public void onListFragmentInteraction(Uri uri);
    }

}
