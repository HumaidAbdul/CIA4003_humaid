package com.example.psjoapp;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.psjoapp.model.Student;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
{
    // References for the UI elements
    private TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Get the object
        tvData = findViewById(R.id.tvData);

    }

    public void onClearClick(View view)
    {
        // Clear the textView
        tvData.setText("");
    }

    public void onDownloadClick(View view)
    {
        // 1. Create a queu for requests
        RequestQueue queue = Volley.newRequestQueue(this);

        // 2. Create a string that represents the URL
        String url = "https://www.jsonkeeper.com/b/2RU4";

        // 3. Create a request - can be either JsonObjectRequest or JsonArrayRequest
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, url, null,
                this::handleResponse,
                this::handleError
        );

        // 4. Add the request to the queu
        queue.add(request);
    }

    private void handleError(VolleyError volleyError)
    {
        // Display an error in case happens
        tvData.setText(volleyError.getMessage());
    }

    private void handleResponse(JSONObject response)
    {
//        try
//        {
//            String name = response.getString("name");
//            String id = response.getString("id");
//            double gpa = response.getDouble("GPA");
//            boolean married = response.getBoolean("married");
//
//            String details = "Name:    "+ name    + "\n"+
//                             "ID:      "+ id      + "\n"+
//                             "GPA:     "+ gpa     + "\n"+
//                             "married: "+ married + "\n";
//
//            tvData.setText(details);
//        } catch (JSONException e) {
//            throw new RuntimeException(e);
//        }

        // Create a Gson object
        Gson gson = new Gson();

        // Ask gson to generate the Student objet -- parse the JSON response
        Student student = gson.fromJson(response.toString(), Student.class);

        // Output the student details
        tvData.setText(student.toString());
    }
}