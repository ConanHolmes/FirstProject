package com.ningjiahao.firstproject.IntroduceFragment;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.ningjiahao.firstproject.Activity.MainActivity;
import com.ningjiahao.firstproject.tools.OkHttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by 甯宁寧 on 2016-10-08.
 */
class yibu extends AsyncTask<Void,Void,String> {
    private String canshu;
    private static ArrayList<News> lst;
    private IntroduceFragment.NewsAdapter news;
    private static Context context;
    private Istopreflash istopreflash;
    private int i;
    public yibu(int i, String canshu, ArrayList<News> lst,IntroduceFragment.NewsAdapter news,Context context,Istopreflash istopreflash) {
        this.i=i;
        this.canshu = canshu;
        this.lst = lst;
        this.news=news;
        this.context=context;
        this.istopreflash=istopreflash;
    }

    @Override
    protected String doInBackground(Void... params) {
        String s=Urltools.encode(canshu);
        String url=String.format(MyContants.URL_NEWS,i,s);
        String str=null;
        try {
            //str = Urltools.request(url);
            str=OkHttpUtils.getString(url);
            Log.e("Tag",str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //String str = Urltools.request(MyContants.HTTP_URL, MyContants.HTTP_ARG, canshu);
        return str;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
           if(s!=null) {
               explain(s);
               news.notifyDataSetChanged();
               //Toast.makeText(context, "刷新", Toast.LENGTH_SHORT).show();
               istopreflash.stop();
           }else{
               Toast.makeText(context, "网络出现错误", Toast.LENGTH_SHORT).show();
               istopreflash.stop();
           }
        } catch (JSONException e) {
            e.printStackTrace();
                Toast.makeText(context, "没有相关新闻", Toast.LENGTH_SHORT).show();
            istopreflash.stop();
        }

    }

    public static void explain(String s) throws JSONException {
        for (int i = 0; i < 10; i++) {
            News d = new News();
            JSONObject json = new JSONObject(s);
            JSONArray array = json.getJSONArray("newslist");
            JSONObject js3 = (JSONObject) array.get(i);
            String title = (String) js3.get("title");
            String pic = (String) js3.get("picUrl");
            String time = (String) js3.get("ctime");
            String address = (String) js3.get("url");
            d.setTitle(title);
            d.setAdress(address);
            d.setTime(time);
            d.setPic(pic);
            lst.add(0,d);
        }


    }
}
