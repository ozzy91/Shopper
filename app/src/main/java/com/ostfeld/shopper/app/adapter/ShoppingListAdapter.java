package com.ostfeld.shopper.app.adapter;

import android.content.Context;
import android.graphics.Color;
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
        ListItemView tmpView = (ListItemView) convertView;
        if (tmpView == null) {
            tmpView = new ListItemView(getContext());
            ViewHolder holder = new ViewHolder();
            holder.title = (TextView) tmpView.findViewById(R.id.txt_item_title);
            holder.checkBox = (CheckBox) tmpView.findViewById(R.id.viw_item_checkbox);
            holder.btnDelete = (ImageButton) tmpView.findViewById(R.id.btn_delete);
            holder.btnEdit = (ImageButton) tmpView.findViewById(R.id.btn_edit);
            tmpView.setTag(holder);
        }

        final ListItemView itemView = tmpView;

        final Item item = shoppingList.getItems().get(position);
        final ViewHolder holder = (ViewHolder) itemView.getTag();
        holder.title.setText(item.getTitle());
        holder.checkBox.setChecked(item.isChecked());
        itemView.setChecked(item.isChecked());

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.viw_item_checkbox:
                        boolean checked = ((CheckBox) view).isChecked();
                        item.setChecked(checked);
                        new SetCheckedTask().execute(item);
                        itemView.setChecked(checked);
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

        return tmpView;
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
