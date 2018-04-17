package com.sanved.attendancecal;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sanved on 17-04-2018.
 */

public class RVAdapt extends RecyclerView.Adapter<RVAdapt.DataHolder> {

    ArrayList<DataKaRakhwala> list;
    static ArrayList<DataKaRakhwala> list2;

    Context context;

    RVAdapt(ArrayList<DataKaRakhwala> list, Context context) {
        this.list = list;
        list2 = list;
        this.context = context;
    }

    public static class DataHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvPics, tvlists1, tvlists2, tvlists3, tvlists4, tvlists5, tvlists6, tvlists7, tvlists8, tvlists9, tvlists10, tvlists11, tvlists12;
        CardView cv;

        DataHolder(final View v) {
            super(v);
            tvName = (TextView) v.findViewById(R.id.tvName);
            tvPics = (TextView) v.findViewById(R.id.tvTotal);
            tvlists1 = (TextView) v.findViewById(R.id.tvlists1);
            tvlists2 = (TextView) v.findViewById(R.id.tvlists2);
            tvlists3 = (TextView) v.findViewById(R.id.tvlists3);
            tvlists4 = (TextView) v.findViewById(R.id.tvlists4);
            tvlists5 = (TextView) v.findViewById(R.id.tvlists5);
            tvlists6 = (TextView) v.findViewById(R.id.tvlists6);
            tvlists7 = (TextView) v.findViewById(R.id.tvlists7);
            tvlists8 = (TextView) v.findViewById(R.id.tvlists8);
            tvlists9 = (TextView) v.findViewById(R.id.tvlists9);
            tvlists10 = (TextView) v.findViewById(R.id.tvlists10);
            tvlists11 = (TextView) v.findViewById(R.id.tvlists11);
            tvlists12 = (TextView) v.findViewById(R.id.tvlists12);
            cv = (CardView) v.findViewById(R.id.cvList);

        }

    }

    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        DataHolder dh = new DataHolder(v);
        return dh;
    }

    @Override
    public void onBindViewHolder(DataHolder holder, int position) {

        int present = 0, absent = 0;

        String text1 = list.get(position).getDay() + "/" + list.get(position).getMonth() + "/" + list.get(position).getYear();
        holder.tvName.setText(text1);

        if(list.get(position).getS1().equals("A")){
            absent++;
            holder.tvlists1.setBackgroundColor(ContextCompat.getColor(context, R.color.red));
            holder.tvlists1.setTextColor(ContextCompat.getColor(context, R.color.white));
        }
        if(list.get(position).getS2().equals("A")){
            absent++;
            holder.tvlists2.setBackgroundColor(ContextCompat.getColor(context, R.color.red));
            holder.tvlists2.setTextColor(ContextCompat.getColor(context, R.color.white));
        }
        if(list.get(position).getS3().equals("A")){
            absent++;
            holder.tvlists3.setBackgroundColor(ContextCompat.getColor(context, R.color.red));
            holder.tvlists3.setTextColor(ContextCompat.getColor(context, R.color.white));
        }
        if(list.get(position).getS4().equals("A")){
            absent++;
            holder.tvlists4.setBackgroundColor(ContextCompat.getColor(context, R.color.red));
            holder.tvlists4.setTextColor(ContextCompat.getColor(context, R.color.white));
        }
        if(list.get(position).getS5().equals("A")){
            absent++;
            holder.tvlists5.setBackgroundColor(ContextCompat.getColor(context, R.color.red));
            holder.tvlists5.setTextColor(ContextCompat.getColor(context, R.color.white));
        }
        if(list.get(position).getS6().equals("A")){
            absent++;
            holder.tvlists6.setBackgroundColor(ContextCompat.getColor(context, R.color.red));
            holder.tvlists6.setTextColor(ContextCompat.getColor(context, R.color.white));
        }
        if(list.get(position).getS7().equals("A")){
            absent++;
            holder.tvlists7.setBackgroundColor(ContextCompat.getColor(context, R.color.red));
            holder.tvlists7.setTextColor(ContextCompat.getColor(context, R.color.white));
        }
        if(list.get(position).getS8().equals("A")){
            absent++;
            holder.tvlists8.setBackgroundColor(ContextCompat.getColor(context, R.color.red));
            holder.tvlists8.setTextColor(ContextCompat.getColor(context, R.color.white));
        }
        if(list.get(position).getS9().equals("A")){
            absent++;
            holder.tvlists9.setBackgroundColor(ContextCompat.getColor(context, R.color.red));
            holder.tvlists9.setTextColor(ContextCompat.getColor(context, R.color.white));
        }
        if(list.get(position).getS10().equals("A")){
            absent++;
            holder.tvlists10.setBackgroundColor(ContextCompat.getColor(context, R.color.red));
            holder.tvlists10.setTextColor(ContextCompat.getColor(context, R.color.white));
        }
        if(list.get(position).getS11().equals("A")){
            absent++;
            holder.tvlists11.setBackgroundColor(ContextCompat.getColor(context, R.color.red));
            holder.tvlists11.setTextColor(ContextCompat.getColor(context, R.color.white));
        }
        if(list.get(position).getS12().equals("A")){
            absent++;
            holder.tvlists12.setBackgroundColor(ContextCompat.getColor(context, R.color.red));
            holder.tvlists12.setTextColor(ContextCompat.getColor(context, R.color.white));
        }


        present = 12 - absent;

        holder.tvPics.setText("Present - " + present + ", Absent - " + absent);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
