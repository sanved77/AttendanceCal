package com.sanved.attendancecal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Sanved on 14-04-2018.
 */

public class EnterAttendance extends AppCompatActivity implements View.OnClickListener, com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener {

    Button date, save;
    TextView tvdate, result;
    CardView c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12;
    static int day, month, year;
    String strName="";
    int[] flags;
    String[] ledger;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter);

        if(savedInstanceState == null){
            Bundle bundle = getIntent().getExtras();
            if(bundle != null){
                strName = bundle.getString("user");
            }
        }else{
            strName = (String) savedInstanceState.getSerializable("user");
        }

        initVals();

    }

    public void initVals(){

        date = findViewById(R.id.bDate);
        save = findViewById(R.id.bSave);

        tvdate = findViewById(R.id.tvDate);
        result = findViewById(R.id.tvResult);

        c1 = findViewById(R.id.cv1);
        c2 = findViewById(R.id.cv2);
        c3 = findViewById(R.id.cv3);
        c4 = findViewById(R.id.cv4);
        c5 = findViewById(R.id.cv5);
        c6 = findViewById(R.id.cv6);
        c7 = findViewById(R.id.cv7);
        c8 = findViewById(R.id.cv8);
        c9 = findViewById(R.id.cv9);
        c10 = findViewById(R.id.cv10);
        c11 = findViewById(R.id.cv11);
        c12 = findViewById(R.id.cv12);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        EnterAttendance.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(), "Datepickerdialog");

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterAttendance();
            }
        });

        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        c3.setOnClickListener(this);
        c4.setOnClickListener(this);
        c5.setOnClickListener(this);
        c6.setOnClickListener(this);
        c7.setOnClickListener(this);
        c8.setOnClickListener(this);
        c9.setOnClickListener(this);
        c10.setOnClickListener(this);
        c11.setOnClickListener(this);
        c12.setOnClickListener(this);

        flags = new int[12];
        ledger = new String[12];

    }

    public void enterAttendance(){
        String url = "http://tapkeer.com/attn/enter.php";

        fillLedger();

        RequestQueue queue = Volley.newRequestQueue(EnterAttendance.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int test = 69;
                Log.i("My success",""+response);

                try {
                    JSONObject reader = new JSONObject(response);
                    test = reader.getInt("result");
                }catch(Exception e){
                    Log.e("",e.toString());
                }

                if(test == 1){
                    result.setTextColor(ContextCompat.getColor(EnterAttendance.this, R.color.black));
                    result.setText("Data entered");

                }else{
                    result.setTextColor(ContextCompat.getColor(EnterAttendance.this, R.color.red));
                    result.setText("Error Occurred");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(EnterAttendance.this, "my error :"+error, Toast.LENGTH_LONG).show();
                Log.i("My error",""+error);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> map = new HashMap<String, String>();
                map.put("name", ""+strName);
                map.put("day", ""+day);
                map.put("month", ""+month);
                map.put("year", ""+year);
                map.put("1", ledger[0]);
                map.put("2", ledger[1]);
                map.put("3", ledger[2]);
                map.put("4", ledger[3]);
                map.put("5", ledger[4]);
                map.put("6", ledger[5]);
                map.put("7", ledger[6]);
                map.put("8", ledger[7]);
                map.put("9", ledger[8]);
                map.put("10", ledger[9]);
                map.put("11", ledger[10]);
                map.put("12", ledger[11]);

                return map;
            }
        };
        queue.add(request);
    }

    public void fillLedger(){
        for(int i =0; i < 12; i ++){

            if(flags[i] == 0) // Present
                ledger[i] = "P";
            if(flags[i] == 1) // Absent
                ledger[i] = "A";

        }
    }

    @Override
    public void onClick(View view) {

        Log.e("",""+flags);

        switch(view.getId()){

            case R.id.cv1:
                if(flags[0] == 0) {
                    c1.setCardBackgroundColor(ContextCompat.getColor(this, R.color.red));
                    flags[0] = 1;
                }
                else {
                    c1.setCardBackgroundColor(ContextCompat.getColor(this, R.color.green));
                    flags[0] = 0;
                }
                break;

            case R.id.cv2:
                if(flags[1] == 0) {
                    c2.setCardBackgroundColor(ContextCompat.getColor(this, R.color.red));
                    flags[1] = 1;
                }
                else {
                    c2.setCardBackgroundColor(ContextCompat.getColor(this, R.color.green));
                    flags[1] = 0;
                }
                break;

            case R.id.cv3:
                if(flags[2] == 0) {
                    c3.setCardBackgroundColor(ContextCompat.getColor(this, R.color.red));
                    flags[2] = 1;
                }
                else {
                    c3.setCardBackgroundColor(ContextCompat.getColor(this, R.color.green));
                    flags[2] = 0;
                }
                break;

            case R.id.cv4:
                if(flags[3] == 0) {
                    c4.setCardBackgroundColor(ContextCompat.getColor(this, R.color.red));
                    flags[3] = 1;
                }
                else {
                    c4.setCardBackgroundColor(ContextCompat.getColor(this, R.color.green));
                    flags[3] = 0;
                }
                break;

            case R.id.cv5:
                if(flags[4] == 0) {
                    c5.setCardBackgroundColor(ContextCompat.getColor(this, R.color.red));
                    flags[4] = 1;
                }
                else {
                    c5.setCardBackgroundColor(ContextCompat.getColor(this, R.color.green));
                    flags[4] = 0;
                }
                break;

            case R.id.cv6:
                if(flags[5] == 0) {
                    c6.setCardBackgroundColor(ContextCompat.getColor(this, R.color.red));
                    flags[5] = 1;
                }
                else {
                    c6.setCardBackgroundColor(ContextCompat.getColor(this, R.color.green));
                    flags[5] = 0;
                }
                break;

            case R.id.cv7:
                if(flags[6] == 0) {
                    c7.setCardBackgroundColor(ContextCompat.getColor(this, R.color.red));
                    flags[6] = 1;
                }
                else {
                    c7.setCardBackgroundColor(ContextCompat.getColor(this, R.color.green));
                    flags[6] = 0;
                }
                break;

            case R.id.cv8:
                if(flags[7] == 0) {
                    c8.setCardBackgroundColor(ContextCompat.getColor(this, R.color.red));
                    flags[7] = 1;
                }
                else {
                    c8.setCardBackgroundColor(ContextCompat.getColor(this, R.color.green));
                    flags[7] = 0;
                }
                break;

            case R.id.cv9:
                if(flags[8] == 0) {
                    c9.setCardBackgroundColor(ContextCompat.getColor(this, R.color.red));
                    flags[8] = 1;
                }
                else {
                    c9.setCardBackgroundColor(ContextCompat.getColor(this, R.color.green));
                    flags[8] = 0;
                }
                break;

            case R.id.cv10:
                if(flags[9] == 0) {
                    c10.setCardBackgroundColor(ContextCompat.getColor(this, R.color.red));
                    flags[9] = 1;
                }
                else {
                    c10.setCardBackgroundColor(ContextCompat.getColor(this, R.color.green));
                    flags[9] = 0;
                }
                break;

            case R.id.cv11:
                if(flags[10] == 0) {
                    c11.setCardBackgroundColor(ContextCompat.getColor(this, R.color.red));
                    flags[10] = 1;
                }
                else {
                    c11.setCardBackgroundColor(ContextCompat.getColor(this, R.color.green));
                    flags[10] = 0;
                }
                break;

            case R.id.cv12:
                if(flags[11] == 0) {
                    c12.setCardBackgroundColor(ContextCompat.getColor(this, R.color.red));
                    flags[11] = 1;
                }
                else {
                    c12.setCardBackgroundColor(ContextCompat.getColor(this, R.color.green));
                    flags[11] = 0;
                }
                break;


        }

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

        day = dayOfMonth;
        month = monthOfYear + 1;
        this.year = year;

        tvdate.setText(dayOfMonth + "/" + month + "/" + year);

        save.setVisibility(View.VISIBLE);

    }
}
