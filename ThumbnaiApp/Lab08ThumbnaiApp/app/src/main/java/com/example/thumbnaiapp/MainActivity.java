package com.example.thumbnaiapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final int RC_CAMERA_CAPTURE = 1;
    //Reference UI element
    private ImageView ivPic;



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

        //Get UI object
        ivPic = findViewById(R.id.imageView);


    }

    public void OnTakePicture(View view) {
        //Create a request to Android
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //check if The device  has an app that can handle this request
        if(intent.resolveActivity(getPackageManager()) != null) {
            //send the request to Andrio
            startActivityForResult(intent, RC_CAMERA_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Check the request is for whom
        if(requestCode == RC_CAMERA_CAPTURE) {

            //Check if the result is OK
            if(resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                Bitmap image = (Bitmap) extras.get("data");
                ivPic.setImageBitmap(image);
            }
        }
    }
}