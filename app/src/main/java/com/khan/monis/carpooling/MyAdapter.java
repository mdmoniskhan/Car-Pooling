package com.khan.monis.carpooling;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<RidesInfo> ridesInfoList;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        protected TextView carName;
        protected TextView carNum;
        protected TextView totalSeats;
        protected TextView availSeats;

        public MyViewHolder(View v) {
            super(v);

            carName = v.findViewById(R.id.carName);
            carNum = v.findViewById(R.id.carNum);
            totalSeats = v.findViewById(R.id.totalSeats);
            availSeats = v.findViewById(R.id.availSeats);
        }
    }

    public MyAdapter(List<RidesInfo> ridesInfoList) {
        this.ridesInfoList = ridesInfoList;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ride, parent, false);

        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        RidesInfo ri = ridesInfoList.get(position);
        holder.carName.setText(ri.carName);
        holder.carNum.setText(ri.carNum);
        holder.totalSeats.setText(ri.totalSeats);
        holder.availSeats.setText(ri.availSeats);

    }

    @Override
    public int getItemCount() {
        return ridesInfoList.size();
    }
}

class RidesInfo{

    protected String carName;
    protected String carNum;
    protected String totalSeats;
    protected String availSeats;
}