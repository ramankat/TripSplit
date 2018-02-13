package com.vendoor.apps.tripsplit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dell on 2/13/2018.
 */

public class PeopleAdapter extends BaseAdapter {
    ArrayList<Person> personArrayList;
    Context c;

    public PeopleAdapter(ArrayList<Person> personArrayList, Context c) {
        this.personArrayList = personArrayList;
        this.c = c;
    }

    @Override
    public int getCount() {
        return personArrayList.size();
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
            v = l.inflate(R.layout.people_list, parent, false);
            viewHolder=new ViewHolder();
            viewHolder.name= (TextView) v.findViewById(R.id.name);
            viewHolder.invest= (TextView) v.findViewById(R.id.invest);
            viewHolder.pending= (TextView) v.findViewById(R.id.pending);
            v.setTag(viewHolder);
        }
        else {
            v=convertView;
            viewHolder=(ViewHolder)v.getTag();
        }
        //TextView name,age,course;
        Person s=  personArrayList.get(position);
        viewHolder.name.setText(s.getName());
        viewHolder.invest.setText(s.getInvest());
        viewHolder.pending.setText(""+s.getPending());
        return v;

    }
    static class ViewHolder{
        TextView name,invest,pending;
    }
}
