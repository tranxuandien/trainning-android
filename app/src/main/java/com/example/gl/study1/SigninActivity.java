package com.example.gl.study1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SigninActivity extends AppCompatActivity{
    EditText EDT_username,EDT_password,EDT_password_confirm,EDT_company_name,EDT_sex,EDT_name,EDT_phone,EDT_email;
    Button BTN_doSignin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        BTN_doSignin= (Button)findViewById(R.id.btn_dosignin);

        EDT_username= (EditText) findViewById(R.id.edt_username);
        EDT_password= (EditText) findViewById(R.id.edt_password);
        EDT_password_confirm= (EditText) findViewById(R.id.edt_password_confirm);
        EDT_company_name= (EditText) findViewById(R.id.edt_company_name);
        EDT_sex= (EditText) findViewById(R.id.edt_sex);
//        EDT_name= (EditText) findViewById(R.id.edt_name);
        EDT_phone= (EditText) findViewById(R.id.edt_phone);
        EDT_email= (EditText) findViewById(R.id.edt_email);

        BTN_doSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (EDT_username.getText().toString().trim().equals("") || EDT_email.getText().toString().trim().equals("") ||
                        EDT_password_confirm.getText().toString().trim().equals("") || EDT_company_name.getText().toString().trim().equals("")){
                    Toast.makeText(SigninActivity.this,R.string.Empty_fields,Toast.LENGTH_LONG).show();
                }
                else
                {
                    doSignin();
                }
            }

            private void doSignin() {
                RequestQueue queue= Volley.newRequestQueue(SigninActivity.this);
                String url="http://192.168.1.18/housebook_v2/public/api/auth/register";
                StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(SigninActivity.this,R.string.create_user_success, Toast.LENGTH_LONG).show();
                        Intent myIntent=new Intent(SigninActivity.this,LoginActivity.class);
                        startActivity(myIntent);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SigninActivity.this,R.string.create_user_fail,Toast.LENGTH_LONG).show();
                    }
                }
                ){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String>  params = new HashMap<String, String>();
                        params.put("first_name", EDT_username.getText().toString());
                        params.put("password", EDT_password.getText().toString());
                        params.put("password_confirmation", EDT_password_confirm.getText().toString());
                        params.put("company_name",EDT_company_name.getText().toString());
                        params.put("sex",EDT_sex.getText().toString());
//                        params.put("last_name",EDT_name.getText().toString());
                        params.put("phone_number",EDT_phone.getText().toString());
                        params.put("email",EDT_email.getText().toString());
                        return params;
                    }
                };
                queue.add(stringRequest);
                return;
            }
        });
    }
}
