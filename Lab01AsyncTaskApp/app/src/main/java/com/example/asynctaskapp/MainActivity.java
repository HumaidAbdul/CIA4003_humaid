package com.example.asynctaskapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // references for the UI elements
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get object from the layout
        tvResult = findViewById(R.id.tvResult);
    }

    public void onClearClick(View view)
    {
        // Clear the text on the TextView
        tvResult.setText("");
    }

    public void onRunClick(View view)
    {
        // Create a background thread - myTask
        MyTask task = new MyTask();
        task.execute();
    }

    // AsyncTask class
    private class MyTask extends AsyncTask<Void, Void, Long>
    {

        @Override
        protected Long doInBackground(Void... voids) {
            // Big task
            long sum = 0;
            for(int i = 0; i < 1000_000_000; i++)
            {
                if(i % 100_000 == 0)
                {
                    sum += 1;
                }
            }
            return sum;
        }

        @Override
        protected void onPostExecute(Long s) {
            super.onPostExecute(s);

            // Display the sum in the TextView
            tvResult.setText(String.valueOf(s));
        }
    }
}