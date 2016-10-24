package com.ningjiahao.firstproject.IntroduceFragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ningjiahao.firstproject.Activity.WebActivity;
import com.ningjiahao.firstproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class IntroduceFragment extends Fragment {
    //广告条图片地址
    private static final String[] AD_URLS = {
            "http://img1.imgtn.bdimg.com/it/u=1422870419,611925770&fm=11&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=393893421,2085519897&fm=11&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=4194842371,2003362742&fm=21&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=1116170970,3346881682&fm=21&gp=0.jpg"
    };

    ImageView top;
    private WebView web;
    private int page = 1;
    private Button btn2;
    private NewsAdapter newsadapter;
    private Istopreflash istopreflash = new Istopreflash() {
        @Override
        public void stop() {
            swipe.setRefreshing(false);
        }
    };
    //广告条轮播ViewPager
    private ViewPager intrduce_vp;
    //轮播view集合
    private List<ImageView> lst;
    //广告圆点布局
    private LinearLayout lin;
    private Context context;
    private Myadapter my;
    private ArrayList<News> news_lst;
    private EditText edi;
    private SwipeRefreshLayout swipe;
    private Handler handler = new Handler();
    private RecyclerView recycler;
    String canshu = "林丹";
    //让当前页面往后走一页
    private Runnable adRunnable = new Runnable() {
        @Override
        public void run() {
            //获得当前画面的位置
            int position = intrduce_vp.getCurrentItem();
            position++;
            //重新设置位置
            intrduce_vp.setCurrentItem(position);

            //无限自动轮播实现了
            handler.postDelayed(adRunnable, 3000);
        }
    };

    public IntroduceFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_introduce, container, false);
        initData();
        initNewsData(v);
        initADDot(v);
        initView(v);
        ButterKnife.bind(this, v);
        return v;
    }

    private void initNewsData(View v) {
        news_lst = new ArrayList<>();
        recycler = (RecyclerView) v.findViewById(R.id.recycler);
        newsadapter = new NewsAdapter();
        recycler.setAdapter(newsadapter);
        new yibu(page, canshu, news_lst, newsadapter, context, istopreflash).execute();
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(adRunnable, 2000);
    }

    private void initADDot(View v) {
        lin = (LinearLayout) v.findViewById(R.id.layout_ad_dot);
        for (int i = 0; i < AD_URLS.length; i++) {
            //动态生成View
            ImageView iv = new ImageView(context);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.leftMargin = 20;
            iv.setLayoutParams(layoutParams);
            if (i == 0) {
                iv.setImageResource(R.drawable.submit_login_confirm_normal);
            } else {
                iv.setImageResource(R.drawable.submit_login_confirm_disable);
            }
            lin.addView(iv);
        }

    }

    private void initData() {

        lst = new ArrayList<>();
        for (int i = 0; i < AD_URLS.length; i++) {
            //动态生成View
            ImageView iv = new ImageView(context);
            iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            //使用Picasso加载网络图片
            Picasso.with(context).load(AD_URLS[i]).into(iv);
            lst.add(iv);
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initView(View v) {
        top= (ImageView) v.findViewById(R.id.top);
        recycler.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if(layoutManager.findFirstVisibleItemPosition()>6){
                        top.setVisibility(View.VISIBLE);
                }else{
                    top.setVisibility(View.GONE);
                }
            }
        });
        swipe = (SwipeRefreshLayout) v.findViewById(R.id.swipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe.setRefreshing(true);
                page++;
                new yibu(page, canshu, news_lst, newsadapter, context, istopreflash).execute();
            }
        });
        web = (WebView) v.findViewById(R.id.web);
        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recycler.smoothScrollToPosition(0);
            }
        });
        intrduce_vp = (ViewPager) v.findViewById(R.id.introduce_vp);
        btn2 = (Button) v.findViewById(R.id.btn2);
        edi = (EditText) v.findViewById(R.id.news_edit);
        edi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                canshu = edi.getText().toString();
            }
        });
        my = new Myadapter(lst);
        intrduce_vp.setAdapter(my);
        intrduce_vp.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % lst.size());
        intrduce_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                //重新设置圆点状态
                setADDot(position % lst.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        //ViewPager的触屏处理，解决触屏时停止无限轮播
        intrduce_vp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int actiton = motionEvent.getAction();
                switch (actiton) {
                    case MotionEvent.ACTION_DOWN: //按下
                        handler.removeCallbacks(adRunnable);
                        break;
                    case MotionEvent.ACTION_UP: //放开
                        handler.postDelayed(adRunnable, 4000);
                        break;
                }
                return false;
            }
        });

        newsadapter.notifyDataSetChanged();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recycler.setLayoutManager(layoutManager);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                news_lst.clear();
                page = 1;
                new yibu(page, canshu, news_lst, newsadapter, context, istopreflash).execute();

            }
        });

    }

    //广告圆点高亮显示
    private void setADDot(int position) {
        for (int i = 0; i < lin.getChildCount(); i++) {
            ImageView iv = (ImageView) lin.getChildAt(i);
            if (i == position) {
                iv.setImageResource(R.drawable.submit_login_confirm_normal);
            } else {
                iv.setImageResource(R.drawable.submit_login_confirm_disable);
            }
        }
    }


    class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.news_item_layout, parent, false);
            return new viewholder(v);
        }


        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            News d = news_lst.get(position);
            viewholder v = (viewholder) holder;
            v.title.setText(d.getTitle());
            v.time.setText(d.getTime());
            new Asnypicdowanload(d.getPic(), context, v.img).execute();
        }

        @Override
        public int getItemCount() {
            return news_lst.size();
        }

    }

    class viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView img;
        TextView title, time;

        public viewholder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img);
            title = (TextView) itemView.findViewById(R.id.news_title);
            time = (TextView) itemView.findViewById(R.id.news_time);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Intent ii = new Intent(context, WebActivity.class);
            ii.putExtra("Key", news_lst.get(getPosition()).getAdress());
            startActivity(ii);
            getActivity().overridePendingTransition(R.anim.a_to_b_in, R.anim.a_to_b_out);

        }
    }


}
