package com.ostfeld.shopper.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ostfeld.shopper.app.R;
import com.ostfeld.shopper.app.model.Item;

import java.util.List;

/**
 * Created by thomas on 24.04.14.
 */
public class ShoppingListAdapter extends ArrayAdapter<Item> {

    private Context context;
    private List<Item> items;
    private int resourceId;

    public ShoppingListAdapter(Context context, int resource, List<Item> objects) {
        super(context, resource, objects);
        this.context = context;
        this.items = objects;
        this.resourceId = resource;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, resourceId, null);
            ViewHolder holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.txt_item_title);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.viw_item_checkbox);
            convertView.setTag(holder);
        }

        Item item = items.get(position);
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.title.setText(item.getTitle());
        holder.checkBox.setChecked(item.isChecked());

        return convertView;
    }

    static class ViewHolder {
        TextView title;
        CheckBox checkBox;
    }
}
