package com.example.freydis.drinklink.view;

import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freydis.drinklink.R;
import com.example.freydis.drinklink.control.DownloadImage;
import com.facebook.login.LoginManager;


public class MainActivity extends AppCompatActivity {

    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private TextView userName;
    private ImageView profilePicture;
    private Toolbar toolbar;
    private boolean doubleBackClick = false;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // find drawer view
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();

        // tie DrawerLayout events to ActionBarToggle
        drawer.setDrawerListener(drawerToggle);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        setupDrawerContent(navigationView);
        setNavigationHeader();
        setFragment(DrinksFragment.class);
    }

    public void setFragment(Class fragmentClass) {
        Fragment f = null;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        try {
            f = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (f != null && fragmentClass != null) {
            ft.replace(R.id.flContent, f);
            ft.addToBackStack(null);
            ft.commit();
            drawer.closeDrawers();
        }
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navdrawer_open, R.string.navdrawer_close);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            case R.id.nav_profile:
                setFragment(ProfileFragment.class);
                break;
            case R.id.nav_drinks:
                setFragment(DrinksFragment.class);
                break;
            case R.id.nav_groups:
                setFragment(FriendsFragment.class);
                break;
            case R.id.nav_tools:
                setFragment(SettingsFragment.class);
                break;
            case R.id.nav_logout:
                logout();
                break;
            default:
                setFragment(DrinksFragment.class);
                break;
        }
    }

    // inflate navigation header
    public void setNavigationHeader() {
        // inflate navigation header and add to navigation drawer
        // (need to inflate this to find the userName and profilePicture views)
        View header = LayoutInflater.from(this).inflate(R.layout.navigation_header, null);
        navigationView.addHeaderView(header);

        userName = (TextView) header.findViewById(R.id.user_name);
        profilePicture = (ImageView) header.findViewById(R.id.profile_pic);

        // stuff sent from login activity
        Bundle loginExtras = getIntent().getExtras();

        String user_id = loginExtras.get("user_id").toString();
        String firstname = loginExtras.get("firstname").toString();
        String lastname = loginExtras.get("lastname").toString();
        String imageUrl = loginExtras.get("imageUrl").toString();

        userName.setText("" + firstname + " " + lastname);
        new DownloadImage(profilePicture).execute(imageUrl);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // sync toggle state after onRestoreInstanceState has occurred
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // pass configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // method changed to allow ActionBarToggle to handle the events
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        else {
            if (doubleBackClick) {
                super.onBackPressed();
                finish();
            }
            this.doubleBackClick = true;
            Toast.makeText(this, "Click back again to exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackClick = false;
                }
            }, 2000);
        }
    }

    // Facebook logout
    public void logout(){
        LoginManager.getInstance().logOut();
        Intent login = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(login);
        finish();
    }


    /*@Override
    protected void onSaveInstanceState(Bundle) {

    }

    @Override
    protected void onPause() {

    }

    @Override
    protected void onStop() {

    }

    @Override
    protected void onDestroy() {

    }*/
}

    /*
        // ViewPager: Layout manager that allows the user to flip through pages of data.
    //            Must be associated with an instance of a PagerAdapter.
    private ViewPager vPager;

    // DrinksPagerAdapter: A subclass that implements FragmentPagerAdapter, where each page is represented as a Fragment.
    //                -  FragmentPagerAdapter implements PagerAdapter, which is an adapter that populates pages
    //                   inside of a ViewPager.
    //                -  Determines how many pages exist and which fragment to display for each page of the adapter.
    private DrinksPagerAdapter vPagerAdapter;

    // TabHost: Container for tabbed window view.
    //          -    Set of tab labels (clickable)
    //          -    FrameLayout object that displays the contents of the selected page
    private TabHost tabHost;

    private void initializeVPager() {

        // fragments = list of pages
        List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(new ProfileFragment());
        fragments.add(new DrinksFragment());
        fragments.add(new FriendsFragment());

        // DrinksPagerAdapter object used to populate pages inside of ViewPager
        vPagerAdapter = new DrinksPagerAdapter(getSupportFragmentManager(), fragments);

        vPager = (ViewPager) findViewById(R.id.viewpager);
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
        });

        vPager.setCurrentItem(1);
        //onRestart();
    }*/