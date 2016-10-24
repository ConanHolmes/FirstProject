package com.ningjiahao.firstproject.WeatherFragment;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ningjiahao.firstproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment extends Fragment {

    @BindView(R.id.weather_bg)
    NestedScrollView weatherBg;
    private IweatherListener i = new IweatherListener() {
        @Override
        public void Setweather(weather w) {
            weatherInfo.setText(w.getWeather_info());
            weatherTxt.setText(w.getWeather_txt());
            setWeatherPic(w.getWeather_txt(), weatherPic);
            setWeatherBg(w.getWeather_txt(),weatherBg);
            weatherCityname.setText(w.getWeather_cityname());
            weatherDate.setText(w.getWeather_date());
            tomorrowTxt.setText(w.getTomorrow_txt());
            setWeatherPic(w.getTomorrow_txt(), tomorrowPic);
            tomorrowInfo.setText(w.getTomorrow_info());
            tomorrowDate.setText(w.getTomorrow_date());
            houtianTxt.setText(w.getHoutian_txt());
            setWeatherPic(w.getHoutian_txt(), houtianPic);
            houtianInfo.setText(w.getHoutian_info());
            houtianDate.setText(w.getHoutian_date());
        }
    };

    @BindView(R.id.weather_cityname_edit)
    EditText weatherCitynameEdit;
    @BindView(R.id.search_city)
    Button searchCity;
    @BindView(R.id.weather_info)
    TextView weatherInfo;
    @BindView(R.id.weather_pic)
    ImageView weatherPic;
    @BindView(R.id.weather_txt)
    TextView weatherTxt;
    @BindView(R.id.weather_cityname)
    TextView weatherCityname;
    @BindView(R.id.weather_date)
    TextView weatherDate;
    @BindView(R.id.tomorrow_pic)
    ImageView tomorrowPic;
    @BindView(R.id.tomorrow_txt)
    TextView tomorrowTxt;
    @BindView(R.id.tomorrow_date)
    TextView tomorrowDate;
    @BindView(R.id.tomorrow_info)
    TextView tomorrowInfo;
    @BindView(R.id.houtian_pic)
    ImageView houtianPic;
    @BindView(R.id.houtian_txt)
    TextView houtianTxt;
    @BindView(R.id.houtian_date)
    TextView houtianDate;
    @BindView(R.id.houtian_info)
    TextView houtianInfo;

    public WeatherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.bind(this, view);
        new getweather("大连", i).execute();
        return view;
    }

    @OnClick(R.id.search_city)
    public void onClick() {
        String city = weatherCitynameEdit.getText().toString();
        new getweather(city, i).execute();
        Log.e("Tag", "???" + city);
    }

    public static void setWeatherPic(String str, ImageView img) {
        switch (str) {
            case "霾":
                img.setImageResource(R.drawable.wumai);
                break;
            case "多云":
                img.setImageResource(R.drawable.duoyun);
                break;
            case "小雨":
            case "阵雨":
                img.setImageResource(R.drawable.zhenyu);
                break;
            case "阴":
                img.setImageResource(R.drawable.yin);
                break;
            case "晴":
                img.setImageResource(R.drawable.sun);
                break;
            case "大雨":
            case "中雨":
                img.setImageResource(R.drawable.dayu);
                break;
            default:
                img.setImageResource(R.drawable.moren);

        }
    }
    public static void setWeatherBg(String str,NestedScrollView weatherBg){
        switch (str){
            case "晴":
                weatherBg.setBackgroundResource(R.drawable.qing_bg);
                break;
            case "霾":
                weatherBg.setBackgroundResource(R.drawable.mai_bg);
                break;
            case "阵雨":
            case "小雨":
            case "中雨":
            case "大雨":
            case "暴雨":
                weatherBg.setBackgroundResource(R.drawable.leiyu_bg);
                break;
            case "大雪":
            case "小雪":
            case "雨夹雪":
                weatherBg.setBackgroundResource(R.drawable.xue_bg);
                break;

        }
    }




}
