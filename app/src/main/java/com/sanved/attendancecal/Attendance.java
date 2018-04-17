package com.sanved.attendancecal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sanved on 15-04-2018.
 */

public class Attendance extends AppCompatActivity {

    TextView tv, tvUser;
    String strName = "", year = "", student = "";
    String[] years = {"", "2016", "2017", "2018"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendance);

        tvUser = findViewById(R.id.tvWel);
        tv = findViewById(R.id.tvStudent);

        if (savedInstanceState == null) {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                strName = bundle.getString("user");
                year = bundle.getString("year");
                student = bundle.getString("student");
            }
        } else {
            strName = (String) savedInstanceState.getSerializable("user");
            year = (String) savedInstanceState.getSerializable("year");
            student = (String) savedInstanceState.getSerializable("student");
        }

        tv.setText("");
        tvUser.setText("Hello - " + strName);

        int test = Integer.parseInt(student);

        if (test == 69)
            getAttendance2();
        else
            getAttendance();

    }

    public void getAttendance2() {

        String url = "http://tapkeer.com/attn/student_all.php";

        RequestQueue queue = Volley.newRequestQueue(Attendance.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int test = 69, present = 0, absent = 0;
                Log.i("My success", "" + response);
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject reader = array.getJSONObject(i);
                        String name, day, month, year, s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12;
                        //name = reader.getString("name");
                        day = reader.getString("day");
                        year = reader.getString("year");
                        month = reader.getString("month");
                        s1 = reader.getString("s1");
                        s2 = reader.getString("s2");
                        s3 = reader.getString("s3");
                        s4 = reader.getString("s4");
                        s5 = reader.getString("s5");
                        s6 = reader.getString("s6");
                        s7 = reader.getString("s7");
                        s8 = reader.getString("s8");
                        s9 = reader.getString("s9");
                        s10 = reader.getString("s10");
                        s11 = reader.getString("s11");
                        s12 = reader.getString("s12");
                        String text = day + "/" + month + "/" + year
                                + " - S1 - " + s1
                                + ",S2 - " + s2
                                + ",S3 - " + s3
                                + ",S4 - " + s4
                                + ",S5 - " + s5
                                + ",S6 - " + s6
                                + ",S7 - " + s7
                                + ",S8 - " + s8
                                + ",S9 - " + s9
                                + ",S10 - " + s10
                                + ",S11 - " + s11
                                + ",S12 - " + s12;
                        tv.setText(text);
                    }
                } catch (Exception e) {
                    Toast.makeText(Attendance.this, "Data error 2", Toast.LENGTH_LONG).show();
                    Log.e("My error", e.toString());
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Attendance.this, "my error :" + error, Toast.LENGTH_LONG).show();
                Log.i("My error", "" + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<String, String>();
                map.put("year", "" + years[Integer.parseInt(year)]);
                map.put("student", student);

                return map;
            }
        };
        queue.add(request);

    }

    public void getAttendance() {

        String url = "http://tapkeer.com/attn/student.php";

        RequestQueue queue = Volley.newRequestQueue(Attendance.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int test = 69, present = 0, absent = 0;
                Log.i("My success", "" + response);

                try {
                    JSONObject reader = new JSONObject(response);
                    test = reader.getInt("result");
                } catch (Exception e) {
                    Log.e("", e.toString());
                }

                if (test == 1) {
                    try {
                        JSONObject reader = new JSONObject(response);
                        present = reader.getInt("present");
                        absent = reader.getInt("absent");
                        int total = present + absent;
                        int percent = (present * 100) / total;
                        String text = "Student no - " + student + "\n"
                                + "Total lectures - " + total + "\n"
                                + "Present - " + present + "\n"
                                + "Absent - " + absent + "\n"
                                + "Percent - " + percent + "%";
                        tv.setText(text);
                    } catch (Exception e) {
                        Toast.makeText(Attendance.this, "Data error 1", Toast.LENGTH_LONG).show();
                        Log.i("My error", "");
                    }
                } else if (test == 0) {
                    Toast.makeText(Attendance.this, "Server error", Toast.LENGTH_LONG).show();
                    Log.i("My error", "");
                } else {
                    Toast.makeText(Attendance.this, "System error", Toast.LENGTH_LONG).show();
                    Log.i("My error", "");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Attendance.this, "my error :" + error, Toast.LENGTH_LONG).show();
                Log.i("My error", "" + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<String, String>();
                map.put("year", "" + years[Integer.parseInt(year)]);
                map.put("student", student);

                return map;
            }
        };
        queue.add(request);

    }
}
