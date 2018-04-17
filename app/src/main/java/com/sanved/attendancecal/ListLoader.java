package com.sanved.attendancecal;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sanved on 17-04-2018.
 */

public class ListLoader extends AppCompatActivity {

    RecyclerView rv;
    Toolbar toolbar;

    RVAdapt adapt;

    ArrayList<DataKaRakhwala> list;

    String strName = "", year = "", student = "";
    String[] years = {"", "2016", "2017", "2018"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_loader);

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

        rv = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setTitle("Attendance report");
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white_36dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        list = new ArrayList<>();

        getAttendance2();

    }

    public void getAttendance2() {

        String url = "http://tapkeer.com/attn/student_all.php";

        RequestQueue queue = Volley.newRequestQueue(ListLoader.this);
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

                        list.add(new DataKaRakhwala(day, month, year, s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12));

                    }
                } catch (Exception e) {
                    Toast.makeText(ListLoader.this, "Data error 2", Toast.LENGTH_LONG).show();
                    Log.e("My error", e.toString());
                }

                Context con = ListLoader.this.getApplication();
                adapt = new RVAdapt(list, con);

                rv.setAdapter(adapt);
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(ListLoader.this, "my error :" + error, Toast.LENGTH_LONG).show();
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
