package com.example.pasejson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView textViewResult ;
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewResult = findViewById(R.id.result_jsonTxtV);
        Button btnParse = findViewById(R.id.btnParse);
        queue = Volley.newRequestQueue(this);

        btnParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JsonParse();
            }
        });

    }
    public void JsonParse(){
        String url = "%7B%0A%20%20%22Student%22%3A%20%5B%0A%20%20%20%20%20%20%7B%0A%20%20%20%20%20%20%20%20%20%20%20%20%22id%22%3A%221%22%2C%0A%20%20%20%20%20%20%20%20%20%20%20%20%22name%22%3A%22VuDinhThang%22%2C%0A%20%20%20%20%20%20%20%20%20%20%20%20%22email%22%3A%20%22vuthang19032%40gmail.com%22%2C%0A%20%20%20%20%20%20%20%20%20%20%20%20%22age%22%3A%2030%0A%20%20%20%20%20%20%7D%2C%0A%20%20%20%20%20%20%7B%0A%20%20%20%20%20%20%20%20%20%20%20%20%22firstname%22%3A%22Phat%22%2C%0A%20%20%20%20%20%20%20%20%20%20%20%20%22lastname%22%3A%20%22Nguyen%22%2C%0A%20%20%20%20%20%20%20%20%20%20%20%20%22age%22%3A%2040%0A%20%20%20%20%20%20%7D%0A%20%20%20%5D%0A%7D";


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("Student");

                    for (int i = 0; i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String id = jsonObject.getString("id");
                        String name  = jsonObject.getString("name");
                        String email  = jsonObject.getString("email");
                        int age = jsonObject.getInt("age");

                        textViewResult.append(id  + ", "+name+", "+ email+", "+String.valueOf(age)+"\n\n");




                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(request);
    }
}