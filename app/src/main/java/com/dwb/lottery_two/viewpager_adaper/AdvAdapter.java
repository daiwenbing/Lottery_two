package com.dwb.lottery_two.viewpager_adaper;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

/**首页滚动广告
 * Created by dwb on 2016/11/25.
 */

public class AdvAdapter extends PagerAdapter {


    private List<View> views = null;

    public AdvAdapter(List<View> views) {
        this.views = views;

    }

    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView(views.get(arg1));
    }

    @Override
    public void finishUpdate(View arg0) {

    }

    @Override
    public int getCount() {
//        return Integer.MAX_VALUE;
        return views.size();
    }

    @Override
    public Object instantiateItem(View arg0, final int arg1) {

        try {
            ((ViewPager) arg0).addView(views.get(arg1), 0);
//            ((ViewPager) arg0).addView((View)views.get(arg1%views.size()),0);
        }catch (Exception e){

        }

        return views.get(arg1);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {

    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void startUpdate(View arg0) {

    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}
