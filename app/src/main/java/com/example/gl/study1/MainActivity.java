package com.example.gl.study1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button BTN_list_project;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        BTN_list_project= (Button) findViewById(R.id.btn_list_project);
        BTN_list_project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent=new Intent(MainActivity.this,ListProjectActivity.class);
                startActivity(newIntent);
            }
        });
    }
}
