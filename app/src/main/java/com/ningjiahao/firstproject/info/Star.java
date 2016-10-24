package com.ningjiahao.firstproject.info;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 甯宁寧 on 2016-10-23.
 */

public class Star {

    /**
     * date : 20161023
     * name : 射手座
     * datetime : 2016年10月23日
     * all : 60%
     * color : 蓝色
     * health : 83%
     * love : 40%
     * money : 40%
     * number : 2
     * QFriend : 双鱼座
     * summary : 脚踏实地又充满信心是今日你的写照。学习状态越来越好，脚踏实地的面对任务，规划自身，有效地学习与思考。有利于你思考个人的瓶颈，建立一个更有效的行动方案。
     * work : 60%
     * resultcode : 200
     * error_code : 0
     */

    @SerializedName("date")
    private int date;
    @SerializedName("name")
    private String name;
    @SerializedName("datetime")
    private String datetime;
    @SerializedName("all")
    private String all;
    @SerializedName("color")
    private String color;
    @SerializedName("health")
    private String health;
    @SerializedName("love")
    private String love;
    @SerializedName("money")
    private String money;
    @SerializedName("number")
    private int number;
    @SerializedName("QFriend")
    private String QFriend;
    @SerializedName("summary")
    private String summary;
    @SerializedName("work")
    private String work;
    @SerializedName("resultcode")
    private String resultcode;
    @SerializedName("error_code")
    private int errorCode;

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getLove() {
        return love;
    }

    public void setLove(String love) {
        this.love = love;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getQFriend() {
        return QFriend;
    }

    public void setQFriend(String QFriend) {
        this.QFriend = QFriend;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
