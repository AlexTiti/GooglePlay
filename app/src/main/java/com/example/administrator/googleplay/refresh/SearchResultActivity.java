package com.example.administrator.googleplay.refresh;

import android.app.ListActivity;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.administrator.googleplay.R;
import com.example.administrator.googleplay.refresh.database.DataBaseHelper;

import java.util.ArrayList;

/**
 * @author Administrator
 */
public class SearchResultActivity extends AppCompatActivity {

    private ListView listView ;
    private ArrayList<String> list = new ArrayList<>();
    private DataBaseHelper dataBaseHelper;
    private ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        dataBaseHelper = new DataBaseHelper(this);
        listView = findViewById(R.id.listview);
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(arrayAdapter);
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        Log.e("handleIntent","Intent");
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
           Cursor cursor = dataBaseHelper.getWordMatches(query,null);
               Log.e("cursor", cursor.getString(0) + "==" + cursor.getString(1));
             list.add(cursor.getString(0));
             list.add(cursor.getString(1));
             arrayAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.seasrch, menu);
        SearchManager manager = (SearchManager) getSystemService(SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setIconifiedByDefault(true);
        SearchableInfo searchableInfo =
                manager.getSearchableInfo(
                        new ComponentName(getApplicationContext(), SearchResultActivity.class));
        searchView.setSearchableInfo(searchableInfo);
//        searchView.setSearchableInfo(
//                manager.getSearchableInfo(getComponentName()));
        return true;
    }

}
