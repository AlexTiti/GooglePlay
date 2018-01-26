package com.example.administrator.googleplay;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrawableActivity extends AppCompatActivity implements ListView.OnItemClickListener {


    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.toolbar_drawer)
    Toolbar toolbarDrawer;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.floatButton)
    FloatingActionButton floatButton;


    private ActionBarDrawerToggle toggle;

    private List<String> mStringList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();


        // Specify that we will be displaying tabs in the action bar.


        actionBar.setDisplayHomeAsUpEnabled(true);

        mStringList.add("AAAA");
        mStringList.add("BBBB");
        mStringList.add("CCC");
        mStringList.add("DDDD");
        mStringList.add("EEEE");
        mStringList.add("FFFF");
        listView.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_item, mStringList));
        listView.setOnItemClickListener(this);
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"FloatingActionButton",Snackbar.LENGTH_SHORT).show();
            }
        });
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbarDrawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

        };


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Fragment1 fragment1 = Fragment1.getInstance(mStringList.get(i));
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.content, fragment1).commit();
        listView.setItemChecked(i, true);
        setTitle(mStringList.get(i));
        drawerLayout.closeDrawer(listView);

    }

    public void setTitle(String titleId) {
        getSupportActionBar().setTitle(titleId);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
