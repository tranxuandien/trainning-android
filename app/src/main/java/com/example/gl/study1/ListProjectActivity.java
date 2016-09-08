package com.example.gl.study1;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

public class ListProjectActivity extends AppCompatActivity {
    Button BTN_logout, BTN_show_all_project;
    ListView LV_project;
    List<Project> ProjectList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {

        }
        setContentView(R.layout.activity_child);
        Toast.makeText(ListProjectActivity.this, R.string.login_success, Toast.LENGTH_LONG).show();
        LV_project = (ListView) findViewById(R.id.lv_list_project_items);
        getAllProject();
    }

    private void getAllProject() {
        String token = "";
        //Get data lisst project from api
        SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        token=sharedpreferences.getString("token",null);
        RequestQueue queue = Volley.newRequestQueue(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            token = extras.getString("token");
        }
        String url = ProjectParams.ListProjectUrl+"?token=" + token;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                setData(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ListProjectActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        }
        );
        queue.add(stringRequest);
    }

    private void setData(String responseData) {
        //add project to List Project
        JSONArray projectArray;
        try {
            JSONObject json = (JSONObject) new JSONTokener(responseData).nextValue();
            projectArray = json.getJSONArray("data");

            for (int i = 0; i < projectArray.length(); i++) {
                JSONObject jsonObject = projectArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                String name = jsonObject.getString("name");
                int progress = jsonObject.getInt("progress");
                Project project = new Project(id, name, progress);
                ProjectList.add(project);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        ProjectList.add();
        ListProjectAdapter adapter = new ListProjectAdapter(ListProjectActivity.this, R.layout.list_project_layout, ProjectList);
        LV_project.setAdapter(adapter);

        LV_project.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> arg0,
                                            View arg1,
                                            int arg2,
                                            long arg3) {
                        Project project = ProjectList.get(arg2);
//                        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//                        try {
//                            String json = ow.writeValueAsString(project);
//                        } catch (JsonProcessingException e) {
//                            e.printStackTrace();
//                        }

                        Intent newIntent = new Intent(ListProjectActivity.this, ProjectDetailActivity.class);
                        newIntent.putExtra("project", project.getProject_name());
                        startActivity(newIntent);
//                        Toast.makeText(ListProjectActivity.this,project.getProject_name(),Toast.LENGTH_LONG).show();
                    }
                });
    }
}
