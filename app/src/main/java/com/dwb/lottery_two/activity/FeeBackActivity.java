package com.dwb.lottery_two.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dwb.lottery_two.R;
import com.dwb.lottery_two.application.DSLApplication;


/**
 * Created by dell on 2018/3/30.
 */

public class FeeBackActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout toorbar_layout_main_back;
    private TextView toorbar_txt_main_title;
    private Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DSLApplication.getInstance().addActivity(this);
        setContentView(R.layout.feedback);
        initview();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
    public void initview(){
        toorbar_layout_main_back= (LinearLayout) findViewById(R.id.toorbar_layout_main_back);
        toorbar_txt_main_title= (TextView) findViewById(R.id.toorbar_txt_main_title);
        toorbar_txt_main_title.setText("用户反馈");
        button=findViewById(R.id.submit_btn);
        toorbar_layout_main_back.setOnClickListener(this);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.toorbar_layout_main_back:
                finish();
                break;
            case R.id.submit_btn:
                Toast.makeText(FeeBackActivity.this,"提交成功",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
