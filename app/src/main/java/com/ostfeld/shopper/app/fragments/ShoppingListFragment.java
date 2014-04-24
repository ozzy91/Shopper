package com.ostfeld.shopper.app.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.ostfeld.shopper.app.R;
import com.ostfeld.shopper.app.adapter.ShoppingListAdapter;
import com.ostfeld.shopper.app.model.ShoppingList;

/**
 * A simple {@link android.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.ostfeld.shopper.app.fragments.ShoppingListFragment.OnListFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShoppingListFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class ShoppingListFragment extends Fragment {
    public static final String TAG = ShoppingListFragment.class.getSimpleName();

    private OnListFragmentInteractionListener mListener;

    private ShoppingList shoppingList;
    private ShoppingListAdapter adapter;

    private ListView listView;
    private TextView txtTitle;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_shopping_list, container, false);
        listView = (ListView) fragmentView.findViewById(R.id.list_shopping);
        txtTitle = (TextView) fragmentView.findViewById(R.id.txt_list_title);

        if (this.shoppingList != null) {
            txtTitle.setText(this.shoppingList.getTitle());
            adapter = new ShoppingListAdapter(getActivity(), R.layout.itm_shopping_list, this.shoppingList.getItems());
            listView.setAdapter(adapter);
        }

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

    public void setShoppingList(ShoppingList list) {
        this.shoppingList = list;

        if (txtTitle != null) {
            txtTitle.setText(list.getTitle());
        }
        if (listView != null) {
            adapter = new ShoppingListAdapter(getActivity(), R.layout.itm_shopping_list, this.shoppingList.getItems());
            listView.setAdapter(adapter);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        public void onListFragmentInteraction(Uri uri);
    }

}
