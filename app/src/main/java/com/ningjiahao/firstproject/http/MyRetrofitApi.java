package com.ningjiahao.firstproject.http;

import com.ningjiahao.firstproject.info.Bagua;
import com.ningjiahao.firstproject.info.Star;
import com.ningjiahao.firstproject.info.xiaohua;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by 甯宁寧 on 2016-10-22.
 */

public interface MyRetrofitApi {
    @Headers("apikey:4b64b9f93a1b2e06de75b16754e1195a")
    @GET(Myconfig.PATH_URL)
    Observable<xiaohua> getXiaohua(@Query("page") int i);


    @Headers("apikey:4b64b9f93a1b2e06de75b16754e1195a")
    @POST
    Observable<Bagua> getBagua(@Url String url,@Query("num") int i, @Query("page") int j);

    @Headers("apikey:4b64b9f93a1b2e06de75b16754e1195a")
    @POST
    Observable<Star> getStar(@Url String url, @Query("consName") String str, @Query("type") String today);
}
