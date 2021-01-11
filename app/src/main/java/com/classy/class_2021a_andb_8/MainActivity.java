package com.classy.class_2021a_andb_8;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.drjacky.imagepicker.ImagePicker;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private ImageView main_IMG_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_IMG_user = findViewById(R.id.main_IMG_user);

        main_IMG_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImage();
            }
        });
    }

    private void getImage() {
        ImagePicker.Companion
                .with(this)
                .crop()
                .cropOval()
                .cropSquare()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start();
    }

    @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            Uri fileUri = data.getData();
            main_IMG_user.setImageURI(fileUri);

            //You can get File object from intent
            File file = new ImagePicker().Companion.getFile(data);

            //You can also get File Path from intent
            String filePath = new ImagePicker().Companion.getFilePath(data);

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, new ImagePicker().Companion.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}