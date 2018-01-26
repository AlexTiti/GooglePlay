package com.example.administrator.googleplay.notification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.googleplay.R;

public class ParentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
