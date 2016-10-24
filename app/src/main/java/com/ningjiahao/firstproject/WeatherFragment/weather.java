package com.ningjiahao.firstproject.WeatherFragment;

/**
 * Created by 甯宁寧 on 2016-10-10.
 */
public class weather {
    /**
     * @BindView(R.id.weather_info)
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
     */
    private String weather_info;
    private String weather_txt;
    private String weather_cityname;
    private String weather_date;
    private String tomorrow_info;
    private String tomorrow_txt;
    private String tomorrow_date;
    private String houtian_info;
    private String houtian_txt;
    private String houtian_date;

    public String getWeather_info() {
        return weather_info;
    }

    public void setWeather_info(String weather_info) {
        this.weather_info = weather_info;
    }

    public String getWeather_txt() {
        return weather_txt;
    }

    public void setWeather_txt(String weather_txt) {
        this.weather_txt = weather_txt;
    }

    public String getWeather_cityname() {
        return weather_cityname;
    }

    public void setWeather_cityname(String weather_cityname) {
        this.weather_cityname = weather_cityname;
    }

    public String getWeather_date() {
        return weather_date;
    }

    public void setWeather_date(String weather_date) {
        this.weather_date = weather_date;
    }

    public String getTomorrow_info() {
        return tomorrow_info;
    }

    public void setTomorrow_info(String tomorrow_info) {
        this.tomorrow_info = tomorrow_info;
    }

    public String getTomorrow_txt() {
        return tomorrow_txt;
    }

    public void setTomorrow_txt(String tomorrow_txt) {
        this.tomorrow_txt = tomorrow_txt;
    }

    public String getTomorrow_date() {
        return tomorrow_date;
    }

    public void setTomorrow_date(String tomorrow_date) {
        this.tomorrow_date = tomorrow_date;
    }

    public String getHoutian_info() {
        return houtian_info;
    }

    public void setHoutian_info(String houtian_info) {
        this.houtian_info = houtian_info;
    }

    public String getHoutian_txt() {
        return houtian_txt;
    }

    public void setHoutian_txt(String houtian_txt) {
        this.houtian_txt = houtian_txt;
    }

    public String getHoutian_date() {
        return houtian_date;
    }

    public void setHoutian_date(String houtian_date) {
        this.houtian_date = houtian_date;
    }
}
