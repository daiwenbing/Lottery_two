package com.dwb.lottery_two.recycler_listview_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dwb.lottery_two.R;
import com.dwb.lottery_two.javabean.HonePageListModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dwb on 2018/3/27.
 */

public class HomeAdapter extends BaseAdapter {

    private List<HonePageListModel.showapi_res_body.result> list;
    private Context context;

    public HomeAdapter(List<HonePageListModel.showapi_res_body.result> list, Context context) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return list != null ? list.get(i) : 0;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
         ViewHolder viewholder=null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.home_item, null);
            viewholder = new ViewHolder(view);
            view.setTag(viewholder);
        } else {
            viewholder = (ViewHolder) view.getTag();
        }
        String title = list.get(i).getDescr();
        String name = list.get(i).getIssuer();
        String address = list.get(i).getArea();
        String content = list.get(i).getNotes();
        String img_code = list.get(i).getCode();
        viewholder.title.setText(title);
        viewholder.name.setText("分类:" + name);
        viewholder.content.setText(content);
        if ("".equals(address)) {
            viewholder.address.setText("地区:全国");
        } else {
            viewholder.address.setText("地区:" + address);
        }
        if ("dlt".equals(img_code)) {
            viewholder.imageview.setImageDrawable(context.getResources().getDrawable(R.mipmap.lottery_daletou));
        } else if ("fc3d".equals(img_code)) {
            viewholder.imageview.setImageDrawable(context.getResources().getDrawable(R.mipmap.lottery_fucai3d));
        } else if ("pl3".equals(img_code)) {
            viewholder.imageview.setImageDrawable(context.getResources().getDrawable(R.mipmap.lottery_pailie3));
        } else if ("pl5".equals(img_code)) {
            viewholder.imageview.setImageDrawable(context.getResources().getDrawable(R.mipmap.lottery_pailie5));
        } else if ("qlc".equals(img_code)) {
            viewholder.imageview.setImageDrawable(context.getResources().getDrawable(R.mipmap.lottery_qile));
        } else if ("qxc".equals(img_code)) {
            viewholder.imageview.setImageDrawable(context.getResources().getDrawable(R.mipmap.lottery_qixing));
        } else if ("ssq".equals(img_code)) {
            viewholder.imageview.setImageDrawable(context.getResources().getDrawable(R.mipmap.lotteryicon10032));
        } else if ("zcbqc".equals(img_code)) {
            viewholder.imageview.setImageDrawable(context.getResources().getDrawable(R.mipmap.lotteryicon10059));
        } else if ("zcjqc".equals(img_code)) {
            viewholder.imageview.setImageDrawable(context.getResources().getDrawable(R.mipmap.lotteryicon10060));
        } else if ("zcsfc".equals(img_code)) {
            viewholder.imageview.setImageDrawable(context.getResources().getDrawable(R.mipmap.lottery_fucai3d));
        } else if (img_code.contains("x5")) {
            viewholder.imageview.setImageDrawable(context.getResources().getDrawable(R.mipmap.lottery_gd115));
        } else if (img_code.contains("k3")) {
            viewholder.imageview.setImageDrawable(context.getResources().getDrawable(R.mipmap.lotteryicon10074));
        } else if (img_code.contains("ahk3")) {
            viewholder.imageview.setImageDrawable(context.getResources().getDrawable(R.mipmap.lotteryicon10074));
        } else if (img_code.contains("klsf")) {
            viewholder.imageview.setImageDrawable(context.getResources().getDrawable(R.mipmap.lotteryicon10066));
        } else if (img_code.contains("ssc")) {
            viewholder.imageview.setImageDrawable(context.getResources().getDrawable(R.mipmap.lottery_shishicai));
        } else if ("ssl".contains(img_code)) {
            viewholder.imageview.setImageDrawable(context.getResources().getDrawable(R.mipmap.lotteryicon10038));
        } else {
            viewholder.imageview.setImageDrawable(context.getResources().getDrawable(R.mipmap.lotteryicon10071));
        }

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.imageview)
        ImageView imageview;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.address)
        TextView address;
        @BindView(R.id.content)
        TextView content;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
