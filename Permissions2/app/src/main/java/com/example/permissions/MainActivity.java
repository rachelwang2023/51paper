package com.example.permissions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener {

    private final int REQ_RECORD_AUDIO = 1;
    private final int REQ_RECEIVE_SMS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button recordAudioBtn = findViewById(R.id.recordAudioBtn);
        if (recordAudioBtn != null) {
            recordAudioBtn.setOnClickListener(this);
        }

        Button receiveSMSBtn = findViewById(R.id.receiveSMSBtn);
        if (receiveSMSBtn != null) {
            receiveSMSBtn.setOnClickListener(this);
        }
    }

    public boolean requestPermission(String permission, int requestCode) {
        if (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        else {
            ActivityCompat.requestPermissions(this, new String[] { permission }, requestCode);
            return false;
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.recordAudioBtn) {
            if (requestPermission(Manifest.permission.RECORD_AUDIO, REQ_RECORD_AUDIO)) {
                Toast.makeText(this, getString(R.string.record_audio_already_granted),
                        Toast.LENGTH_SHORT).show();
            }
        }

        if (id == R.id.receiveSMSBtn) {
            if (requestPermission(Manifest.permission.RECEIVE_SMS, REQ_RECEIVE_SMS)) {
                Toast.makeText(this, getString(R.string.receive_sms_already_granted),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        String msg = "";

        switch (requestCode) {
            case REQ_RECORD_AUDIO:
                msg = (grantResults[0] == PackageManager.PERMISSION_GRANTED) ?
                        getString(R.string.record_audio_granted) : getString(R.string.record_audio_denied);
                break;

            case REQ_RECEIVE_SMS:
                msg = (grantResults[0] == PackageManager.PERMISSION_GRANTED) ?
                        getString(R.string.receive_sms_granted) : getString(R.string.receive_sms_denied);
                break;
        }

        if (! msg.isEmpty()) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }
}

































