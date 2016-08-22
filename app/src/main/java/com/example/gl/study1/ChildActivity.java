package com.example.gl.study1;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ChildActivity extends AppCompatActivity {
    Button BTN_logout;
    ListView LV_project;
    List<Project> ProjectList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);
        Toast.makeText(ChildActivity.this, "Login Success!", Toast.LENGTH_LONG).show();
        final Project project=new Project("1","projectnasd",30);
        final Project project1=new Project("1","projectnasd",30);
        final Project project2=new Project("1","projectnasd",30);
        final TextView TV_Position = (TextView) findViewById(R.id.lv_list_project_position);
        LV_project = (ListView) findViewById(R.id.lv_list_project_items);
        BTN_logout = (Button) findViewById(R.id.btn_logout);
        ProjectList.add(project);
        ProjectList.add(project1);
        ProjectList.add(project2);
        ListProjectAdapter adapter = new ListProjectAdapter(ChildActivity.this, R.layout.list_project_layout, ProjectList);
        LV_project.setAdapter(adapter);
//        LV_project.setOnClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//                TV_Position.setText("position :" + arg2 + " ; value =" + arr[arg2]);
//            }
//        });
//        LV_project.setOnItemClickListener(
//                new AdapterView.OnItemClickListener() {
//                    public void onItemClick(AdapterView<?> arg0,
//                                            View arg1,
//                                            int arg2,
//                                            long arg3) {
//                        TV_Position.setText("position :"+arg2+" ; value ="+arr[arg2]);
//                    }
//                });
        BTN_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
