package com.example.pjaapp;

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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.pjaapp.model.Student;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    // reference for the UI elements
    TextView tvData;

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

        // Get the object from the data
        tvData = findViewById(R.id.tvData);

    }

    public void onDownloadClick(View view)
    {
        // 1. Create a queue
        RequestQueue queue = Volley.newRequestQueue(this);

        // 2. Create the url
        String url = "https://www.jsonkeeper.com/b/HPMZ";

        // 3. Create the request
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET, url, null,
                this::handleResponse,
                this::handleError
        );

        // 4. add the request to the queue
        queue.add(request);
    }

    private void handleError(VolleyError volleyError)
    {
        tvData.setText(volleyError.getMessage());
    }

    private void handleResponse(JSONArray response)
    {
        Gson gson = new Gson();
        try
        {
//            Student student1 = gson.fromJson(response.getJSONObject(0).toString(), Student.class);
//            Student student2 = gson.fromJson(response.getJSONObject(1).toString(), Student.class);
//
//            tvData.setText(student1.toString() + "\n\n"+ student2.toString());

            tvData.setText("");
            for (int i = 0; i < response.length(); i++)
            {
                Student student = gson.fromJson(response.getJSONObject(i).toString(), Student.class);
                tvData.append(student.toString());
                tvData.append("\n\n");
            }
        } catch (JSONException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void onClearClick(View view)
    {
        // to clear the text view
        tvData.setText("");
    }
}