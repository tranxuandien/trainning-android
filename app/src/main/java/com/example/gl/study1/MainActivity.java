package com.example.gl.study1;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText ET_username,ET_password;
    TextView TV_response_result;
    Button BTN_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ET_username = (EditText)findViewById(R.id.editText1);
        ET_password = (EditText)findViewById(R.id.editText2);
        BTN_login = (Button) findViewById(R.id.btn_login);
        TV_response_result = (TextView) findViewById(R.id.textView1);
        BTN_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] dataLogin=doLogin();
                if(dataLogin[0].equals("test")&&dataLogin[1].equals("123"))
                {
                    Toast.makeText(MainActivity.this, "Login success", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private String[] doLogin(){
        String username =  ET_username.getText().toString();
        String password =  ET_password.getText().toString();
        String dataLogin[]={username,password};
        RequestQueue queue= Volley.newRequestQueue(this);
        String url = "http://192.168.1.18/housebook_v2/public/api/auth/login";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loginSuccess();
//                TV_response_result.setText(response);
//                Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                TV_response_result.setText("Thông tin đăng nhập hoặc mật khẩu không chính xác!");
                TV_response_result.setTextColor(Color.parseColor("#FF0000"));
//                Toast.makeText(MainActivity.this,"that didn't work",Toast.LENGTH_LONG).show();
            }
        }
        ){
        @Override
        protected Map<String, String> getParams()
        {
            Map<String, String>  params = new HashMap<String, String>();
            params.put("email", ET_username.getText().toString());
            params.put("password", ET_password.getText().toString());
            return params;
        }
        };
        queue.add(stringRequest);
        return dataLogin ;
    }
    private void loginSuccess(){
        Intent myIntent=new Intent(this,ChildActivity.class);
        startActivity(myIntent);
    }
}
