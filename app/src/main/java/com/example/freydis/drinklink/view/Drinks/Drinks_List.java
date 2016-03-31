package com.example.freydis.drinklink.view.Drinks;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.freydis.drinklink.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Freydis on 3/26/2016.
 */
public class Drinks_List extends ListFragment {

    private List<Drinks_ListItem> items;
    private Callbacks callbacks;

    public interface Callbacks {
        int getDrinkCount(Drinks_ListItem item);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_drinks_list, null);

        items = new ArrayList<>();
        items.add(new Drinks_ListItem(getResources().getDrawable(R.drawable.ic_drinks), getString(R.string.beer), (Button) view.findViewById(R.id.minusButton), (TextView) view.findViewById(R.id.textView), (Button) view.findViewById(R.id.plusButton)));
        items.add(new Drinks_ListItem(getResources().getDrawable(R.drawable.ic_drinks), getString(R.string.shot), (Button) view.findViewById(R.id.minusButton), (TextView) view.findViewById(R.id.textView), (Button) view.findViewById(R.id.plusButton)));
        items.add(new Drinks_ListItem(getResources().getDrawable(R.drawable.ic_drinks), getString(R.string.cocktail), (Button) view.findViewById(R.id.minusButton), (TextView) view.findViewById(R.id.textView), (Button) view.findViewById(R.id.plusButton)));

        setListAdapter(new Drinks_ListAdapter(getActivity(), items));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
