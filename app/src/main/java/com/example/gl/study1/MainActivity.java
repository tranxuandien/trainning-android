package com.example.gl.study1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button BTN_list_project, BTN_create_project,BTN_test_frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        BTN_list_project = (Button) findViewById(R.id.btn_list_project);
        BTN_create_project = (Button) findViewById(R.id.btn_create_project);
        BTN_test_frag= (Button) findViewById(R.id.btn_test_frag);
        BTN_list_project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(MainActivity.this, ListProjectActivity.class);
                startActivity(newIntent);
            }
        });
        BTN_create_project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createProjectIntent= new Intent(MainActivity.this,CreateProjectActivity.class);
                startActivity(createProjectIntent);
            }
        });
        BTN_test_frag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent testFrag=new Intent(MainActivity.this,Frag.class);
                startActivity(testFrag);
            }
        });
    }
}
