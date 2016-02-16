package com.example.freydis.drinklink;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.List;
import java.util.Vector;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private CallbackManager callbackManager;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private TextView userName;
    private ImageView profilePicture;
    private TextView profileUrl;

    // ViewPager: Layout manager that allows the user to flip through pages of data.
    //            Must be associated with an instance of a PagerAdapter.
    private ViewPager vPager;

    // VPagerAdapter: A subclass that implements FragmentPagerAdapter, where each page is represented as a Fragment.
    //                -  FragmentPagerAdapter implements PagerAdapter, which is an adapter that populates pages
    //                   inside of a ViewPager.
    //                -  Determines how many pages exist and which fragment to display for each page of the adapter.
    private VPagerAdapter vPagerAdapter;

    // TabHost: Container for tabbed window view.
    //          -    Set of tab labels (clickable)
    //          -    FrameLayout object that displays the contents of the selected page
    private TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this,   // host activity
                drawer, // DrawerLayout object - layout that host activity is linked to
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        ) /*{

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        }*/
        ;

        drawer.setDrawerListener(toggle);
        toggle.syncState();

        setNavigationHeader();




        //FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //this.initializeTabHost(savedInstanceState);
        //this.initializeVPager();


    }


    // inflate navigation header
    
    public void setNavigationHeader() {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // inflate navigation header view
        View header = LayoutInflater.from(this).inflate(R.layout.navigation_header, null);
        navigationView.addHeaderView(header);

        userName = (TextView) header.findViewById(R.id.user_name);
        profilePicture = (ImageView) header.findViewById(R.id.profile_pic);

        Bundle extras = getIntent().getExtras();
        String name = extras.get("name").toString();
        String surname = extras.get("surname").toString();
        String imageUrl = extras.get("imageUrl").toString();

        userName.setText("" + name + " " + surname);
        new DownloadImage(profilePicture).execute(imageUrl);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_drinks) {

        } else if (id == R.id.nav_groups) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_logout) {
            logout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    /*private void initializeVPager() {

        // fragments = list of pages
        List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(new LSidePanelFragment());
        fragments.add(new MainPanelFragment());
        fragments.add(new RSidePanelFragment());

        // VPagerAdapter object used to populate pages inside of ViewPager
        vPagerAdapter = new VPagerAdapter(getSupportFragmentManager(), fragments);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Facebook logout
    public void logout(){
        LoginManager.getInstance().logOut();
        Intent login = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(login);
        finish();
    }
}
