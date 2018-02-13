package com.vendoor.apps.tripsplit;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker;
import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ArrayList<Trips> tripsArrayList=new ArrayList<>();
    public SharedPreferences sharedPreferences;
    private int date1,year1,date2,year2;
    private String month1,month2;
    private static int hour1,minute1,hour2,minute2;
    FloatingActionButton addfab;
    AlertDialog alertDialog;
    ListView listView;
    EditText editDestination,editTripN;
    TextView endDate,todate;
    private long alarmTime=0;
    int i=0;
    private SingleDateAndTimePickerDialog singleDateAndTimePickerDialog,singleDateAndTimePickerDialog2;
    TextView datex1,datex2,timex1,timex2;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.credts:
                startActivity(new Intent(MainActivity.this,Credits.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences=getSharedPreferences("TripInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        i=0;
        while(!sharedPreferences.getString("tripN"+i,"").equals(""))
        {
            tripsArrayList.add(new Trips(sharedPreferences.getString("tripN"+i,""),sharedPreferences.getString("tripD"+i,""),sharedPreferences.getString("tripT"+i,""),sharedPreferences.getString("tripE"+i,"")));
            i++;
        }
        LinearLayout l1=(LinearLayout)getLayoutInflater().inflate(R.layout.trip_dialog,null);
        LinearLayout l2=(LinearLayout)getLayoutInflater().inflate(R.layout.custom_title1,null);
        editTripN=(EditText)l1.findViewById(R.id.tripN);
        editDestination=(EditText)l1.findViewById(R.id.destination);
        todate=(TextView)l1.findViewById(R.id.toDate);
        endDate=(TextView)l1.findViewById(R.id.endDate);
        addfab=(FloatingActionButton)findViewById(R.id.addfab);
        datex1=(TextView)findViewById(R.id.datex1);
        datex2=(TextView)findViewById(R.id.datex2);
        timex1=(TextView)findViewById(R.id.timex1);
        timex2=(TextView)findViewById(R.id.timex2);
        listView=(ListView)findViewById(R.id.listView);
        final TripAdapter tripAdapter=new TripAdapter(tripsArrayList,this);
        listView.setAdapter(tripAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this,People.class).putExtra("pos",position);
                startActivity(intent);
            }
        });
        todate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singleDateAndTimePickerDialog.display();
                alertDialog.dismiss();
            }
        });
        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singleDateAndTimePickerDialog2.display();
                alertDialog.dismiss();
            }
        });
        singleDateAndTimePickerDialog=new SingleDateAndTimePickerDialog.Builder(this)
                .curved()
                .minutesStep(1)
                .defaultDate(Calendar.getInstance().getTime())
                .titleTextColor(Color.WHITE)
                .mainColor(Color.rgb(255,140,0))
                .displayListener(new SingleDateAndTimePickerDialog.DisplayListener() {
                    @Override
                    public void onDisplayed(SingleDateAndTimePicker picker) {

                    }
                })
                .title("Pick Time")
                .listener(new SingleDateAndTimePickerDialog.Listener() {
                    @Override
                    public void onDateSelected(Date date) {
                        alarmTime = date.getTime();
                        if(alarmTime - System.currentTimeMillis() > 0) {
                            String test = date.toString().replace("Mon", "").replace("Tue", "").replace("Wed", "").replace("Thu", "")
                                    .replace("Mon", "").replace("Fri", "").replace("Sat", "").replace("Sun", "")
                                    .replace("GMT+05:30", "");
                            hour1 = Integer.parseInt(String.valueOf(test.charAt(8)) + String.valueOf(test.charAt(9)));
                            minute1 = Integer.parseInt(String.valueOf(test.charAt(11)) + String.valueOf(test.charAt(12)));
                            date1 = Integer.parseInt(String.valueOf(test.charAt(5)) + String.valueOf(test.charAt(6)));
                            month1 = String.valueOf(test.charAt(1)) + String.valueOf(test.charAt(2)) + String.valueOf(test.charAt(3));
                            year1 = Integer.parseInt(String.valueOf(test.charAt(18)) + String.valueOf(test.charAt(19)) + String.valueOf(test.charAt(20)) + String.valueOf(test.charAt(21)));
                            datex1.setText(date1 + " " + month1 + " " + year1);
                            if (hour1 < 10) {
                                if (minute1 < 10) {
                                    timex1.setText("0" + hour1 + ":0" + minute1);
                                } else {
                                    timex1.setText("0" + hour1 + ":" + minute1);
                                }
                            } else {
                                if (minute1 < 10) {
                                    timex1.setText(hour1 + ":0" + minute1);
                                } else {
                                    timex1.setText(hour1 + ":" + minute1);
                                }
                            }
                            Log.e("TAG", "onDateSelected: " + test);
                            todate.setText(datex1.getText()+"     "+timex1.getText());
                            alertDialog.show();
                        }

                        else
                            Toast.makeText(getBaseContext(),"Must be a valid date time",Toast.LENGTH_SHORT).show();
                    }

                }).build();
        singleDateAndTimePickerDialog2=new SingleDateAndTimePickerDialog.Builder(this)
                .curved()
                .minutesStep(1)
                .defaultDate(Calendar.getInstance().getTime())
                .titleTextColor(Color.WHITE)
                .mainColor(Color.rgb(255,140,0))
                .displayListener(new SingleDateAndTimePickerDialog.DisplayListener() {
                    @Override
                    public void onDisplayed(SingleDateAndTimePicker picker) {

                    }
                })
                .title("Pick Time")
                .listener(new SingleDateAndTimePickerDialog.Listener() {
                    @Override
                    public void onDateSelected(Date date) {
                        alarmTime = date.getTime();
                        if(alarmTime - System.currentTimeMillis() > 0) {
                            String test = date.toString().replace("Mon", "").replace("Tue", "").replace("Wed", "").replace("Thu", "")
                                    .replace("Mon", "").replace("Fri", "").replace("Sat", "").replace("Sun", "")
                                    .replace("GMT+05:30", "");
                            hour2 = Integer.parseInt(String.valueOf(test.charAt(8)) + String.valueOf(test.charAt(9)));
                            minute2 = Integer.parseInt(String.valueOf(test.charAt(11)) + String.valueOf(test.charAt(12)));
                            date2 = Integer.parseInt(String.valueOf(test.charAt(5)) + String.valueOf(test.charAt(6)));
                            month2 = String.valueOf(test.charAt(1)) + String.valueOf(test.charAt(2)) + String.valueOf(test.charAt(3));
                            year2 = Integer.parseInt(String.valueOf(test.charAt(18)) + String.valueOf(test.charAt(19)) + String.valueOf(test.charAt(20)) + String.valueOf(test.charAt(21)));
                            datex2.setText(date2 + " " + month2 + " " + year2);
                            if (hour2 < 10) {
                                if (minute2 < 10) {
                                    timex2.setText("0" + hour2 + ":0" + minute2);
                                } else {
                                    timex2.setText("0" + hour2 + ":" + minute2);
                                }
                            } else {
                                if (minute2 < 10) {
                                    timex2.setText(hour2 + ":0" + minute2);
                                } else {
                                    timex2.setText(hour2 + ":" + minute2);
                                }
                            }
                            Log.e("TAG", "onDateSelected: " + test);
                            endDate.setText(datex2.getText()+"     "+timex2.getText());
                            alertDialog.show();
                        }

                        else
                            Toast.makeText(getBaseContext(),"Must be a valid date time",Toast.LENGTH_SHORT).show();
                    }
                }).build();
        final int finalI = i;
        alertDialog=new AlertDialog.Builder(this)
                .setView(l1).setCustomTitle(l2)
                .setCancelable(false)
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        //editor.putString("trip"+ i,editTripN.getText().toString()+"."+editDestination.getText().toString()+"."+todate.getText().toString()+"."+endDate.getText().toString()+"/");
                        editor.putString("tripN"+ i,editTripN.getText().toString());
                        editor.putString("tripD"+ i,editDestination.getText().toString());
                        editor.putString("tripT"+ i,todate.getText().toString());
                        editor.putString("tripE"+ i,endDate.getText().toString());
                        editor.commit();
                        tripsArrayList.add(new Trips(editTripN.getText().toString(),editDestination.getText().toString(),todate.getText().toString(),endDate.getText().toString()));
                        tripAdapter.notifyDataSetChanged();
                        i++;
                        //editor.putString()
                        editTripN.setText("");
                        editDestination.setText("");
                        todate.setText("");
                        endDate.setText("");
                        datex1.setText("");
                        datex2.setText("");
                        timex1.setText("");
                        timex2.setText("");
                        todate.setText("Select Start Date");
                        endDate.setText("Select End Date");

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editTripN.setText("");
                        editDestination.setText("");
                        todate.setText("");
                        endDate.setText("");
                        datex1.setText("");
                        datex2.setText("");
                        timex1.setText("");
                        timex2.setText("");
                        todate.setText("Select Start Date");
                        endDate.setText("Select End Date");
                        alertDialog.dismiss();
                    }
                })
                .create();
        addfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.show();
            }
        });

    }
}
