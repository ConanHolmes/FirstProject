package com.ningjiahao.firstproject.info;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 甯宁寧 on 2016-10-22.
 */

public class Bagua {
    /**
     * ctime : 2016-10-21 07:51
     * title : 经历家庭纠纷、被疑炒作，张靓颖放空了一星期
     * description : 腾讯明星
     * picUrl : http://img1.gtimg.com/ent/pics/hv1/57/198/2143/139399122_small.png
     * url : http://ent.qq.com/a/20161021/005789.htm
     */

    @SerializedName("newslist")
    private List<NewslistBean> newslist;

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }


}
