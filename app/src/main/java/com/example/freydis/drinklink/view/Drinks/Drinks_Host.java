package com.example.freydis.drinklink.view.Drinks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

import com.example.freydis.drinklink.R;
import com.example.freydis.drinklink.control.DrinksPagerAdapter;

import java.util.List;
import java.util.Vector;

/**
 * Created by Freydis on 2/1/2016.
 *
 * ViewPager: Layout manager that allows the user to flip through pages of data.
 *            Must be associated with an instance of a PagerAdapter.
 *
 * DrinksPagerAdapter: A subclass that implements FragmentPagerAdapter, where each page is represented as a Fragment.
 *                     -  FragmentPagerAdapter implements PagerAdapter, which is an adapter that populates pages
 *                        inside of a ViewPager.
 *                     -  Determines how many pages exist and which fragment to display for each page of the adapter.
 *
 * TabHost: Container for tabbed window view.
 *          -    Set of tab labels (clickable)
 *          -    FrameLayout object that displays the contents of the selected page
 */


public class Drinks_Host extends Fragment {

    private ViewPager vPager;
    private DrinksPagerAdapter vPagerAdapter;
    private TabHost tabHost;

    private void initializeVPager() {

        // fragments = fragment_profile of pages
        List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(new Drinks_List());
        //fragments.add(new AssignActivity());

        // DrinksPagerAdapter object used to populate pages inside of ViewPager

        vPagerAdapter = new DrinksPagerAdapter(getFragmentManager(), fragments);

        /*vPager = (ViewPager) findViewById(R.id.viewpager);
        vPager.setAdapter(vPagerAdapter);
        vPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });*/


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drinks, container, false);
        return view;
    }
}





