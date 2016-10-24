package com.ningjiahao.firstproject.tools;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by 甯宁寧 on 2016-10-11.
 */
public class OkHttpUtils {
    private static OkHttpUtils okHttpUtils;
    private static OkHttpClient client;

    private synchronized static OkHttpUtils newOkHttpUtilsInstance() {
        if (okHttpUtils == null) {
            okHttpUtils = new OkHttpUtils();
        }
        return okHttpUtils;
    }

    /**
     * 创建一个OkHttpClient的对象
     * @return
     */
    private synchronized static OkHttpClient newOkHttpClientInstance() {
        if (client == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            client = builder.build();
        }
        return client;
    }

    /**
     * 获得Request实例
     * @param url
     * @return
     */
    private Request getRequest(String url) {
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        builder.addHeader("apikey","4b64b9f93a1b2e06de75b16754e1195a");
        return builder.build();
    }

    /**
     * 获得Call实例
     * @param url
     * @return
     */
    private Call getCall(String url) {
        okHttpUtils = newOkHttpUtilsInstance();
        client = newOkHttpClientInstance();
        Request request = okHttpUtils.getRequest(url);
        return client.newCall(request);
    }

    /**
     * 获得Response对象
     * @param url
     * @return
     * @throws Exception
     */
    private Response getResponse(String url) throws Exception{
        Call call = getCall(url);
        return call.execute();
    }

    /**
     * 获得ResponseBody实例
     * @param url
     * @return
     * @throws Exception
     */
    private ResponseBody getResponseBody(String url) throws Exception{
        Response response = getResponse(url);
        if (response != null && response.isSuccessful()) {
            return response.body();
        }
        return null;
    }

    /**
     * 通过Url获得字符串 -- 同步的GET 请求
     * @param url
     * @return
     * @throws Exception
     */
    public static String getString(String url) throws Exception{
        okHttpUtils = newOkHttpUtilsInstance();
        ResponseBody responseBody = okHttpUtils.getResponseBody(url);
        if (responseBody != null) {
            return responseBody.string();
        }
        return null;
    }

    /**
     * 通过Url获得字节数组 -- 同步的GET 请求
     * @param url
     * @return
     * @throws Exception
     */
    public static byte[] getBytes(String url) throws Exception{
        okHttpUtils = newOkHttpUtilsInstance();
        ResponseBody responseBody = okHttpUtils.getResponseBody(url);
        if (responseBody != null) {
            return responseBody.bytes();
        }
        return null;
    }

    /**
     * 通过Url获得输入流 -- 同步的GET 请求
     * @param url
     * @return
     * @throws Exception
     */
    public static InputStream getInputStream(String url) throws Exception{
        okHttpUtils = newOkHttpUtilsInstance();
        ResponseBody responseBody = okHttpUtils.getResponseBody(url);
        if (responseBody != null) {
            return responseBody.byteStream();
        }
        return null;
    }

    //--------------------------异步请求------------------------------
    public static void doGET(String url, Callback callback) {
        okHttpUtils = newOkHttpUtilsInstance();
        Call call = okHttpUtils.getCall(url);
        call.enqueue(callback);//执行异步的任务
    }


    public static final int KEY_FAILE = 0;
    public static final int KEY_SUCCESS = 1;
    public static void getAsyncString(final  String url, final Handler handler) {
        okHttpUtils = newOkHttpUtilsInstance();
        Call call = okHttpUtils.getCall(url);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = handler.obtainMessage();
                msg.what = KEY_FAILE;
                msg.obj = e.getMessage();
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    ResponseBody responseBody = response.body();
                    if (responseBody != null) {
                        String result = responseBody.string();
                        Message msg = handler.obtainMessage();
                        msg.what = KEY_SUCCESS;
                        msg.obj = result;
                        handler.sendMessage(msg);
                    }
                }
            }
        });
    }


}
