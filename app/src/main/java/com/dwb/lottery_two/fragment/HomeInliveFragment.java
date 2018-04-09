package com.dwb.lottery_two.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dwb.lottery_two.R;
import com.dwb.lottery_two.viewpager_adaper.HoeViewPageAdapter;
import com.zhengsr.viewpagerlib.indicator.TabIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by dwb on 2018/3/26.
 */

public class HomeInliveFragment extends Fragment {

    @BindView(R.id.line_indicator)
    TabIndicator line_indicator;
    @BindView(R.id.home_live_viewpager)
    ViewPager home_live_viewpager;
    private List<Fragment> list;
    private List<String> mtitle;
    private HoeViewPageAdapter pageAdapter;
    private Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homelivelayout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initview();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    public void initview() {
        list=new ArrayList<Fragment>();
        list.add(new Page_Fragment_Three());
        list.add(new Page_Fragment_Two());
        list.add(new Page_Fragment_One());
        mtitle=new ArrayList<String>();
        mtitle.add("开奖公告");
        mtitle.add("大神推荐");
        mtitle.add("实时比分");
        pageAdapter=new HoeViewPageAdapter(getChildFragmentManager(),list);
        home_live_viewpager.setAdapter(pageAdapter);
        home_live_viewpager.setCurrentItem(0);
        // 设置 viewpager的切换速度，这样点击的时候比较自然
        line_indicator.setViewPagerSwitchSpeed(home_live_viewpager,600);
        line_indicator.setTabData(home_live_viewpager,mtitle, new TabIndicator.TabClickListener() {
            @Override
            public void onClick(int position) {
                //顶部点击的方法公布出来
                home_live_viewpager.setCurrentItem(position);
            }
        });

    }

}
