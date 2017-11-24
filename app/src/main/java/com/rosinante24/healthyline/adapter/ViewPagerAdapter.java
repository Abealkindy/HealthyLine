package com.rosinante24.healthyline.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Rosinante24 on 18/10/17.
 */

public class ViewPagerAdapter extends PagerAdapter {

    private LayoutInflater inflater;
    Context context;
    int[] layouts;

    public ViewPagerAdapter(Context context, int[] layouts) {
        this.context = context;
        this.layouts = layouts;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(layouts[position], container, false);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View v = (View) object;
        container.removeView(v);

    }

    @Override
    public int getCount() {
        return layouts.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
