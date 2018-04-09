package com.dwb.lottery_two.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dwb.lottery_two.R;
import com.dwb.lottery_two.activity.FeeBackActivity;
import com.dwb.lottery_two.activity.MapActivity;
import com.dwb.lottery_two.application.DSLApplication;
import com.zhengsr.viewpagerlib.anim.MzTransformer;
import com.zhengsr.viewpagerlib.bean.PageBean;
import com.zhengsr.viewpagerlib.callback.PageHelperListener;
import com.zhengsr.viewpagerlib.indicator.TransIndicator;
import com.zhengsr.viewpagerlib.view.BannerViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 更多
 * Created by dwb on 2018/3/26.
 */

public class HomepageMoreFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.layout_ssq)
    RelativeLayout layout_ssq;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.table2)
    RelativeLayout table2;
    @BindView(R.id.table4)
    RelativeLayout table4;
    @BindView(R.id.table_check)
    RelativeLayout table_check;
    @BindView(R.id.table5)
    RelativeLayout table5;
    @BindView(R.id.loop_viewpager_text)
    BannerViewPager loopViewpager;
    @BindView(R.id.bottom_text_layout)
    TransIndicator bottomScaleLayout;
    private Unbinder unbinder;
    private String[] imageUrls;
    private List<String> banner_list;
    private List<String> list_img;

/*    private static final String[] RESURL = {
            "http://img.mukewang.com/54bf7e1f000109c506000338-590-330.jpg",
            "http://upload.techweb.com.cn/2015/0114/1421211858103.jpg",
            "http://img1.cache.netease.com/catchpic/A/A0/A0153E1AEDA115EAE7061A0C7EBB69D2.jpg",
            "http://image.tianjimedia.com/uploadImages/2015/202/27/57RF8ZHG8A4T_5020a2a4697650b89" + "c394237ba9ffbb45fe8555a2cbec-6O6nmI_fw658.jpg"};*/
private static final String[] RESURL = {
        "http://p1.qhimg.com/t01518e3392d7f91852.jpg",
        "http://p3.qhimg.com/t017801fb7a63d86fa7.png",
        "http://img.500.com/upimages/wap/img/20180403141605091.jpg",
        "http://img.500.com/upimages/wap/img/20180317210424460.jpg",
        "http://img.500.com/upimages/wap/img/20180408111959212.jpg",
        "http://p0.qhimg.com/t01292199e6762943ca.png"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_set, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        loopViewpager.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        loopViewpager.onReusme();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initview();
    }
    public void initview(){
        //配置数据
        List<LoopBean> loopBeens = new ArrayList<>();
        for (int i = 0; i < RESURL.length; i++) {
            LoopBean bean = new LoopBean();
            bean.url = RESURL[i];
            loopBeens.add(bean);
        }
        //配置pagerbean，这里主要是为了viewpager的指示器的作用，注意记得写上泛型
        PageBean bean = new PageBean.Builder<LoopBean>()
                .setDataObjects(loopBeens)
                .setIndicator(bottomScaleLayout)
                .builder();
        // 设置viewpager的动画，这里提供了三种，分别是MzTransformer，ZoomOutPageTransformer,
        // 和DepthPageTransformer，可以体验一下
        loopViewpager.setPageTransformer(false,new MzTransformer());
        //
        loopViewpager.setPageListener(bean, R.layout.image_layout, new PageHelperListener() {
            @Override
            public void getItemView(View view, Object data) {
                ImageView imageView = view.findViewById(R.id.icon);
                LoopBean bean = (LoopBean) data;
                Glide.with(getActivity())
                        .load(bean.url)
                        .placeholder(R.mipmap.loag_station_banner)
                        .into(imageView);
             /*   TextView textView = view.findViewById(R.id.loop_text);
                textView.setText(bean.text);*/

                //如若你要设置点击事件，也可以直接通过这个view 来设置，或者图片的更新等等
            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.table2:
                tv.setText("0M");
                Toast.makeText(getActivity(), "清除缓存成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.table4:
                Intent intent = new Intent(getActivity(), FeeBackActivity.class);
                startActivity(intent);
                break;
            case R.id.table5:
                DSLApplication.getInstance().onTerminate();
                break;
            case R.id.table_check:
                Toast.makeText(getActivity(), "当前已是最新版本", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    @OnClick({R.id.table2, R.id.table4, R.id.table_check, R.id.table5, R.id.layout_ssq})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.table2) {
            tv.setText("0M");
            Toast.makeText(getActivity(), "清除缓存成功", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.table4) {
            Intent intent = new Intent(getActivity(), FeeBackActivity.class);
            startActivity(intent);
        } else if (id == R.id.table_check) {
            Toast.makeText(getActivity(), "已是最新版本", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.table5) {
            DSLApplication.getInstance().onTerminate();
        } else if (id == R.id.layout_ssq) {
            Intent intent = new Intent(getActivity(), MapActivity.class);
            intent.putExtra("title", "双色球玩法介绍");
            intent.putExtra("url", "https://m.dididapiao.com/page/help/desc_ssq.html?clientType=10&agentId=100339");
            startActivity(intent);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
