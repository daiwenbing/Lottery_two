package com.dwb.lottery_two.recycler_listview_adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dwb.lottery_two.R;
import com.dwb.lottery_two.javabean.HonePageDetailListModel;

import java.util.List;


/**
 * Created by dwb on 2018/3/27.
 */

public class HomeDetailAdapter extends BaseAdapter{

    private Viewholder viewholder=null;
    private List<HonePageDetailListModel.showapi_res_body.result> list;
    private Context context;
    public HomeDetailAdapter(List<HonePageDetailListModel.showapi_res_body.result> list, Context context) {
        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list!=null?list.size():0;
    }

    @Override
    public Object getItem(int i) {
        return list!=null?list.get(i):0;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null){
            viewholder=new Viewholder();
            view= LayoutInflater.from(context).inflate(R.layout.home_deatel_item,null);
            viewholder.textview1=view.findViewById(R.id.textview1);
            viewholder.textview2=view.findViewById(R.id.textview2);
            viewholder.textview3=view.findViewById(R.id.textview3);
            viewholder.ll_qiu=view.findViewById(R.id.ll_qiu);
            view.setTag(viewholder);
        }else {
            viewholder= (Viewholder) view.getTag();
        }
        String name=list.get(i).getName();
        String time=list.get(i).getTime();
        String date=list.get(i).getExpect();
        String openCode=list.get(i).getOpenCode();
        viewholder.textview1.setText(name);
        viewholder.textview2.setText(time);
        viewholder.textview3.setText("第"+date+"期");
        if (openCode == null || "".equals(openCode)) {
            viewholder.ll_qiu.removeAllViews();
            return view;
        }
        String[] result = openCode.split(",");
        if (viewholder.ll_qiu.getChildCount() <= 0) {
            for (String text : result) {
                TextView textView = new TextView(context);
                textView.setText(text);
                textView.setGravity(Gravity.CENTER);
                textView.setBackgroundResource(R.drawable.shape_qiu);
                LinearLayout.LayoutParams vlp =new LinearLayout.LayoutParams( LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                textView.setPadding(3, 3, 3, 3);
                textView.setTextColor(context.getResources().getColor(R.color.login_button_bg));
                textView.setLayoutParams(vlp);
                viewholder.ll_qiu.addView(textView);
            }

        }
        return view;
    }
    public class Viewholder{
        private TextView textview1;
        private TextView textview2;
        private TextView textview3;
        private LinearLayout ll_qiu;
    }
}
