package com.example.senserup;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    //Refereces of ui elements
    private TextView tvData;

    //sensor reference
    private SensorManager sensorManager;
    private Sensor tempSensore;

    //Get UI objects from the layout

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

        tvData = findViewById(R.id.tvData);


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        tempSensore = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

        if (tempSensore == null) {
            String meg = "This device dose not support";
            tvData.setText(meg);

        }

    }


    @Override
    public Object getSystemService(@NonNull String name) {
        return super.getSystemService(name);
    }

    public void onSensorAdd(View view) {
        sensorManager.registerListener(this, tempSensore, SensorManager.SENSOR_DELAY_FASTEST);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float value = event.values[0];
        String  msg = event.sensor.getName() + ":" + value;
        tvData.setText(msg);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onPause() {
        super.onPause();

        sensorManager.unregisterListener(this);
    }
}