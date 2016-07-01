package com.example.rahardyan.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private final String BASE_URL = "http://crysdip.herokuapp.com/api/";
    private final String LIST_INDUSTRI = "industri/list";
    private final String LIST_MAHASISWA = "mahasiswa/list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        StringRequest listIndustri = new StringRequest(Request.Method.GET, BASE_URL + LIST_INDUSTRI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response", "Response "+response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    JSONArray industris = jsonObject.getJSONArray("industris");
                    for (int i = 0; i < industris.length(); i++){
                        JSONObject industri = industris.getJSONObject(i);
                        String namaIndustri = industri.getString("nama_industri");
                        String alamat = industri.getString("alamat");
                    }

                    Log.d("response", "status " + status);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );

        requestQueue.add(listIndustri);
    }
}
