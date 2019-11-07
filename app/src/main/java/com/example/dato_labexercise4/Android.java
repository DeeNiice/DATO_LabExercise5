package com.example.dato_labexercise4;

public class Android {
    int logo;
    String name, version, api, rdate, dbmsg;

    public Android(int logo, String name, String version, String api, String rdate, String dbmsg) {
        this.logo = logo;
        this.name = name;
        this.version = version;
        this.api = api;
        this.rdate = rdate;
        this.dbmsg =dbmsg;

    }

    public int getLogo(){
        return logo;
    }

    public String getName() {
        return name;
    }

    public String getVersion(){
        return version;
    }

    public String getApi(){
        return api;
    }

    public String getRdate(){
        return rdate;
    }

    public String getDbmsg() {
        return dbmsg;}
}
