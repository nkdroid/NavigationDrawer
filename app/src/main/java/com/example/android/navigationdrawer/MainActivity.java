package com.example.android.navigationdrawer;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ListView leftDrawerList;
    private ArrayAdapter<String> navigationDrawerAdapter;
    private String[] leftSliderData = {"Home",
            "Profile", "About Us", "Contact Us", "Settings", "Logout"};

    private int[] imagelist = {R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();
        initDrawer();
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("HOME");
            setSupportActionBar(toolbar);
        }
    }

    private void initDrawer() {
        leftDrawerList = (ListView) findViewById(R.id.left_drawer);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        leftDrawerList.setAdapter(new DrawerAdapter(MainActivity.this,leftSliderData));
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

        };
        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    public class DrawerAdapter extends BaseAdapter {

        Context context;
        String[] arraylist;

        public DrawerAdapter(Context context, String[] drawerTitleListValue) {
            this.context = context;
            arraylist = drawerTitleListValue;
        }

        public int getCount() {
            return arraylist.length;
        }

        public Object getItem(int position) {
            return arraylist[position];
        }

        public long getItemId(int position) {
            return position;
        }

        public class ViewHolder {
            TextView title;
            ImageView imgIcon;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_drawer, parent,false);
                holder = new ViewHolder();
                holder.title=(TextView)convertView.findViewById(R.id.txtTitle);
                holder.imgIcon=(ImageView)convertView.findViewById(R.id.imgIcon);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.title.setText(arraylist[position]);
            holder.imgIcon.setImageResource(imagelist[position]);
            return convertView;
        }
    }
}
