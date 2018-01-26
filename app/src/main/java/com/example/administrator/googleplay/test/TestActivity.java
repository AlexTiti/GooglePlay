package com.example.administrator.googleplay.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.googleplay.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Administrator
 */
public class TestActivity extends AppCompatActivity {

    @BindView(R.id.et_input_login)
    EditText et_input_login;
    @BindView(R.id.et_psd_login)
    EditText et_psd_login;
    @BindView(R.id.btn_login)
    Button button;
    @BindView(R.id.tv_show_login)
    TextView tv_show_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn_login})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_login:

                String inputString = et_input_login.getText().toString().trim();
                String psdString = et_psd_login.getText().toString().trim();
               if (!checkText(inputString,psdString)){
                   tv_show_login.setText("输入有误！");
               }else {
                   StringBuilder builder = new StringBuilder(inputString);
                   builder.append("密码:");
                   builder.append(psdString);
                   tv_show_login.setText(builder.toString());
               }
                break;
            default:
                break;
        }
    }

    private boolean checkText(String input,String psd){
        if (TextUtils.isEmpty(input) || TextUtils.isEmpty(psd)) {
            return false;
        }
        if (input.equals("18238818283") && psd.equals("123456")){
            return true;
        }
        return false;
    }
}
