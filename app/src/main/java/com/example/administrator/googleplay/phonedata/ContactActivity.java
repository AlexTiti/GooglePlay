package com.example.administrator.googleplay.phonedata;

import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.example.administrator.googleplay.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Administrator
 */
public class ContactActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, AdapterView.OnItemClickListener {

    @BindView(R.id.listview)
    public ListView listViewContact;
    private SimpleCursorAdapter simpleCursorAdapter;
    private final static String[] FROM_COLUMNS = {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ? ContactsContract.Contacts.DISPLAY_NAME_PRIMARY : ContactsContract.Contacts.DISPLAY_NAME
    };
    private final static int[] TO_IDS = { android.R.id.text1};
    private final static String[] PROJECTION = {
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.LOOKUP_KEY,
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
                    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY :
                    ContactsContract.Contacts.DISPLAY_NAME
    };
    private final static String SELECTION = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ? ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
            + " LIKE ?" : ContactsContract.Contacts.DISPLAY_NAME + " LIKE ?";
    private long mContactId;
    private String mContactKey;
    private Uri mContactUri;
    private static final int CONTACT_ID_INDEX = 0;
    private static final int LOOKUP_KEY_INDEX = 1;

    private String mSearchString;
    private String[] mSelectionArgs = { mSearchString };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ButterKnife.bind(this);

        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.item, null, FROM_COLUMNS, TO_IDS, 0);

        listViewContact.setAdapter(simpleCursorAdapter);
        listViewContact.setOnItemClickListener(this);
       getSupportLoaderManager().initLoader(0,null,this);


    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        mSelectionArgs[0] = "%" + mSearchString + "%";
        return new CursorLoader(
                this,
                ContactsContract.Contacts.CONTENT_URI,
                PROJECTION,
                SELECTION,
                mSelectionArgs,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        simpleCursorAdapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        simpleCursorAdapter.swapCursor(null);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Cursor cursor = ((SimpleCursorAdapter) adapterView.getAdapter()).getCursor();
        cursor.moveToPosition(i);
        mContactId = cursor.getLong(CONTACT_ID_INDEX);
        mContactKey = cursor.getString(LOOKUP_KEY_INDEX);

        mContactUri = ContactsContract.Contacts.getLookupUri(mContactId,mContactKey);

    }
}
