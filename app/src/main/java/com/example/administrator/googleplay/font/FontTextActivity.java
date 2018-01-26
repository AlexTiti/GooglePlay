package com.example.administrator.googleplay.font;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.googleplay.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FontTextActivity extends AppCompatActivity {


    @BindView(R.id.textView3)
    TextView textView;

    boolean aBoolean ;
    @BindView(R.id.imageView3)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_font_text);
        ButterKnife.bind(this);

        ArrayList<String> strings = new ArrayList<>();
        for (String s : strings){
            Log.e("sssss",s);
        }


        final AnimatorSet set = new AnimatorSet();
        Animator animator = ObjectAnimator.ofFloat(imageView,"scaleX",0.5f,1);
        Animator animator1 = ObjectAnimator.ofFloat(imageView,"translationX",0,400);
        set.playTogether(animator,animator1);
        set.setDuration(2000);
        set.start();
        imageView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (aBoolean) {
                    set.reverse();
                }else {
                    set.setCurrentPlayTime(1000);
                }
                aBoolean = !aBoolean;
            }
        });

        TextViewCompat.setAutoSizeTextTypeWithDefaults(textView, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        //TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(textView,20,100,5, TypedValue.COMPLEX_UNIT_SP);
        int text_size[] = getResources().getIntArray(R.array.text_size_sp);
        TextViewCompat.setAutoSizeTextTypeUniformWithPresetSizes(textView, text_size, TypedValue.COMPLEX_UNIT_SP);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ViewGroup.LayoutParams params = textView.getLayoutParams();
                params.width -= 50;
                params.height -= 50;
                textView.setLayoutParams(params);

            }
        });
    }


}
