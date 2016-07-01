package com.example.rahardyan.myapplication;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

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

/**
 * Created by rahardyan on 01/07/16.
 */
public class NetworkService {
    private final RequestQueue requestQueue;
    private final Context context;

    public NetworkService(Context context) {
        this.context = context;
        this.requestQueue = Volley.newRequestQueue(context);

    }

    public void login(final String nim, final String password, final ClientCallback clientCallback){
        String url = context.getResources().getString(R.string.base_url) + context.getResources().getString(R.string.login_url);
        Log.d("amsibsam", "url "+url);
        final StringRequest loginRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject logResponse = new JSONObject(response);
                    Log.d("amsibsam", "response "+logResponse.toString(2));
                    String status = logResponse.getString("status");
                    if (status.equals("success")) {
                        clientCallback.onSucceedeed();
                    } else {
                        clientCallback.onFailed();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("amsibsam", response);
                    clientCallback.onFailed();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                clientCallback.onFailed();
                Log.d("amsibsam", "error login "+error.toString());
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<>();
                header.put("Content-Type", "application/x-www-form-urlencoded");
                return header;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nim", nim);
                params.put("password", password);
                return params;
            }
        };
        requestQueue.add(loginRequest);
    }
}
