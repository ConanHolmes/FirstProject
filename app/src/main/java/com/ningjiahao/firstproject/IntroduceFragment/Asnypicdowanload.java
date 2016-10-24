package com.ningjiahao.firstproject.IntroduceFragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.ningjiahao.firstproject.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

/**
 * Created by 甯宁寧 on 2016-09-10.
 */
public class Asnypicdowanload extends AsyncTask<Void, Void, Bitmap>{

    private String url;
    private Context context;
    private RequestCreator p;
    private ImageView img;
    public Asnypicdowanload(String url,Context context,ImageView img) {
        this.url=url;
        this.context=context;
        this.img=img;
    }
    @Override
    protected Bitmap doInBackground(Void... params) {
        Bitmap bit=null;
        if(url.equals("")){
            p=Picasso.with(context).load(R.drawable.yin_zuidiceng);
        }else{
            p=Picasso.with(context).load(url);
        }
        return bit;
    }
    @Override
    protected void onPostExecute(Bitmap bit) {
        super.onPostExecute(bit);
        p.into(img);
    }

}
