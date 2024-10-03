package com.example.pnjoapp;

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
import com.example.pnjoapp.model.Car;
import com.example.pnjoapp.model.Student;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.ReferenceQueue;

public class MainActivity extends AppCompatActivity
{
    // References for the UI elements
    public TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // get the UI object from the layout
        tvData = findViewById(R.id.tvData);
    }

    public void onDownloadClick(View view)
    {
        // Create a request queu
        RequestQueue queu = Volley.newRequestQueue(this);
        
        // 2. Create a url
        String url = "https://www.jsonkeeper.com/b/AO4J";
        
        // 3. Create a request
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, url, null,
                this::handleResponse,
                this::handleError
        );

        // 4. add the request to the que
        queu.add(request);
    }

    private void handleError(VolleyError volleyError)
    {
        tvData.setText(volleyError.getMessage());
    }

    private void handleResponse(JSONObject response)
    {
//        Gson gson = new Gson();
//
//        Student student = gson.fromJson(response.toString(), Student.class);
//
//        tvData.setText(student.toString());
        try
        {
            String name = response.getString("name");
            String id = response.getString("id");
            double gpa = response.getDouble("GPA");
            boolean married = response.getBoolean("married");

            // Getting car object
            JSONObject carObj = response.getJSONObject("car");
            String make = carObj.getString("make");
            String model = carObj.getString("model");
            int year = carObj.getInt("year");

            // instantiating car - student Objects
            Car car = new Car(make, model, year);
            Student student = new Student(name, id, gpa, married, car);

            // Display the data of the student
            tvData.setText(student.toString());
        } catch (JSONException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void onClearClick(View view)
    {
        // Clear the text view
        tvData.setText("");
    }
}