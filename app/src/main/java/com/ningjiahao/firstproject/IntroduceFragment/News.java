package com.ningjiahao.firstproject.IntroduceFragment;

import java.io.Serializable;

/**
 * Created by 甯宁寧 on 2016-10-04.
 */
public class News implements Serializable{
    private String title;
    private String time;
    private String pic;
    private String adress;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
