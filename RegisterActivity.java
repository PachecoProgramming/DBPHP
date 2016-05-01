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
        final String registerUrl = "http://capstoneusers.comlu.com/reg.php";

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
                StringRequest rrequest = new StringRequest(Request.Method.POST, registerUrl, new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError response) {

                    }
                }){
                    @Override
                    public Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        if (etUsername.getText().toString() != " " && etPassword.getText().toString() != " " && etLName.getText().toString() != " " && etFName.getText().toString() != " ") {
                            params.put("FirstName", etFName.getText().toString());
                            params.put("LastName", etLName.getText().toString());
                            params.put("Username", etUsername.getText().toString());
                            params.put("Password", etPassword.getText().toString());
                            Log.d(params.toString(), "DEBUGGING params.put...");
                            Log.v(params.toString(), "VERBOSE params.put...");
                        } else {
                            AlertDialog.Builder buildme = new AlertDialog.Builder(RegisterActivity.this);
                            buildme.setMessage("Please fill all fields ")
                                    .setNegativeButton("Retry", null)
                                    .create()
                                    .show();

                        }
                        return params;
                    }
                };
                queue.add(rrequest);
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                RegisterActivity.this.startActivity(intent);
            }
        });
    }
}
