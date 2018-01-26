package com.example.administrator.googleplay.snackbar;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.googleplay.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SnackBarActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.floatButton)
    FloatingActionButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_bar);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.floatButton})
    public void click(View view){
      //  Snackbar.make(view,"HEHE",Snackbar.LENGTH_SHORT).show();

        Snackbar.make(view, "Replace", Snackbar.LENGTH_LONG)
                .setAction("Action", SnackBarActivity.this).show();

    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this,"AAA",Toast.LENGTH_SHORT).show();
    }
}
