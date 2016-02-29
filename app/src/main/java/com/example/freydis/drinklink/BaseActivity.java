package com.example.freydis.drinklink;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Freydis on 2/25/2016.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private LinearLayout listContainer;
    private ViewGroup drawerItemsListContainer;

    // list of items that were added to the navigation drawer
    private ArrayList<Integer> navDrawerItems = new ArrayList<Integer>();

    // view that corresponds to each navigation drawer item
    private View[] navDrawerItemViews = null;

    // primary toolbar and drawer toggle
    private Toolbar actionBarToolbar;


    protected static final int NAVDRAWER_ITEM_PROFILE = 0;
    protected static final int NAVDRAWER_ITEM_DRINKS = 1;
    protected static final int NAVDRAWER_ITEM_GROUPS = 2;
    protected static final int NAVDRAWER_ITEM_SETTINGS = 3;
    protected static final int NAVDRAWER_ITEM_LOGOUT = 4;
    protected static final int NAVDRAWER_ITEM_INVALID = -1;

    // titles for navigation drawer items
    private static final int[] NAVDRAWER_TITLE_RES_ID = new int[] {
            R.string.navdrawer_item_profile,
            R.string.navdrawer_item_drinks,
            R.string.navdrawer_item_groups,
            R.string.navdrawer_item_settings,
            R.string.navdrawer_item_logout
    };

    // icons for navigation drawer items
    private static final int[] NAVDRAWER_ICON_RES_ID = new int[] {
            R.drawable.ic_profile,
            R.drawable.ic_drinks,
            R.drawable.ic_group,
            R.drawable.ic_settings,
            R.drawable.ic_logout
    };







    /*private NavigationView navigationView;
    private TextView userName;
    private ImageView profilePicture;
    private Toolbar toolbar;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }


    /**
     * Returns the navigation drawer item that corresponds to this Activity. Subclasses
     * of BaseActivity override this to indicate what nav drawer item corresponds to them
     * Return NAVDRAWER_ITEM_INVALID to mean that this Activity should not have a Nav Drawer.
     */
    protected int getSelfNavDrawerItem() {
        return NAVDRAWER_ITEM_INVALID;
    }

    private void setupNavDrawer() {
        int selfItem = getSelfNavDrawerItem();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navDrawer = (NavigationView) findViewById(R.id.nav_view);

        if (drawerLayout == null) {
            return;
        }

        if (selfItem == NAVDRAWER_ITEM_INVALID) {
            // do not show a nav drawer
            if (navDrawer != null) {
                ((ViewGroup) navDrawer.getParent()).removeView(navDrawer);
            }
            drawerLayout = null;
            return;
        }

        if (actionBarToolbar != null) {
            actionBarToolbar.setNavigationIcon(R.drawable.ic_drinks);
            actionBarToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            });
        }

        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerClosed(View drawerView) {

            }
            @Override
            public void onDrawerOpened(View drawerView) {

            }
            @Override
            public void onDrawerStateChanged(int newState) {

            }
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }
        });

        populateNavDrawer();
    }

    private void populateNavDrawer() {
        navDrawerItems.clear();
        navDrawerItems.add(NAVDRAWER_ITEM_PROFILE);
        navDrawerItems.add(NAVDRAWER_ITEM_DRINKS);
        navDrawerItems.add(NAVDRAWER_ITEM_GROUPS);
        navDrawerItems.add(NAVDRAWER_ITEM_SETTINGS);
        navDrawerItems.add(NAVDRAWER_ITEM_LOGOUT);

        createNavDrawerItems();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void createNavDrawerItems() {
        drawerItemsListContainer = (ViewGroup) findViewById(R.id.flContent);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        /*switch (id) {
        }*/
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}



