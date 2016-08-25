package com.example.gl.study1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class ProjectDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_detail);
        TextView TV_ProjectDetail=(TextView)findViewById(R.id.tv_project_detail);
//        Project ProjectObject=(Project) getIntent().getSerializableExtra("project");
        String ProjectObject= getIntent().getStringExtra("project");
        TV_ProjectDetail.setText(ProjectObject);
    }
}
