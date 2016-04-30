package com.example.anthony.capstoneapp3;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        final String loginUrl = "http://capstoneusers.comlu.com//loginUser.php";

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bLogin = (Button) findViewById(R.id.bLogin);
        final TextView registerLink = (TextView) findViewById(R.id.tvRegisterHere);
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final Intent intent = new Intent(LoginActivity.this, UserAreaActivity.class);
                final StringRequest lrequest = new StringRequest(Request.Method.POST, loginUrl, new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        if(response == "success")
                        {
                            LoginActivity.this.startActivity(intent);
                            //Log.v(response, "Server Response");

                        }else{
                            Log.i(response, "Server Response");
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            builder.setMessage("Login Failed: Invalid Credentials ")
                                    .setNegativeButton("Retry", null)
                                    .create()
                                    .show();
                        }
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError response) {
                            }
                        })
                {
                    @Override
                    public Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("username", etUsername.getText().toString());
                        params.put("password", etPassword.getText().toString());
                       //Log.d(params.toString(), "DEBUGGING params.put...");
                       Log.v(params.toString(), "VERBOSE params.put...");
                        return params;
                    }
                };
                queue.add(lrequest);
                //LoginActivity.this.startActivity(intent);
            }
        });
    }
}
