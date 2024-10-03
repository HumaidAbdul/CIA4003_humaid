package com.example.bluetoothcontrolapp;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final int RC_BLUETOOTH_CONNECT = 1;
    private static final int RC_BLUETOOTH_ENABLE = 2;

    // References for the UI elements
    private TextView tvData, tvMessage;
    private Switch swOnOff;

    // References for Bluetooth adapter
    private BluetoothAdapter adapter;

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

        // Get UI objects from the layout
        tvData = findViewById(R.id.tvData);
        tvMessage = findViewById(R.id.tvMessage);
        swOnOff = findViewById(R.id.swOnOff);

        // Get Bluetooth adapter object
        adapter = BluetoothAdapter.getDefaultAdapter();

    } // End of onCreate() method

    public void onTurnOnClick(View view)
    {
        // Check the API level for 31 or above
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
        {
            // Ask for permission for API 31 and above
            if(ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this, new
                        String[]{}, RC_BLUETOOTH_CONNECT);
                return; // Stop if there is no permission
            }
        }

        // Here you can ask for turning Bluetooth on
        // Request Android to ask the user for turning the Bluetooth on
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(intent, RC_BLUETOOTH_ENABLE);

    } // End of onTurnOnClick() method

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Check the request code
        if(requestCode == RC_BLUETOOTH_CONNECT)
        {
            // Check the request state
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                // User Granted the Bluetooth connect request
                onTurnOnClick(null);
            }
            else
            {
                String msg = "User Denied Bluetooth connect request";
                tvMessage.setText(msg);
            }
        }
    } // End of onRequestPermissionsResult() method

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        // Check the request code
        if(requestCode == RC_BLUETOOTH_ENABLE)
        {
            // Check the state of the request
            if(requestCode == RESULT_OK)
            {
                String msg = "Bluetooth enable";
                tvData.setText(msg);
            }
            else
            {
                String msg = "User denied Bluetooth enable request";
                tvData.setText(msg);
            }
        }
    }

    public void onTurnOffClick(View view)
    {
    } // End of onTurnOffClick() method

    public void onMakeVisibleClick(View view)
    {
    } // End of onMakeVisibleClick() method
}