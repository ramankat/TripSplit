package com.vendoor.apps.tripsplit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dell on 2/12/2018.
 */

public class TripAdapter extends BaseAdapter {
    ArrayList<Trips> tripsArrayList ;
    Context c;

    public TripAdapter(ArrayList<Trips> tripsArrayList, Context c) {
        this.tripsArrayList = tripsArrayList;
        this.c = c;
    }

    @Override
    public int getCount() {
        return tripsArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater l= LayoutInflater.from(c);
        View v;
        ViewHolder viewHolder;
        if(convertView==null) {
            v = l.inflate(R.layout.listview, parent, false);
            viewHolder=new ViewHolder();
            viewHolder.tripName= (TextView) v.findViewById(R.id.textTN);
            viewHolder.tripDestination= (TextView) v.findViewById(R.id.textTD);
            viewHolder.todate= (TextView) v.findViewById(R.id.textTDT);
            viewHolder.endDate= (TextView) v.findViewById(R.id.textTED);
            v.setTag(viewHolder);
        }
        else {
            v=convertView;
            viewHolder=(ViewHolder)v.getTag();
        }
        //TextView name,age,course;
        Trips s=  tripsArrayList.get(position);
        viewHolder.tripName.setText(s.getTripName());
        viewHolder.tripDestination.setText(s.getDestination());
        viewHolder.todate.setText(s.getToDate());
        viewHolder.endDate.setText(s.getEndDate());
        return v;

    }
    static class ViewHolder{
        TextView tripName,tripDestination,todate,endDate;
    }
    }
