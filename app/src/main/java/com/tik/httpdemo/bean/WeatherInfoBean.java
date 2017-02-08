package com.tik.httpdemo.bean;

public class WeatherInfoBean {
    String Radar;
    String SD;
    String WD;
    String WS;
    String WSE;
    String city;
    String cityid;
    String isRadar;
    String njd;
    String qy;
    String rain;
    String temp;
    String time;

    public String getRadar() {
        return Radar;
    }

    public void setRadar(String radar) {
        Radar = radar;
    }

    public String getSD() {
        return SD;
    }

    public void setSD(String SD) {
        this.SD = SD;
    }

    public String getWD() {
        return WD;
    }

    public void setWD(String WD) {
        this.WD = WD;
    }

    public String getWS() {
        return WS;
    }

    public void setWS(String WS) {
        this.WS = WS;
    }

    public String getWSE() {
        return WSE;
    }

    public void setWSE(String WSE) {
        this.WSE = WSE;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getIsRadar() {
        return isRadar;
    }

    public void setIsRadar(String isRadar) {
        this.isRadar = isRadar;
    }

    public String getNjd() {
        return njd;
    }

    public void setNjd(String njd) {
        this.njd = njd;
    }

    public String getQy() {
        return qy;
    }

    public void setQy(String qy) {
        this.qy = qy;
    }

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "WeatherInfoBean{" +
                "Radar='" + Radar + '\'' +
                ", SD='" + SD + '\'' +
                ", WD='" + WD + '\'' +
                ", WS='" + WS + '\'' +
                ", WSE='" + WSE + '\'' +
                ", city='" + city + '\'' +
                ", cityid='" + cityid + '\'' +
                ", isRadar='" + isRadar + '\'' +
                ", njd='" + njd + '\'' +
                ", qy='" + qy + '\'' +
                ", rain='" + rain + '\'' +
                ", temp='" + temp + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
