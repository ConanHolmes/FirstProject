package com.ningjiahao.firstproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ningjiahao.firstproject.R;
import com.ningjiahao.firstproject.http.HttpUtils;
import com.ningjiahao.firstproject.info.Bagua;
import com.ningjiahao.firstproject.info.NewslistBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MovieActivity extends AppCompatActivity {

    @BindView(R.id.xiaohua_tool)
    Toolbar xiaohuaTool;
    @BindView(R.id.recycler_xiaohua)
    RecyclerView recyclerXiaohua;
    @BindView(R.id.activity_xiaohua)
    LinearLayout activityXiaohua;
    private NewsAdapter movadapter;
    private List<NewslistBean> lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiaohua);
        ButterKnife.bind(this);
        xiaohuaTool.setTitle("明星八卦");
        xiaohuaTool.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        lst = new ArrayList<>();
        movadapter = new NewsAdapter();
        recyclerXiaohua.setLayoutManager(new LinearLayoutManager(MovieActivity.this, LinearLayout.VERTICAL, false));
        recyclerXiaohua.setAdapter(movadapter);
        HttpUtils.newInstance().getBagua(10, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Bagua>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {


                    }

                    @Override
                    public void onNext(Bagua bagua) {
                        lst.addAll(bagua.getNewslist());
                        Toast.makeText(MovieActivity.this, lst.get(1).getTitle(), Toast.LENGTH_SHORT).show();
                        movadapter.notifyDataSetChanged();
                    }
                });

    }

    class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(MovieActivity.this).inflate(R.layout.news_item_layout, parent, false);
            return new viewholder(v);
        }


        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            NewslistBean d = lst.get(position);
            viewholder v = (viewholder) holder;
            v.title.setText(d.getTitle());
            v.time.setText(d.getCtime());
            Glide.with(MovieActivity.this).load(d.getPicUrl()).into(v.img);
        }

        @Override
        public int getItemCount() {
            return lst.size();
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

            Intent ii = new Intent(MovieActivity.this, WebActivity.class);
            ii.putExtra("Key", lst.get(getPosition()).getUrl());
            startActivity(ii);
            MovieActivity.this.overridePendingTransition(R.anim.a_to_b_in, R.anim.a_to_b_out);

        }
    }

}
