package com.ningjiahao.firstproject.info;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 甯宁寧 on 2016-10-22.
 */

public  class ContentlistBean {
    @SerializedName("ct")
    private String ct;
    @SerializedName("text")
    private String text;
    @SerializedName("title")
    private String title;
    @SerializedName("type")
    private int type;

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

