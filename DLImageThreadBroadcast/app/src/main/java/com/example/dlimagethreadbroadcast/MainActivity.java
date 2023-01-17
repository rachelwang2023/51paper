package com.example.dlimagethreadbroadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;

import java.io.File;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    protected BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (action.equals("download_completed")) {
                String filename = intent.getStringExtra("filename");
                updateImageView(filename);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initReceiver();

        String URL = "https://p4.wallpaperbetter.com/wallpaper/291/663/679/stones-background-stones-spa-wallpaper-preview.jpg";
        startDownloadImage(URL);
    }

    protected void initReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("download_completed");

        registerReceiver(receiver, filter);
    }

    protected void startDownloadImage(String imgURL) {
        Intent intent = new Intent(this, MyDownloadService.class);
        intent.setAction("download_file");
        intent.putExtra("url", imgURL);

        startService(intent);
    }

    protected void updateImageView(String filename) {
        File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File destFile = new File(dir, filename);

        Bitmap bitmap = BitmapFactory.decodeFile(destFile.getAbsolutePath());
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageBitmap(bitmap);
    }
}