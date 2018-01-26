package com.example.administrator.googleplay.refresh.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <pre>
 *
 *   @author   :   Alex
 *   @e_mail   :   18238818283@sina.cn
 *   @time     :   2018/01/02
 *   @desc     :
 *   @version  :   V 1.0.9
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    private Context mContext;
    private SQLiteDatabase sqLiteDatabase;

    public static final  String CREATE_TABLE = "CREATE VIRTUAL TABLE "+DataBaseTable.TABLE_NAME
            +" USING fts3 ("+DataBaseTable.COL_WORD+", "+DataBaseTable.COL_DEFINITION+")";

    public DataBaseHelper(Context context) {
        super(context, DataBaseTable.DATABASE_NAME, null, DataBaseTable.DATABASE_VERSION);
        this.mContext = context;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
        sqLiteDatabase.execSQL(CREATE_TABLE);
        loadWords();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DataBaseTable.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
    private void loadWords(){
        final ExecutorService service = Executors.newFixedThreadPool(2);
        service.execute(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<100;i++){
                    long id = addWord(String.valueOf(i),"This is "+i);
                    if (id < 0){
                        Log.e("=====","NOT ADD "+i);
                    }
                }
            }
        });
    }

    private long addWord(String word ,String definition){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseTable.COL_WORD,word);
        contentValues.put(DataBaseTable.COL_DEFINITION,definition);

        return sqLiteDatabase.insert(DataBaseTable.TABLE_NAME,null,contentValues);
    }

    public Cursor getWordMatches(String name ,String[] columns){
        String selection = DataBaseTable.COL_WORD + " MATCH ?";
        String[] selectionArgs = new String[]{name + "*"};
        return query(selection,selectionArgs,columns);
    }

    private Cursor query(String selection,String[] selectionArgs,String[] columns){
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(DataBaseTable.TABLE_NAME);
        Cursor cursor =  builder.query(getReadableDatabase(),columns,selection,selectionArgs,
                null,null,null);
        if (cursor == null){
            return null;
        }else if (!cursor.moveToFirst()){
            return null;
        }
        return cursor;

    }


}
