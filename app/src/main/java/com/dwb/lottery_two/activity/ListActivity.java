package com.dwb.lottery_two.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dwb.lottery_two.R;
import com.dwb.lottery_two.application.DSLApplication;
import com.dwb.lottery_two.javabean.HonePageListModel;
import com.dwb.lottery_two.recycler_listview_adapter.HomeAdapter;
import com.dwb.lottery_two.utils.DSLConnections;
import com.dwb.lottery_two.utils.DateChange;
import com.dwb.lottery_two.volley.GsonRequest;
import com.dwb.lottery_two.volley.VolleyErrorHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 彩票列表
 * Created by dwb on 2018/4/2.
 */

public class ListActivity extends AppCompatActivity implements XListView.IXListViewListener {
    @BindView(R.id.toorbar_layout_main_back)
    LinearLayout toorbar_layout_main_back;
    @BindView(R.id.toorbar_txt_main_title)
    TextView toorbar_txt_main_title;
    private Intent mintent;
    private HomeAdapter adapter;
    private HonePageListModel honePageListModel;
    private ArrayList<HonePageListModel.showapi_res_body.result> list;
    private ProgressDialog dialog = null;
    @BindView(R.id.xListView)
    XListView xListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);
        //绑定初始化ButterKnife
        ButterKnife.bind(this);
        initview();
        dialog_shows();
        request();
    }

    public void initview() {
        list = new ArrayList<HonePageListModel.showapi_res_body.result>();
        adapter = new HomeAdapter(list, this);
        xListView.setAdapter(adapter);
        xListView.setPullLoadEnable(false);
        xListView.setPullRefreshEnable(true);
        xListView.setXListViewListener(this);
        xListView.setFocusable(true);
        xListView.setOnItemClickListener(new itemclink());
        toorbar_txt_main_title.setText("列表");
    }

    @OnClick(R.id.toorbar_layout_main_back)
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.toorbar_layout_main_back) {
            finish();
        }
    }

    public class itemclink implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            int a = position - 1;
            mintent = new Intent(ListActivity.this, HomePageListDetail.class);
            mintent.putExtra("code", list.get(a).getCode());
            mintent.putExtra("title", list.get(a).getDescr());
            startActivity(mintent);
        }
    }

    /**
     * 数据请求
     */
    public void request() {
        Map<String, String> param = new HashMap<String, String>();
        param.put("showapi_appid", "49035");
        param.put("showapi_sign", "6f6b85bce5e347139a9fc1affb25abd1");
        GsonRequest<HonePageListModel> request = new GsonRequest<HonePageListModel>(Request.Method.POST, DSLConnections.home_list_url, HonePageListModel.class, param,
                new Response.Listener<HonePageListModel>() {
                    @Override
                    public void onResponse(HonePageListModel model) {
                        dialog_dismess();
                        onLoad();
                        honePageListModel = model;
                        return_list();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError arg0) {
                Toast.makeText(ListActivity.this, VolleyErrorHelper.getMessage(arg0, ListActivity.this), Toast.LENGTH_SHORT).show();
                dialog_dismess();
                onLoad();
            }
        });
        request.setTag("tag");
        DSLApplication.getHttpQueue().add(request);
    }

    public void return_list() {
        try {
            if ("0".equals(honePageListModel.getShowapi_res_code())) {
                list.clear();
                list.addAll(honePageListModel.getShowapi_res_body().getResult());
                adapter.notifyDataSetChanged();
            }
        } catch (NullPointerException e) {
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DSLApplication.getHttpQueue().cancelAll("tag");
    }

    @Override
    public void onRefresh() {
        request();
    }

    @Override
    public void onLoadMore() {
        onLoad();
    }

    private void onLoad() {
        xListView.stopRefresh();
        xListView.stopLoadMore();
        DateChange change = new DateChange();
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

    public void dialog_dismess() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        return;

    }
}
