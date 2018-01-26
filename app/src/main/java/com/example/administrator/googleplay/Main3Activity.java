package com.example.administrator.googleplay;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class Main3Activity extends AppCompatActivity {

    private ConstraintLayout constraintLayout;
    private TouchPullView mTouchPullView;
    private int maxheight = 800;
    private float startheight, moveHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        constraintLayout = findViewById(R.id.consLayout);
        mTouchPullView = findViewById(R.id.pullView);

        constraintLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startheight = motionEvent.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        moveHeight = motionEvent.getY();
                        float dis = moveHeight - startheight;
                        if (dis >= 0) {
                            float progress = dis >= maxheight ? 1 : (dis / maxheight);
                            mTouchPullView.setProgress(progress);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        mTouchPullView.autoBack();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }
}
