package com.ningjiahao.firstproject.QueryFragment;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.ningjiahao.firstproject.IntroduceFragment.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by 甯宁寧 on 2016-10-09.
 */
public class queryTickets extends AsyncTask<Void,Void,String>{
    private String from,to,date;
    private static List<Train> lst;
    private QueryFragment.TrainAdapter trainAdapter;
    private static Context context;
    public queryTickets(String from,String to,String date,List<Train> lst,QueryFragment.TrainAdapter trainAdapter,Context context){
        this.from=from;
        this.to=to;
        this.date=date;
        this.lst=lst;
        this.trainAdapter=trainAdapter;
        this.context=context;
    }
    @Override
    protected String doInBackground(Void... params) {

        String str=QueryTools.query_request(from,to,date);

        return str;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if(s!=null) {
                explain(s);
                trainAdapter.notifyDataSetChanged();
            }else {
                Toast.makeText(context, "网络出现错误", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
                Toast.makeText(context, "没有相关票务", Toast.LENGTH_SHORT).show();
        }


    }
    public static void explain(String s) throws JSONException {
            lst.clear();
            Train d = new Train();
            JSONObject json = new JSONObject(s);
            JSONObject json2=json.getJSONObject("data");
            JSONArray array=json2.getJSONArray("trainList");
            for(int i=0;i<array.length();i++){
                Train t=new Train();
                JSONObject js3 = (JSONObject) array.get(i);
                String train_type=js3.getString("trainType");
                t.setTraintype(train_type);
                String trainNo=js3.getString("trainNo");
                t.setTrainNo(trainNo);
                String from_place=js3.getString("from");
                t.setFrom(from_place);
                String to_place=js3.getString("to");
                t.setTo(to_place);
                String start_time=js3.getString("startTime");
                t.setStartTime(start_time);
                String end_time=js3.getString("endTime");
                t.setEndTime(end_time);
                String duration=js3.getString("duration");
                t.setDuration(duration);
                JSONArray seatList=js3.getJSONArray("seatInfos");
                StringBuffer sb=new StringBuffer();
                for(int j=0;j<seatList.length();j++){
                    JSONObject seat = (JSONObject)seatList.get(j);
                    String seatType=seat.getString("seat");
                    sb.append(seatType+":");
                    String remainNum=String.valueOf(seat.getInt("remainNum"));
                    sb.append(remainNum+"  ¥");
                    String price=seat.getString("seatPrice");
                    sb.append(price+"     ");

                }
                t.setSeatInfo(sb.toString());
                lst.add(t);
            }



    }
}
