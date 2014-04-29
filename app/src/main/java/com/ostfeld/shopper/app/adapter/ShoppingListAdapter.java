package com.ostfeld.shopper.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ostfeld.shopper.app.R;
import com.ostfeld.shopper.app.model.Item;
import com.ostfeld.shopper.app.model.ShoppingList;
import com.ostfeld.shopper.app.tasks.RemoveItemTask;
import com.ostfeld.shopper.app.tasks.SetCheckedTask;
import com.ostfeld.shopper.app.views.ListItemView;

import java.util.List;

/**
 * Created by thomas on 24.04.14.
 */
public class ShoppingListAdapter extends ArrayAdapter<Item> {

    private Context context;
    private int resourceId;
    private ShoppingList shoppingList;

    public ShoppingListAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.resourceId = resource;
    }

    @Override
    public int getCount() {
        if (shoppingList == null) {
            return 0;
        } else {
            return shoppingList.getItems().size();
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItemView view = (ListItemView) convertView;
        if (view == null) {
 //           convertView = View.inflate(context, resourceId, null);
            view = new ListItemView(getContext());
            ViewHolder holder = new ViewHolder();
            holder.title = (TextView) view.findViewById(R.id.txt_item_title);
            holder.checkBox = (CheckBox) view.findViewById(R.id.viw_item_checkbox);
            holder.btnDelete = (ImageButton) view.findViewById(R.id.btn_delete);
            holder.btnEdit = (ImageButton) view.findViewById(R.id.btn_edit);
            view.setTag(holder);
        }

        final Item item = shoppingList.getItems().get(position);
        final ViewHolder holder = (ViewHolder) view.getTag();
        holder.title.setText(item.getTitle());
        holder.checkBox.setChecked(item.isChecked());

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.viw_item_checkbox:
                        boolean checked = ((CheckBox) view).isChecked();
                        item.setChecked(checked);
                        new SetCheckedTask().execute(item);
                        break;
                    case R.id.btn_delete:
                        shoppingList.getItems().remove(shoppingList.getItems().indexOf(item));
                        notifyDataSetChanged();
                        new RemoveItemTask().execute(item);
                        break;
                    case R.id.btn_edit:

                        break;
                    default:
                        break;
                }
            }
        };

        holder.checkBox.setOnClickListener(clickListener);
        holder.btnDelete.setOnClickListener(clickListener);
        holder.btnEdit.setOnClickListener(clickListener);

        return view;
    }

    public void setShoppingList(ShoppingList list) {
        this.shoppingList = list;
    }

    static class ViewHolder {
        TextView title;
        CheckBox checkBox;
        ImageButton btnDelete;
        ImageButton btnEdit;
    }
}
