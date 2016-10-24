package com.ningjiahao.firstproject.http;

import com.ningjiahao.firstproject.IntroduceFragment.Urltools;
import com.ningjiahao.firstproject.info.Bagua;
import com.ningjiahao.firstproject.info.Star;
import com.ningjiahao.firstproject.info.xiaohua;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by 甯宁寧 on 2016-10-22.
 */

public class HttpUtils {
    private static HttpUtils httpUtils;
    private MyRetrofitApi myRetrofitApi;
    private Retrofit retrofit;
    private HttpUtils(){
        retrofit=new Retrofit.Builder().baseUrl(Myconfig.BASE_URL).
                addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        myRetrofitApi=retrofit.create(MyRetrofitApi.class);

    }
    public static HttpUtils newInstance(){
        if(httpUtils==null){
            httpUtils=new HttpUtils();
        }
        return httpUtils;
    }
    public Observable<xiaohua> getXiaohua(int i){
        return myRetrofitApi.getXiaohua(i);
    }
    public Observable<Bagua> getBagua(int i,int j){
        return myRetrofitApi.getBagua(Myconfig.BAGUA_BASE,i,j);
    }
    public Observable<Star> getStar(String  str){
        return myRetrofitApi.getStar(Myconfig.STAR_URL,str,"today");
    }
    /*public static String getStar(String str) {
        BufferedReader reader = null;
        String star=Myconfig.STAR_URL+Myconfig.STAR_path+ Urltools.encode(str)+Myconfig.STAR_PARAMS;
        StringBuffer sbf = new StringBuffer();
        String result = null;
        try {
            URL url = new URL(str);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            // 填入apikey到HTTP header
            connection.setRequestProperty("apikey","4b64b9f93a1b2e06de75b16754e119c");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }*/
}
