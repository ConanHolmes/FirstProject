package com.ningjiahao.firstproject.IntroduceFragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 甯宁寧 on 2016-09-10.
 */
public class Urltools {

        public static String encode(String str){
            String s= null;
            try {
                s = URLEncoder.encode(str,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return s;
        }
        public static String request(String str) {
            BufferedReader reader = null;
            /*String s=encode(canshu);


            httpUrl = httpUrl + "?" + httpArg+s;*/
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
        }
    public static Bitmap NetBit(String ss) throws IOException{
        HttpURLConnection conn=null;
        InputStream is=null;
        Bitmap bit=null;
        try{
            URL u=new URL(ss);
            conn = (HttpURLConnection) u.openConnection();
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            is=conn.getInputStream();
            bit= BitmapFactory.decodeStream(is);
        }catch(Exception e){
            e.printStackTrace();
        }finally{

        }
        return bit;
    }




    }

