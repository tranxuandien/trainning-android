package com.example.gl.study1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
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

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText ET_username, ET_password;
    TextView TV_fogot_pass;
    Button BTN_login, BTN_signin;
    SharedPreferences sharedPreferences;
//    public static final String MyPREFERENCES="Mypreference";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ET_username = (EditText) findViewById(R.id.editText1);
        ET_password = (EditText) findViewById(R.id.editText2);
        BTN_login = (Button) findViewById(R.id.btn_login);
        BTN_signin = (Button) findViewById(R.id.btn_signin);
        TV_fogot_pass = (TextView) findViewById(R.id.fogot_pass);
        TV_fogot_pass.setClickable(true);
        TV_fogot_pass.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='http://www.google.com' style=\"color:white\"> パスワードをお忘れですか？ </a>";
        TV_fogot_pass.setText(Html.fromHtml(text));
//        sharedPreferences=getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        BTN_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogin();
            }
        });

        BTN_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doSignin();
            }
        });
    }

    private void doSignin() {
        Intent signIn = new Intent(LoginActivity.this, SigninActivity.class);
        startActivity(signIn);
    }

    private void doLogin() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.1.18/housebook_v2/public/api/auth/login";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject json = (JSONObject) new JSONTokener(response).nextValue();
                    String token = json.getString("token");
                    loginSuccess(token);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, R.string.validate_error, Toast.LENGTH_LONG).show();
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", ET_username.getText().toString());
                params.put("password", ET_password.getText().toString());
                return params;
            }
        };
        queue.add(stringRequest);
        return;
    }

    private void loginSuccess(String token) {
//        SharedPreferences.Editor editor= sharedPreferences.edit();
//        editor.putString("token",token);
//        editor.commit();
        Intent myIntent = new Intent(this, MainActivity.class);
        myIntent.putExtra("token", token);
        startActivity(myIntent);
    }
}
