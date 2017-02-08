package com.tik.httpdemo.bean;

public class WeatherBean {
    WeatherInfoBean weatherinfo;

    public WeatherInfoBean getWeatherinfo() {
        return weatherinfo;
    }

    public void setWeatherinfo(WeatherInfoBean weatherinfo) {
        this.weatherinfo = weatherinfo;
    }

    @Override
    public String toString() {
        return "WeatherBean{" +
                "weatherinfo=" + weatherinfo.toString() +
                '}';
    }
}
