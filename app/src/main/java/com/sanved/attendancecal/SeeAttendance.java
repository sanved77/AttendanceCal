package com.sanved.attendancecal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Sanved on 15-04-2018.
 */

public class SeeAttendance extends AppCompatActivity implements View.OnClickListener{

    Button y1,y2,y3;
    String strName = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.see_attendance);

        if(savedInstanceState == null){
            Bundle bundle = getIntent().getExtras();
            if(bundle != null){
                strName = bundle.getString("user");
            }
        }else{
            strName = (String) savedInstanceState.getSerializable("user");
        }

        y1 = findViewById(R.id.bYear1);
        y2 = findViewById(R.id.bYear2);
        y3 = findViewById(R.id.bYear3);

        y1.setOnClickListener(this);
        y2.setOnClickListener(this);
        y3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(SeeAttendance.this, SelectStudent.class);
        intent.putExtra("user", strName);

        switch(view.getId()){

            case R.id.bYear1:
                intent.putExtra("year", ""+1);
                break;

            case R.id.bYear2:
                intent.putExtra("year", ""+2);
                break;

            case R.id.bYear3:
                intent.putExtra("year", ""+3);
                break;

        }

        startActivity(intent);
    }
}
