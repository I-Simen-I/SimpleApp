package com.simensohol.simpleapp.gui;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.simensohol.simpleapp.R;
import com.simensohol.simpleapp.gui.navigation.NavigationDrawerFragment;
import com.simensohol.simpleapp.gui.pages.FirebaseSavePage;
import com.simensohol.simpleapp.gui.pages.FirstPage;
import com.simensohol.simpleapp.gui.pages.SecondPage;


public class MainActivity extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private static final int FIRST_PAGE = 1;
    private static final int SECOND_PAGE = 2;
    private static final int THIRD_PAGE = 3;
    private NavigationDrawerFragment navigationDrawer;
    private CharSequence pageTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationDrawer = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        pageTitle = getTitle();

        navigationDrawer.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    /**
     * Update the main content by replacing fragments
     *
     * @param position the selected page in the menulist
     */
    @Override
    public void onNavigationDrawerItemSelected(int position) {
        FragmentManager fragmentManager = getFragmentManager();
        switch (position) {
            case FIRST_PAGE - 1:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, FirstPage.newInstance(FIRST_PAGE))
                        .commit();
                break;
            case SECOND_PAGE - 1:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, SecondPage.newInstance(SECOND_PAGE))
                        .commit();
                break;
            case THIRD_PAGE - 1:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, FirebaseSavePage.newInstance(THIRD_PAGE))
                        .commit();
                break;
        }
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case FIRST_PAGE:
                pageTitle = getString(R.string.title_section1);
                break;
            case SECOND_PAGE:
                pageTitle = getString(R.string.title_section2);
                break;
            case THIRD_PAGE:
                pageTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();

        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(pageTitle);
        }
    }


    /**
     * Only show items in the action bar relevant to this screen
     * if the drawer is not showing. Otherwise, let the drawer
     * decide what to show in the action bar.
     *
     * @param menu the menu
     * @return menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!navigationDrawer.isDrawerOpen()) {
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Handle action bar item clicks here. The action bar will
     * automatically handle clicks on the Home/Up button, so long
     * as you specify a parent activity in AndroidManifest.xml.
     *
     * @param item the selected menuItem
     * @return the selected item
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Toast.makeText(this, getString(R.string.txtSettings), Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
