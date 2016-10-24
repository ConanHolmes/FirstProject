package com.ningjiahao.firstproject.IntroduceFragment;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by 甯宁寧 on 2016-10-03.
 */
public class Myadapter extends PagerAdapter {
    private List<ImageView> lst;
    public Myadapter(List<ImageView> lst){
        this.lst=lst;
    }
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(lst.get(position % lst.size()));
        return lst.get(position  % lst.size());
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(lst.get(position  % lst.size()));
    }
}
