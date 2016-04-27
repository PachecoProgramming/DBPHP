package com.example.anthony.capstoneapp3;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
        final String registerUrl = "http://capstoneusers.comlu.com//reg.php";

        /* creates fields for the user to input information */

        final EditText etFName = (EditText) findViewById(R.id.etFName);
        final EditText etLName = (EditText) findViewById(R.id.etLName);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);

        /* creates a button to send the information to the database */
        final Button bRegister = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
            
                final Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                StringRequest rrequest = new StringRequest(Request.Method.POST, registerUrl, new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response)
                    {
                      if(response.contains(" success "))
                        {
                            RegisterActivity.this.startActivity(intent);
                            //Log.d(response, "Server Response");
                        }else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                            builder.setMessage("Register Failed)
                                    .setNegativeButton("Retry", null)
                                    .create()
                                    .show();
                                    Log.d(response, "Server Response");
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError response) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map <String,String> params = new HashMap<String, String>();
                        params.put("firstname", etFName.getText().toString());
                        params.put("lastname", etLName.getText().toString());
                        params.put("username", etUsername.getText().toString());
                        params.put("password", etPassword.getText().toString());

                        return params;
                    }
                };
                queue.add(rrequest);
            }
        });
    }
}
