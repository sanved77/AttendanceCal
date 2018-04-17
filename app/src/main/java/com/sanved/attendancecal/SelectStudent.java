package com.sanved.attendancecal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

/**
 * Created by Sanved on 15-04-2018.
 */

public class SelectStudent extends AppCompatActivity implements View.OnClickListener{

    CardView c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12;
    String strName = "";
    String year = "";

    Button all;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_student);

        if(savedInstanceState == null){
            Bundle bundle = getIntent().getExtras();
            if(bundle != null){
                strName = bundle.getString("user");
                year = bundle.getString("year");
            }
        }else{
            strName = (String) savedInstanceState.getSerializable("user");
            year = (String) savedInstanceState.getSerializable("year");
        }

        all = findViewById(R.id.bAll);

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

        all.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(SelectStudent.this, Attendance.class);
        intent.putExtra("user", strName);
        intent.putExtra("year", year);

        switch(view.getId()){

            case R.id.cv1:
                intent.putExtra("student", ""+1);
                break;

            case R.id.cv2:
                intent.putExtra("student", ""+2);
                break;

            case R.id.cv3:
                intent.putExtra("student", ""+3);
                break;

            case R.id.cv4:
                intent.putExtra("student", ""+4);
                break;

            case R.id.cv5:
                intent.putExtra("student", ""+5);
                break;

            case R.id.cv6:
                intent.putExtra("student", ""+6);
                break;

            case R.id.cv7:
                intent.putExtra("student", ""+7);
                break;

            case R.id.cv8:
                intent.putExtra("student", ""+8);
                break;

            case R.id.cv9:
                intent.putExtra("student", ""+9);
                break;

            case R.id.cv10:
                intent.putExtra("student", ""+10);
                break;

            case R.id.cv11:
                intent.putExtra("student", ""+11);
                break;

            case R.id.cv12:
                intent.putExtra("student", ""+12);
                break;

            case R.id.bAll:
                intent.putExtra("student", ""+69);
                break;
        }

        startActivity(intent);

    }
}
