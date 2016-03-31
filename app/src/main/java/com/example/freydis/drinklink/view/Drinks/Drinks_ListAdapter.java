package com.example.freydis.drinklink.view.Drinks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.freydis.drinklink.R;

import java.util.List;

/**
 * Created by Freydis on 3/26/2016.
 */
public class Drinks_ListAdapter extends ArrayAdapter<Drinks_ListItem> {

    public interface OnPlusButtonPressed {
        public void DecreaseCount();
        public void IncreaseCount();
    }


    public Drinks_ListAdapter(Context context, List<Drinks_ListItem> items) {
        super(context, R.layout.item_drinks_list, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_drinks_list, parent, false);

            // initialize the view holder
            viewHolder = new ViewHolder();
            viewHolder.drinkIcon = (ImageView) convertView.findViewById(R.id.drinkIcon);
            viewHolder.drinkTitle = (TextView) convertView.findViewById(R.id.drinkTitle);
            viewHolder.minusButton = (Button) convertView.findViewById(R.id.minusButton);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.textView);
            viewHolder.plusButton = (Button) convertView.findViewById(R.id.plusButton);
            viewHolder.count = Integer.parseInt(viewHolder.textView.getText().toString());

            viewHolder.minusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (viewHolder.count > 0) {
                        viewHolder.count--;
                    }
                    viewHolder.textView.setText(""+viewHolder.count);
                }
            });

            viewHolder.plusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.count += 1;
                    viewHolder.textView.setText(""+viewHolder.count);
                }
            });

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // update the item view
        Drinks_ListItem item = getItem(position);
        viewHolder.drinkIcon.setImageDrawable(item.getIcon());
        viewHolder.drinkTitle.setText(item.getTitle());
        return convertView;
    }

    // prevents using findViewById() repeatedly in the getView() method of the adapter
    private static class ViewHolder {
        ImageView drinkIcon;
        TextView drinkTitle;
        Button minusButton;
        TextView textView;
        Button plusButton;
        int count;
    }
}
