package com.vendoor.apps.tripsplit;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class People extends AppCompatActivity {
ArrayList<Person> personArrayList=new ArrayList<>();
    AlertDialog alertDialog,alertDialog2;
    int i=0;
    RadioGroup radioGroup;
    RadioButton radioButton1,radioButton2;
    EditText editName,editInvest,another;
    SharedPreferences sharedPreferences;
    FloatingActionButton floatingActionButton;
    EditText amt;
    int k=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        sharedPreferences=getSharedPreferences("TripInfo",MODE_PRIVATE);
        final Integer x=getIntent().getIntExtra("pos",0);
        LinearLayout l2=(LinearLayout)getLayoutInflater().inflate(R.layout.pay_dialog,null);
        LinearLayout l3=(LinearLayout)getLayoutInflater().inflate(R.layout.custom_title2,null);
        LinearLayout l4=(LinearLayout)getLayoutInflater().inflate(R.layout.custom_title3,null);
        amt=(EditText)l2.findViewById(R.id.amt);
        radioButton1=(RadioButton)l2.findViewById(R.id.radio1);
        radioButton2=(RadioButton)l2.findViewById(R.id.radio2);
        another=(EditText)l2.findViewById(R.id.another);
        i=0;
        while(!sharedPreferences.getString("name"+x+""+i,"").equals(""))
        {
            personArrayList.add(new Person(sharedPreferences.getString("name"+x+""+i,""),sharedPreferences.getString("invest"+x+""+i,""),sharedPreferences.getInt("pending"+x+""+i,0)));
            i++;
        }
        floatingActionButton=(FloatingActionButton)findViewById(R.id.addperson);
        LinearLayout l=(LinearLayout)getLayoutInflater().inflate(R.layout.person_dialog,null);
        editName=(EditText)l.findViewById(R.id.pname);
        editInvest=(EditText)l.findViewById(R.id.pinvest);
        ListView listView=(ListView)findViewById(R.id.peopleList);
        final PeopleAdapter peopleAdapter=new PeopleAdapter(personArrayList,this);
        listView.setAdapter(peopleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                k=position;
                alertDialog2.show();
            }
        });
        alertDialog=new AlertDialog.Builder(this)
                .setView(l).setCustomTitle(l3).setCancelable(false)
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        //editor.putString("trip"+ i,editTripN.getText().toString()+"."+editDestination.getText().toString()+"."+todate.getText().toString()+"."+endDate.getText().toString()+"/");
                        editor.putString("name"+x+""+i,editName.getText().toString());
                        editor.putString("invest"+x+""+ i,editInvest.getText().toString());
                        editor.putInt("pending"+x+""+ i,0);
                        editor.commit();
                        personArrayList.add(new Person(editName.getText().toString(),editInvest.getText().toString(),0));
                        peopleAdapter.notifyDataSetChanged();
                        i++;
                        //editor.putString()
                        editName.setText("");
                        editInvest.setText("");

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editName.setText("");
                        editInvest.setText("");
                        alertDialog.dismiss();
                    }
                })
                .create();
        alertDialog2=new AlertDialog.Builder(this)
                .setView(l2).setCustomTitle(l4)
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        String ano=another.getText().toString();
                        int d=0;
                        i=0;
                        while(!sharedPreferences.getString("name"+""+x+""+i,"").equals(""))
                        {
                            if(sharedPreferences.getString("name"+x+""+i,"").equals(ano))
                            {
                                d=i;
                            }
                            i++;
                        }
                        if(radioButton1.isChecked())
                        {
                            int b=Integer.parseInt(amt.getText().toString());
                            int p=sharedPreferences.getInt("pending"+x+""+k,0);
                            int q=sharedPreferences.getInt("pending"+x+""+d,0);
                            editor.putInt("pending"+x+""+k,p-b);
                            editor.putInt("pending"+x+""+d,q+b);
                            radioButton1.setChecked(false);
                            editor.commit();
                        }
                        if(radioButton2.isChecked())
                        {
                            int b=Integer.parseInt(amt.getText().toString());
                            int p=sharedPreferences.getInt("pending"+x+""+k,0);
                            int q=sharedPreferences.getInt("pending"+x+""+d,0);
                            editor.putInt("pending"+x+""+k,p+b);
                            editor.putInt("pending"+x+""+d,q-b);
                            radioButton2.setChecked(false);
                            editor.commit();
                        }
                        else
                        {
                        }
                        personArrayList.clear();
                        i=0;
                        while(!sharedPreferences.getString("name"+x+""+i,"").equals(""))
                        {
                            personArrayList.add(new Person(sharedPreferences.getString("name"+x+""+i,""),sharedPreferences.getString("invest"+x+""+i,""),sharedPreferences.getInt("pending"+x+""+i,0)));
                            i++;
                        }
                        peopleAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog2.dismiss();
                    }
                })
                .create();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.show();
            }
        });
    }
}
