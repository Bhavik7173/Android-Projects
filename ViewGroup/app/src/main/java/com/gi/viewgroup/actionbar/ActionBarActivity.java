package com.gi.viewgroup.actionbar;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.gi.viewgroup.R;
import com.gi.viewgroup.actionbar.fragment.FirstFragment;
import com.gi.viewgroup.actionbar.fragment.FourthFragment;
import com.gi.viewgroup.actionbar.fragment.SecondFragment;
import com.gi.viewgroup.actionbar.fragment.ThirdFragment;

public class ActionBarActivity extends Activity {

    public static Context context;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar);

        context = getApplicationContext();
        try {
            android.app.ActionBar actionBar = getActionBar();
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

            //Action BAr Creation
            Tab tab1 = actionBar.newTab().setText("Tab 1");
            Tab tab2 = actionBar.newTab().setText("Tab 2");
            Tab tab3 = actionBar.newTab().setText("Tab 3");
            Tab tab4 = actionBar.newTab().setText("Tab 4");

            Fragment fragment1 = new FirstFragment();
            Fragment fragment2 = new SecondFragment();
            Fragment fragment3 = new ThirdFragment();
            Fragment fragment4 = new FourthFragment();

            tab1.setTabListener(new MyTabsListener(fragment1));
            tab2.setTabListener(new MyTabsListener(fragment2));
            tab3.setTabListener(new MyTabsListener(fragment3));
            tab4.setTabListener(new MyTabsListener(fragment4));

            actionBar.addTab(tab1);
            actionBar.addTab(tab2);
            actionBar.addTab(tab3);
            actionBar.addTab(tab4);

        } catch (Exception e) {
            Toast.makeText(this, e + " ", Toast.LENGTH_SHORT).show();
        }
    }
}