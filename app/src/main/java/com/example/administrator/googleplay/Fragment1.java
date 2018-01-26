package com.example.administrator.googleplay;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * <pre>
 *
 *   @author   :   Alex
 *   @e_mail   :   18238818283@sina.cn
 *   @time     :   2017/12/11
 *   @desc     :
 *   @version  :   V 1.0.9
 */

public class Fragment1 extends Fragment {
    public static final String ARS = "content";
    private String mStringContent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null){
            mStringContent = getArguments().getString(ARS);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_layout,container,false);
        TextView textView = v.findViewById(R.id.textView);
        textView.setText(mStringContent);
        return v;
    }

    public static Fragment1 getInstance(String s){
        Fragment1 fragment1 = new Fragment1();
        Bundle bundle = new Bundle();
        bundle.putString(ARS,s);
        fragment1.setArguments(bundle);
        return fragment1;

    }
}
