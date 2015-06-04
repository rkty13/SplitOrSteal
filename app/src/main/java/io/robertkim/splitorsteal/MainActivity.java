package io.robertkim.splitorsteal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.ParseUser;
import com.parse.ui.ParseLoginBuilder;

public class MainActivity extends ActionBarActivity {

    private String[] mNavigationLinks;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mNavigationLinks = getResources().getStringArray(R.array.drawer_array);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.leftDrawer);

        //Set adapter for listview
        mDrawerList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mNavigationLinks));

        //Set list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (ParseUser.getCurrentUser() == null) {
            ParseLoginBuilder builder = new ParseLoginBuilder(MainActivity.this);
            startActivityForResult(builder.build(), 0);
        } else {
            Log.d("ffdsa", "asdfsdfasdf");
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = fm.findFragmentById(R.id.profileFragmentContainer);

            if (fragment == null) {
                fragment = new ProfileFragment();
                fm.beginTransaction()
                        .add(R.id.profileFragmentContainer, fragment)
                        .commit();
            }
        }
    }




    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    /** Swaps fragments in the main content view */
    private void selectItem(int position) {
        // Create a new fragment and specify the planet to show based on position
        Fragment fragment = null;
        switch(position) {
            case 0:
                fragment = new ProfileFragment();
                break;
            case 1:
                fragment = new ProfileFragment();
                break;
            case 2:
                fragment = new ProfileFragment();
                break;
            default:
                fragment = new ProfileFragment();
                break;
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.profileFragmentContainer, fragment)
                .commit();

        // Highlight the selected item, update the title, and close the drawer
        mDrawerLayout.closeDrawer(mDrawerList);
    }
}


