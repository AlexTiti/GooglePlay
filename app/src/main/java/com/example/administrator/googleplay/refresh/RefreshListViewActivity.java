package com.example.administrator.googleplay.refresh;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.app.assist.AssistContent;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.administrator.googleplay.R;
import com.example.administrator.googleplay.ThreadPoolUtils;
import com.example.administrator.googleplay.refresh.database.DataBaseHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RefreshListViewActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {


    ListView mListView;
    private ArrayList<String> arrayList = new ArrayList<>();
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_list_view);
        ButterKnife.bind(this);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        mListView = findViewById(android.R.id.list);
        for (int i = 0; i < 15; i++) {
            arrayList.add("AAA" + i);
        }
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        mListView.setAdapter(arrayAdapter);
        swipeRefreshLayout.setOnRefreshListener(this);




    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onProvideAssistContent(AssistContent outContent) {
        super.onProvideAssistContent(outContent);

        String structuredJson = null;
        try {
            structuredJson = new JSONObject()
                    .put("@type", "MusicRecording")
                    .put("@id", "https://example.com/music/recording")
                    .put("name", "Album Title")
                    .toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (structuredJson != null) {
            outContent.setStructuredData(structuredJson);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
//        SearchManager manager = (SearchManager) getSystemService(SEARCH_SERVICE);
//        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
//        searchView.setIconifiedByDefault(true);
//        searchView.setSubmitButtonEnabled(true);
//        SearchableInfo searchableInfo =
//                manager.getSearchableInfo(
//                        new ComponentName(getApplicationContext(), SearchResultActivity.class));
//        searchView.setSearchableInfo(searchableInfo);
////        searchView.setSearchableInfo(
////                manager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.refresh:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(true);
                addList();
                break;
            case R.id.search:
                startActivity(new Intent(this, SearchResultActivity.class));
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        addList();

    }

    void addList() {
        arrayList.add(" This Refresh ");
        arrayAdapter.notifyDataSetChanged();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 1500);

    }

}
