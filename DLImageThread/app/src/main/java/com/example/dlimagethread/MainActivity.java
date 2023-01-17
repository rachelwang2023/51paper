package com.example.dlimagethread;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;

import java.io.File;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String URL = "https://p4.wallpaperbetter.com/wallpaper/291/663/679/stones-background-stones-spa-wallpaper-preview.jpg";
        startDownloadImage(URL);
    }

    protected void startDownloadImage(String imgURL) {
        String destFilename = UUID.randomUUID().toString() +
                imgURL.substring(imgURL.lastIndexOf("."));
        File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File destFile = new File(dir, destFilename );

        // creating a background thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                ImageDownloader imgDL = new ImageDownloader();
                if (imgDL.downloadImage(imgURL, destFile)) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Bitmap bitmap = BitmapFactory.decodeFile(destFile.getAbsolutePath());
                            ImageView imageView = findViewById(R.id.imageView);
                            imageView.setImageBitmap(bitmap);
                        }
                    });
                }
            }
        }).start();
    }
}