package com.sanved.attendancecal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Sanved on 14-04-2018.
 */

public class StartScreen extends AppCompatActivity{

    Button enter, see;
    String strName="";
    TextView welcome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.start_screen);

        enter = findViewById(R.id.bAttn);
        see = findViewById(R.id.bSee);
        welcome = findViewById(R.id.tvWel);

        if(savedInstanceState == null){
            Bundle bundle = getIntent().getExtras();
            if(bundle != null){
                strName = bundle.getString("user");
            }
        }else{
            strName = (String) savedInstanceState.getSerializable("user");
        }

        welcome.setText("Hello " + strName);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                Intent intent = new Intent(StartScreen.this, EnterAttendance.class);
                intent.putExtra("user", strName);
                startActivity(intent);
            }
        });

        see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartScreen.this, SeeAttendance.class);
                intent.putExtra("user", strName);
                startActivity(intent);
            }
        });

    }
}
