package com.ningjiahao.firstproject.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ningjiahao.firstproject.R;
import com.ningjiahao.firstproject.http.HttpUtils;
import com.ningjiahao.firstproject.info.ContentlistBean;
import com.ningjiahao.firstproject.info.Star;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static android.R.id.list;
import static com.ningjiahao.firstproject.R.id.star_content;

public class StarActivity extends AppCompatActivity {
    public static final String [] STAR_Constellation={
        "白羊座","金牛座","双子座","巨蟹座","狮子座","处女座","天秤座","天蝎座","射手座","摩羯座",
        "水瓶座","双鱼座"
};
    @BindView(R.id.xiaohua_tool)
    Toolbar xiaohuaTool;
    @BindView(R.id.recycler_xiaohua)
    RecyclerView recyclerXiaohua;
    @BindView(R.id.activity_xiaohua)
    LinearLayout activityXiaohua;
    private static final List<Star> lst=new ArrayList<>();
    private StarAdapter starAdapter;
    CompositeSubscription compositeSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiaohua);
        ButterKnife.bind(this);
        xiaohuaTool.setTitle("星座运势");
        xiaohuaTool.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        compositeSubscription=new CompositeSubscription();
        starAdapter=new StarAdapter();
        recyclerXiaohua.setLayoutManager(new LinearLayoutManager(this,LinearLayout.VERTICAL,false));
        recyclerXiaohua.setAdapter(starAdapter);
        for(int i=0;i<STAR_Constellation.length;i++){
            Subscription subscription=HttpUtils.newInstance().getStar(STAR_Constellation[i])
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Star>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(StarActivity.this, "???", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNext(Star star) {
                            lst.add(star);
                            starAdapter.notifyDataSetChanged();
                        }
                    });

            compositeSubscription.add(subscription);

        }





    }

    private void initData(final List<Star> list) {


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeSubscription.unsubscribe();
    }

    class StarAdapter extends RecyclerView.Adapter<StarAdapter.StarViewHolder>{
        @Override
        public StarAdapter.StarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(StarActivity.this).inflate(R.layout.item_star, parent, false);
            return new StarAdapter.StarViewHolder(v);
        }

        @Override
        public void onBindViewHolder(StarAdapter.StarViewHolder holder, int position) {
            Star star=lst.get(position);
            holder.star_name.setText(star.getName());
            holder.star_color.setText("幸运颜色"+star.getColor());
            holder.star_health.setText("健康指数"+star.getHealth());
            holder.star_love.setText("爱情指数"+star.getLove());
            holder.star_money.setText("金钱指数"+star.getMoney());
            holder.star_work.setText("工作指数"+star.getWork());
            holder.star_friend.setText("星座朋友"+star.getQFriend());
            holder.star_content.setText("今日运势"+star.getSummary());
        }

        @Override
        public int getItemCount() {
            return lst.size();
        }

        class StarViewHolder extends RecyclerView.ViewHolder{
            TextView star_name,star_color,star_health,star_love,star_money,star_work,star_friend,star_content;

            public StarViewHolder(View itemView) {
                super(itemView);
                star_name= (TextView) itemView.findViewById(R.id.star_name);
                star_color= (TextView) itemView.findViewById(R.id.star_color);
                star_health= (TextView) itemView.findViewById(R.id.star_health);
                star_love= (TextView) itemView.findViewById(R.id.star_love);
                star_money= (TextView) itemView.findViewById(R.id.star_money);
                star_work= (TextView) itemView.findViewById(R.id.star_work);
                star_friend= (TextView) itemView.findViewById(R.id.star_friend);
                star_content= (TextView) itemView.findViewById(R.id.star_content);

            }
        }
    }
}
