package com.ningjiahao.firstproject.IntroduceFragment;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by 甯宁寧 on 2016-10-03.
 */
public class MyViewPager extends ViewPager{


    public MyViewPager(Context context) {
        this(context,null);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        // 当触碰ViewPager的时候，该方法触发

        // 通知其父控件，现在进行的是本控件的操作，不允许拦截
        getParent().requestDisallowInterceptTouchEvent(true);

        return super.onTouchEvent(arg0);
    }
}
