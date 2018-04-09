package com.dwb.lottery_two.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dwb.lottery_two.R;
import com.dwb.lottery_two.application.DSLApplication;
import com.dwb.lottery_two.javabean.HonePageDetailListModel;
import com.dwb.lottery_two.recycler_listview_adapter.HomeDetailAdapter;
import com.dwb.lottery_two.utils.DSLConnections;
import com.dwb.lottery_two.utils.DateChange;
import com.dwb.lottery_two.volley.GsonRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



/**
 * Created by dwb on 2018/3/28.
 */

public class HomePageListDetail extends AppCompatActivity implements XListView.IXListViewListener {
    private Intent intent;
    private XListView xListView;
    private ArrayList<HonePageDetailListModel.showapi_res_body.result> list;
    private HomeDetailAdapter adapter;
    private HonePageDetailListModel honePageDetailListModel=null;
    private TextView toorbar_txt_main_title;
    private LinearLayout toorbar_layout_main_back;
    private String code="",title="";
    private ProgressDialog dialog=null;
    private int count=10;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homedetail);
        initview();
        dialog_shows();
        request();
    }
public void initview(){
        intent=getIntent();
        code=intent.getStringExtra("code");
        title=intent.getStringExtra("title");
    toorbar_txt_main_title=(TextView) findViewById(R.id.toorbar_txt_main_title);
    toorbar_layout_main_back=(LinearLayout) findViewById(R.id.toorbar_layout_main_back);
    toorbar_txt_main_title.setText(title);
    list=new ArrayList<HonePageDetailListModel.showapi_res_body.result>();
    adapter=new HomeDetailAdapter(list,this);
    xListView=(XListView)findViewById(R.id.xListView);
    xListView.setAdapter(adapter);
    xListView.setPullLoadEnable(true);
    xListView.setPullRefreshEnable(true);
    xListView.setXListViewListener(this);
    xListView.setFocusable(true);
//    xListView.setOnItemClickListener(new itemclink());
    toorbar_layout_main_back.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    });

}
    /**
     * 数据请求
     */
    public void request(){
        Map<String,String> param=new HashMap<String, String>();
        param.put("showapi_appid","49035");
        param.put("showapi_sign","6f6b85bce5e347139a9fc1affb25abd1");
        param.put("code",code);
        param.put("count",count+"");
        GsonRequest<HonePageDetailListModel> request=new GsonRequest<HonePageDetailListModel>(Request.Method.POST, DSLConnections.home_detail_liat_url, HonePageDetailListModel.class,param,
                new Response.Listener<HonePageDetailListModel>() {
                    @Override
                    public void onResponse(HonePageDetailListModel model) {
                        dialog_dismess();
                        onLoad();
                        honePageDetailListModel=model;
                        return_list();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError arg0) {
                onLoad();
                dialog_dismess();
            }
        });
        request.setTag("tag");
        DSLApplication.getHttpQueue().add(request);
    }
    public void return_list(){
        try {
            if("0".equals(honePageDetailListModel.getShowapi_res_code())){
                if (honePageDetailListModel.getShowapi_res_body().getResult().size()>0){
                    list.clear();
                    list.addAll(honePageDetailListModel.getShowapi_res_body().getResult());
                    adapter.notifyDataSetChanged();
                }else {
                    count=list.size();
                    Toast.makeText(this,"没有更多",Toast.LENGTH_SHORT).show();
                }
            }
        }catch (NullPointerException e){
            count=list.size();
            Toast.makeText(this,"没有更多",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    public void onRefresh() {
        count=10;
        request();
    }

    @Override
    public void onLoadMore() {
        count+=5;
        request();
    }
    private void onLoad() {
        xListView.stopRefresh();
        xListView.stopLoadMore();
        DateChange change=new DateChange();
        xListView.setRefreshTime(change.getTime1());
    }
    public void dialog_shows() {
        if (dialog == null) {
            dialog = new ProgressDialog(this);
            dialog.setMessage("加载中...");
            dialog.setCancelable(true);
        }
        dialog.show();

    }
    public void dialog_dismess(){
        if (dialog!=null&&dialog.isShowing()){dialog.dismiss();}return;

    }
}
