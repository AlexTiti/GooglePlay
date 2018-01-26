package com.example.administrator.googleplay.input;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.AlarmManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.googleplay.R;

public class InputActivity extends AppCompatActivity implements TextView.OnEditorActionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        EditText editText = findViewById(R.id.ed1);
        EditText editText2 = findViewById(R.id.ed2);
        AutoCompleteTextView textView = findViewById(R.id.text_auto);
        String s[] = getResources().getStringArray(R.array.string_array);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,s);
        textView.setAdapter(arrayAdapter);
        editText.setOnEditorActionListener(this);
        editText2.setOnEditorActionListener(this);

        AlarmManager alarmManagerCompat = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent("TEST ALARM");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);
        alarmManagerCompat.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime()+10*1000,
                10*1000,pendingIntent);

    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        Log.e("===","==="+i+"==="+ EditorInfo.IME_ACTION_NEXT);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int count = event.getPointerCount();
        int pointId = event.getPointerId(0);
        int pointIndex = event.findPointerIndex(pointId);

        float x = event.getX(pointIndex);
        float y = event.getY(pointIndex);

        return super.onTouchEvent(event);
    }
}
