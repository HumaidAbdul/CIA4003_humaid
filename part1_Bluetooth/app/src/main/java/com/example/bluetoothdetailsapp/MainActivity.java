package com.example.bluetoothdetailsapp;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // Still not working, needs to be solved

    // request code
    public static final int RC_Bluetooth_Connect = 1;

    // references for the UI elements
    private TextView tvData;

    // reference for the Bluetooth adapter
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

        // Get Bluetooth adapter object
        adapter = BluetoothAdapter.getDefaultAdapter();
    }

    public void onClickBTDetailsClick(View view)
    {
        // Check if the Bluetooth is supported on this device
        if(adapter == null) // not supported
        {
            String msg = "Bluetooth not supported on this device";
            tvData.setText(msg);
            return; // to stop executing, there is no point on continuing
        }
        // get Bluetooth details
        getBluetootdetails();
    } // end of onClickBTDetailsClick

    private void getBluetootdetails()
    {
        // Check if the API is 31 or above for the connect permission
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
        {
            // check if the permission is granted
            if(ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED)
            {
                // Ask the user to permit the connect permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.BLUETOOTH_CONNECT},
                        RC_Bluetooth_Connect);
                return;
            }

            // Get Bluetooth details from the adapter
            String name = adapter.getName();
            String address = adapter.getAddress();
            int state = adapter.getState();

            String bluetoothDetails = "Name    "+ name   + "\n"+
                                      "Address:"+address + "\n" +
                                      "State:  "+state;

            // Display Bluetooth details on the text view
            tvData.setText(bluetoothDetails);
        }
    } // End of getBluetoothDetails () method

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // check the request code
        if(requestCode == RC_Bluetooth_Connect) // BLUETOOTH CONNECT REQUEST
        {
            // check the state of the request
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                getBluetootdetails();
            }
            else
            {
                String msg = "User denied Bluetooth connect request";
                tvData.setText(msg);
                getBluetootdetails();
            }
        }
    }
}