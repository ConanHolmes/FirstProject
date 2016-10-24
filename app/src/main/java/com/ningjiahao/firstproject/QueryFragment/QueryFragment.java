package com.ningjiahao.firstproject.QueryFragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.ningjiahao.firstproject.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class QueryFragment extends Fragment {
    private EditText edit_from,edit_to,edit_date;
    private Button query_btn;
    private RecyclerView query_recycler;
    private List<Train> lst;
    private Context context;
    private TrainAdapter trainAdapter;
    public QueryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_query, container, false);
        initView(v);

        return v;
    }

    private void initView(View v) {
        lst=new ArrayList<>();
        edit_from= (EditText) v.findViewById(R.id.edit_from);
        edit_to= (EditText) v.findViewById(R.id.edit_to);
        edit_date= (EditText) v.findViewById(R.id.edit_date);
        query_btn= (Button) v.findViewById(R.id.query_btn);
        query_recycler= (RecyclerView) v.findViewById(R.id.query_recycler);
        trainAdapter=new TrainAdapter();
        query_recycler.setAdapter(trainAdapter);
        trainAdapter.notifyDataSetChanged();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        query_recycler.setLayoutManager(layoutManager);
        query_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String from=edit_from.getText().toString();
                String to=edit_to.getText().toString();
                String date=edit_date.getText().toString();
                new queryTickets(from,to,date,lst,trainAdapter,context).execute();
            }
        });
    }
    class TrainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v=LayoutInflater.from(context).inflate(R.layout.ticket_item_layout,parent,false);
            return new TrainView(v);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position){
            Train t=lst.get(position);
            TrainView v= (TrainView) holder;
            v.txt_checi.setText(t.getTrainNo());
            v.train_type.setText(t.getTraintype());
            v.start_place.setText(t.getFrom());
            v.end_place.setText(t.getTo());
            v.start_time.setText("开车时间:"+t.getStartTime());
            v.end_time.setText("到达时间:"+t.getEndTime());
            v.duration_time.setText("车程:"+t.getDuration());
            v.seat_detaile.setText(t.getSeatInfo());
        }

        @Override
        public int getItemCount() {
            return lst.size();
        }
        class TrainView extends RecyclerView.ViewHolder{
            TextView txt_checi,start_place,end_place,train_type,start_time,end_time,duration_time,seat_detaile;

            public TrainView(View itemView) {
                super(itemView);
                txt_checi= (TextView) itemView.findViewById(R.id.txt_checi);
                start_place= (TextView) itemView.findViewById(R.id.start_place);
                end_place= (TextView) itemView.findViewById(R.id.end_place);
                train_type= (TextView) itemView.findViewById(R.id.train_type);
                start_time= (TextView) itemView.findViewById(R.id.start_time);
                end_time= (TextView) itemView.findViewById(R.id.end_time);
                duration_time= (TextView) itemView.findViewById(R.id.duration_time);
                seat_detaile= (TextView) itemView.findViewById(R.id.seat_detaile);
            }
        }
    }

}
