package com.dwb.lottery_two.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dwb.lottery_two.R;
import com.dwb.lottery_two.activity.ListActivity;
import com.dwb.lottery_two.activity.MapActivity;
import com.dwb.lottery_two.activity.Map_Two_Activity;
import com.dwb.lottery_two.activity.WebviewActivity;
import com.sunfusheng.marqueeview.MarqueeView;
import com.zhengsr.viewpagerlib.anim.MzTransformer;
import com.zhengsr.viewpagerlib.bean.PageBean;
import com.zhengsr.viewpagerlib.callback.PageHelperListener;
import com.zhengsr.viewpagerlib.indicator.TransIndicator;
import com.zhengsr.viewpagerlib.indicator.ZoomIndicator;
import com.zhengsr.viewpagerlib.view.BannerViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * 首页
 * Created by dwb on 2018/3/26.
 */

public class HomepageFragment extends Fragment {
    @BindView(R.id.homepage_layout_top1)
    RelativeLayout homepageLayoutTop1;
    @BindView(R.id.homepage_layout_top2)
    RelativeLayout homepageLayoutTop2;
    @BindView(R.id.homepage_layout_top3)
    RelativeLayout homepageLayoutTop3;
    @BindView(R.id.homepage_layout_top4)
    RelativeLayout homepageLayoutTop4;
    @BindView(R.id.layout_zs)
    RelativeLayout layoutZs;
    @BindView(R.id.loop_viewpager_text)
    BannerViewPager loopViewpagerText;
    @BindView(R.id.bottom_text_layout)
    TransIndicator bottomTextLayout;
    @BindView(R.id.layout_bask_bool)
    LinearLayout layoutBaskBool;
    @BindView(R.id.layout_foot_bool)
    LinearLayout layoutFootBool;
    @BindView(R.id.layout_head_bask)
    RelativeLayout layoutHeadBask;
    @BindView(R.id.layout_head_foot)
    RelativeLayout layoutHeadFoot;
    @BindView(R.id.layout_head_god)
    RelativeLayout layoutHeadGod;
    private Unbinder unbinder;
    @BindView(R.id.loop_viewpager)
    BannerViewPager loopViewpager;
    @BindView(R.id.bottom_scale_layout)
    ZoomIndicator bottomScaleLayout;
    @BindView(R.id.layout_kaijiang)
    LinearLayout layout_kaijiang;
    @BindView(R.id.layout_gonggao)
    LinearLayout layout_gonggao;
    @BindView(R.id.layout_jingcai)
    LinearLayout layout_jingcai;
    @BindView(R.id.marqueeView)
    MarqueeView marqueeView;
    @BindView(R.id.homepage_layout_kj)
    RelativeLayout homepage_layout_kj;
    @BindView(R.id.homepage_layout_gg)
    RelativeLayout homepage_layout_gg;
    @BindView(R.id.homepage_layout_3)
    RelativeLayout homepage_layout_3;
    @BindView(R.id.homepage_layout_4)
    RelativeLayout homepage_layout_4;
    @BindView(R.id.homepage_layout_5)
    RelativeLayout homepage_layout_5;
    @BindView(R.id.homepage_layout_6)
    RelativeLayout homepage_layout_6;
    @BindView(R.id.homepage_layout_9)
    RelativeLayout homepage_layout_9;
    @BindView(R.id.homepage_layout_10)
    RelativeLayout homepage_layout_10;
    @BindView(R.id.layout_img)
    RelativeLayout layout_img;
    private Intent mintent;
    private static final String[] RESURL = {

            "http://m.dididapiao.com/upload/cms/news/images/2017/12/20/tn_up_201712201706000007.png",
            "http://www.lottery.gov.cn/upload/20180306/20180306102116902.jpg",
            "http://www.lottery.gov.cn/upload/20180207/20180207084852830.jpg",
            "http://info.fengkuangtiyu.cn/images/2018/3/28/20183281522217348886_7.jpg",
            "http://info.fengkuangtiyu.cn/images/2018/3/30/20183301522372594725_7.jpg",
            "https://r.dididapiao.com/image/bill/and-720x312-gd.jpg",};
    private static final String[] banner_url = {
            "http://m.lottech.cn/vue/views/didi/betRedPlan.html#/redPlan/1/D33269DE20A5278C6517B89FC1940850-384234?hiddenHead=true",
            "http://hgame.lottery.gov.cn/2018lianghuigy/",
            "http://hgame.lottery.gov.cn/1802zhuantigy/mobile.html",
            "http://m.159cai.com/sjbguanyajun/guanjun.html",
            "http://mapi.159cai.com/discovery/news/football/2018/0330/33094.html",
            "http://m.159cai.com/discovery/news/football/2018/0302/32651.html",};
    private static final Integer[] RESURL_two = {
            R.mipmap.info_deaulft, R.mipmap.instant_entrance_default, R.mipmap.home_foot_bg};
    private static final String[] babber_url_two = {
            "https://m.qmcai.com/hd/caipiaoclass/index.html?h5ControlTitle=true&backH5Control=true",
            "http://m.159cai.com/gong/news.html",
            "https://m.qmcai.com/hd/fbQuickBao/index.html?h5ControlTitle=true&backH5Control=true&source=client",
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepagefragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initview();
    }

    public void initview() {
        bindmequrelist();
        initviewpager();
        initviewpager_two();
        marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                mintent = new Intent(getActivity(), WebviewActivity.class);
                mintent.putExtra("title", textView.getText().toString());
                mintent.putExtra("url", "http://mapi.159cai.com/discovery/notice/2018/0320/32942.html");
                startActivity(mintent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void initviewpager() {
        //配置数据
        List<LoopBean> loopBeens = new ArrayList<>();
        for (int i = 0; i < RESURL.length; i++) {
            LoopBean bean = new LoopBean();
            bean.url = RESURL[i];
            bean.img_url = banner_url[i];
            loopBeens.add(bean);
        }
        //配置pagerbean，这里主要是为了viewpager的指示器的作用，注意记得写上泛型
        PageBean bean = new PageBean.Builder<LoopBean>()
                .setDataObjects(loopBeens)
                .setIndicator(bottomScaleLayout)
                .builder();
        // 设置viewpager的动画，这里提供了三种，分别是MzTransformer，ZoomOutPageTransformer,
        // 和DepthPageTransformer，可以体验一下
        loopViewpager.setPageTransformer(false, new MzTransformer());
        //
        loopViewpager.setPageListener(bean, R.layout.loop_layout, new PageHelperListener() {
            @Override
            public void getItemView(View view, final Object data) {
                ImageView imageView = view.findViewById(R.id.loop_icon);
                LoopBean bean = (LoopBean) data;
                Glide.with(getActivity())
                        .load(bean.url)
                        .placeholder(R.mipmap.loag_station_banner)
                        .into(imageView);
             /*   TextView textView = view.findViewById(R.id.loop_text);
                textView.setText(bean.text);*/
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mintent = new Intent(getActivity(), WebviewActivity.class);
                        mintent.putExtra("url", ((LoopBean) data).img_url);
                        mintent.putExtra("title", "详情");
                        startActivity(mintent);
                    }
                });
                //如若你要设置点击事件，也可以直接通过这个view 来设置，或者图片的更新等等
            }
        });

    }

    public void initviewpager_two() {
        //配置数据
        List<LoopBean> loopBeens = new ArrayList<>();
        for (int i = 0; i < RESURL_two.length; i++) {
            LoopBean bean = new LoopBean();
            bean.res = RESURL_two[i];
            bean.img_url = babber_url_two[i];
            loopBeens.add(bean);
        }
        PageBean arcbean = new PageBean.Builder<LoopBean>()
                .setDataObjects(loopBeens)
                .setIndicator(bottomTextLayout)
                .builder();
        //第二个轮播图
        loopViewpagerText.setPageTransformer(false, new MzTransformer());

        loopViewpagerText.setPageListener(arcbean, R.layout.image_layout, new PageHelperListener() {
            @Override
            public void getItemView(View view, final Object data) {
                ImageView imageView = view.findViewById(R.id.icon);
                LoopBean bean = (LoopBean) data;
                imageView.setImageResource(bean.res);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mintent = new Intent(getActivity(), WebviewActivity.class);
                        mintent.putExtra("url", ((LoopBean) data).img_url);
                        startActivity(mintent);
                    }
                });
            }
        });
    }

    public void bindmequrelist() {
        List<String> info = new ArrayList<>();
        info.add("关于发单【跟单异常】调整公告");
        info.add("关于【预约发单调整】公告");
        marqueeView.startWithList(info);

// 在代码里设置自己的动画
        marqueeView.startWithList(info, R.anim.anim_bottom_in, R.anim.anim_top_out);
    }

    @OnClick({R.id.layout_bask_bool, R.id.layout_foot_bool, R.id.layout_head_bask, R.id.layout_head_foot, R.id.layout_head_god,R.id.layout_zs, R.id.homepage_layout_kj, R.id.homepage_layout_gg, R.id.layout_img, R.id.layout_kaijiang, R.id.layout_gonggao, R.id.layout_jingcai, R.id.homepage_layout_top1, R.id.homepage_layout_top2, R.id.homepage_layout_top3, R.id.homepage_layout_top4,
            R.id.homepage_layout_3, R.id.homepage_layout_4, R.id.homepage_layout_5, R.id.homepage_layout_6, R.id.homepage_layout_9, R.id.homepage_layout_10})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_bask_bool:
                mintent = new Intent(getActivity(), WebviewActivity.class);
                mintent.putExtra("url", "https://m.qmcai.com/hd/lqzlk/basketLeague.html?h5ControlTitle=true&backH5Control=true&version=5.2.6");
                startActivity(mintent);
                break;
            case R.id.layout_foot_bool:
                mintent = new Intent(getActivity(), WebviewActivity.class);
                mintent.putExtra("url", "https://m.qmcai.com/hd/zqzlk/leagueList.html?version=5.2.6");
                startActivity(mintent);
                break;
            case R.id.layout_head_bask:
                mintent = new Intent(getActivity(), WebviewActivity.class);
                mintent.putExtra("url", "https://m.qmcai.com/hd/lqzlk/basketLeague.html?h5ControlTitle=true&backH5Control=true&version=5.2.6");
                startActivity(mintent);
                break;
            case R.id.layout_head_foot:
                mintent = new Intent(getActivity(), WebviewActivity.class);
                mintent.putExtra("url", "https://m.qmcai.com/hd/zqzlk/leagueList.html?version=5.2.6");
                startActivity(mintent);
                break;
            case R.id.layout_head_god:
                mintent = new Intent(getActivity(), WebviewActivity.class);
                mintent.putExtra("title", "大神秘籍");
                mintent.putExtra("url", "https://m.qmcai.com/hd/caipiaoclass/index.html?h5ControlTitle=true&backH5Control=true");
                startActivity(mintent);
                break;
            case R.id.homepage_layout_top1:
                mintent = new Intent(getActivity(), ListActivity.class);
                startActivity(mintent);
                break;
            case R.id.homepage_layout_top2:
                mintent = new Intent(getActivity(), WebviewActivity.class);
                mintent.putExtra("title", "比分直播");
                mintent.putExtra("url", "http://live.m.500.com/home/zq/jczq/cur?render=local");
                startActivity(mintent);
                break;
            case R.id.homepage_layout_top3:
                mintent = new Intent(getActivity(), WebviewActivity.class);
                mintent.putExtra("title", "公告信息");
                mintent.putExtra("url", "http://m.159cai.com/gong/index.html");
                startActivity(mintent);
                break;
            case R.id.homepage_layout_top4:
                mintent = new Intent(getActivity(), WebviewActivity.class);
                mintent.putExtra("title", "每日竞猜");
                mintent.putExtra("url", "http://m.159cai.com/gong/news.html");
                startActivity(mintent);
                break;
            case R.id.layout_zs:
                //走势
                mintent = new Intent(getActivity(), Map_Two_Activity.class);
                mintent.putExtra("title", "走势图");
                mintent.putExtra("url", "http://m.500.com/datachart/");
                startActivity(mintent);
                break;
            case R.id.homepage_layout_kj:
                mintent = new Intent(getActivity(), MapActivity.class);
                mintent.putExtra("title", "双色球走势图");
                mintent.putExtra("url", "http://m.500.com/datachart/ssq/jb.html");
                startActivity(mintent);
                break;
            case R.id.homepage_layout_gg:
                mintent = new Intent(getActivity(), MapActivity.class);
                mintent.putExtra("title", "大乐透走势图");
                mintent.putExtra("url", "http://m.500.com/datachart/ssq/jb.html");
                startActivity(mintent);
                break;
            case R.id.layout_kaijiang:

                mintent = new Intent(getActivity(), WebviewActivity.class);
                mintent.putExtra("url", "https://m.lechuangmingcai.com/yc/event/videoRecording/index.html?videonum=1&firstIn=true&userNo=&imei=");
                startActivity(mintent);
                break;
            case R.id.layout_gonggao:
                mintent = new Intent(getActivity(), WebviewActivity.class);
                mintent.putExtra("title", "彩票课堂");
                mintent.putExtra("url", "https://m.qmcai.com/hd/caipiaoclass/index.html?h5ControlTitle=true&backH5Control=true");
                startActivity(mintent);
                break;
            case R.id.layout_jingcai:
                //走势
                mintent = new Intent(getActivity(), Map_Two_Activity.class);
                mintent.putExtra("title", "走势图");
                mintent.putExtra("url", "http://m.500.com/datachart/");
                startActivity(mintent);
                break;
            case R.id.layout_img:
                //第一资讯
                mintent = new Intent(getActivity(), WebviewActivity.class);
                mintent.putExtra("title", "新闻资讯");
                mintent.putExtra("url", "http://m.159cai.com/gong/news.html");
                startActivity(mintent);
                break;
            case R.id.homepage_layout_3:
                mintent = new Intent(getActivity(), MapActivity.class);
                mintent.putExtra("title", "福彩3D走势图");
                mintent.putExtra("url", "http://m.500.com/datachart/sd/jb.html");
                startActivity(mintent);
                break;
            case R.id.homepage_layout_4:
                mintent = new Intent(getActivity(), MapActivity.class);
                mintent.putExtra("title", "11选5走势图");
                mintent.putExtra("url", "http://m.500.com/datachart/fjsyxw/jb.html");
                startActivity(mintent);
                break;
            case R.id.homepage_layout_5:
                mintent = new Intent(getActivity(), MapActivity.class);
                mintent.putExtra("title", "江西快三走势图");
                mintent.putExtra("url", "http://m.500.com/datachart/jxk3/zx/2.html");
                startActivity(mintent);
                break;
            case R.id.homepage_layout_6:
                mintent = new Intent(getActivity(), MapActivity.class);
                mintent.putExtra("title", "快三走势图");
                mintent.putExtra("url", "http://m.500.com/datachart/jsk3/zx/2.html");
                startActivity(mintent);
                break;
            case R.id.homepage_layout_9:
                mintent = new Intent(getActivity(), MapActivity.class);
                mintent.putExtra("title", "幸运赛车走势图");
                mintent.putExtra("url", "http://m.500.com/datachart/sfc/zfb/2.html");
                startActivity(mintent);
                break;
            case R.id.homepage_layout_10:
                mintent = new Intent(getActivity(), MapActivity.class);
                mintent.putExtra("title", "排列五走势图");
                mintent.putExtra("url", "http://m.500.com/datachart/plw/zx/0.html");
                startActivity(mintent);
                break;

        }
    }

}
