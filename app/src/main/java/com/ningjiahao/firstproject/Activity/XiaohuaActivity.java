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

import com.ningjiahao.firstproject.IntroduceFragment.IntroduceFragment;
import com.ningjiahao.firstproject.IntroduceFragment.News;
import com.ningjiahao.firstproject.R;
import com.ningjiahao.firstproject.http.HttpUtils;
import com.ningjiahao.firstproject.info.ContentlistBean;
import com.ningjiahao.firstproject.info.xiaohua;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class XiaohuaActivity extends AppCompatActivity {

    @BindView(R.id.xiaohua_tool)
    Toolbar xiaohuaTool;
    @BindView(R.id.recycler_xiaohua)
    RecyclerView recyclerXiaohua;
    @BindView(R.id.activity_xiaohua)
    LinearLayout activityXiaohua;
    private List<ContentlistBean> lst=new ArrayList<>();
    private XiaohuaAdapter xiaohuaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiaohua);

        ButterKnife.bind(this);
        xiaohuaTool.setTitle("笑一笑");
        xiaohuaTool.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        xiaohuaAdapter=new XiaohuaAdapter();
        recyclerXiaohua.setLayoutManager(new LinearLayoutManager(XiaohuaActivity.this,LinearLayout.VERTICAL,false));
        recyclerXiaohua.setAdapter(xiaohuaAdapter);
        HttpUtils.newInstance().getXiaohua(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<xiaohua>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {


                    }

                    @Override
                    public void onNext(xiaohua xiaohua) {
                        lst.addAll(xiaohua.getShowapiResBody().getContentlist());

                        Toast.makeText(XiaohuaActivity.this, lst.get(1).getTitle(), Toast.LENGTH_SHORT).show();
                        xiaohuaAdapter.notifyDataSetChanged();
                    }
                });





    }
    class XiaohuaAdapter extends RecyclerView.Adapter<XiaohuaAdapter.XiaohuaViewHolder>{
        @Override
        public XiaohuaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(XiaohuaActivity.this).inflate(R.layout.item_xiaohua, parent, false);
            return new XiaohuaViewHolder(v);
        }

        @Override
        public void onBindViewHolder(XiaohuaViewHolder holder, int position) {
            ContentlistBean contentlistBean = lst.get(position);
            holder.xiaohuatitle.setText(contentlistBean.getTitle());

            holder.xiaohuacontent.setText(contentlistBean.getText());

        }

        @Override
        public int getItemCount() {
            return lst.size();
        }

        class XiaohuaViewHolder extends RecyclerView.ViewHolder{
            TextView xiaohuatitle,xiaohuacontent;

            public XiaohuaViewHolder(View itemView) {
                super(itemView);
                xiaohuatitle= (TextView) itemView.findViewById(R.id.xiaohua_title);
                xiaohuacontent= (TextView) itemView.findViewById(R.id.xiaohua_content);
            }
        }
    }
}
