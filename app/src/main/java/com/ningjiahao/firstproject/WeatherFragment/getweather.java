package com.ningjiahao.firstproject.WeatherFragment;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.ningjiahao.firstproject.QueryFragment.Train;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 甯宁寧 on 2016-10-10.
 */
public class getweather extends AsyncTask<Void,Void,String>{
    private String location;
    private IweatherListener i;
    private TextView txt;
    public getweather(String location,IweatherListener i){
        this.location=location;
        this.i=i;
    }
    @Override
    protected String doInBackground(Void... params) {
        String str=weathertools.weatherrequest(location);
        return str;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            weather w=explain(s);
            i.Setweather(w);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Tag","异常");
        }

    }
    public static weather explain(String s) throws JSONException {
        weather w = new weather();
        JSONObject json = new JSONObject(s);
        JSONArray array=json.getJSONArray("results");
        JSONObject json2= (JSONObject) array.get(0);
        JSONObject json3=json2.getJSONObject("location");
        String cityname=json3.getString("name");//城市名
        w.setWeather_cityname(cityname);
        JSONArray weatherarray=json2.getJSONArray("daily");
        JSONObject todayweather=weatherarray.getJSONObject(0);
        String todaydate=todayweather.getString("date");//当天日期
        w.setWeather_date(todaydate);
        String todaytxt=todayweather.getString("text_day");//当天天气信息
        w.setWeather_txt(todaytxt);
        String todaylow=todayweather.getString("low");
        String todayhigh=todayweather.getString("high");
        String todayinfo=todaylow+"-"+todayhigh+"度";//当天温度信息
        w.setWeather_info(todayinfo);
        JSONObject tomorrowweather=weatherarray.getJSONObject(1);
        String tomorrowdate=tomorrowweather.getString("date");//明天天日期
        w.setTomorrow_date(tomorrowdate);
        String tomorrowtxt=tomorrowweather.getString("text_day");//明天天气信息
        w.setTomorrow_txt(tomorrowtxt);
        String tomorrowlow=tomorrowweather.getString("low");
        String tomorrowhigh=tomorrowweather.getString("high");
        String tomorrowinfo=tomorrowlow+"-"+tomorrowhigh+"度";//明天温度信息
        w.setTomorrow_info(tomorrowinfo);
        JSONObject houtianweather=weatherarray.getJSONObject(2);
        String houtiandate=houtianweather.getString("date");//后天天日期
        w.setHoutian_date(houtiandate);
        String houtiantxt=houtianweather.getString("text_day");//后天天气信息
        w.setHoutian_txt(houtiantxt);
        String houtianlow=houtianweather.getString("low");
        String houtianhigh=houtianweather.getString("high");
        String houtianinfo=houtianlow+"-"+houtianhigh+"度";//后天温度信息
        w.setHoutian_info(houtianinfo);
        return w;

    }
}
