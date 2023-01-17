package com.example.implicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupBtns();
    }

    protected void setupBtns() {
        int[] ids = {
                R.id.visit_nus,
                R.id.locate_nus,
                R.id.call_nus,
                R.id.email_nus
        };

        for (int i=0; i<ids.length; i++) {
            Button btn = findViewById(ids[i]);
            if (btn != null) {
                btn.setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        Uri uri;

        int id = v.getId();

        if (id == R.id.visit_nus) {
            uri = Uri.parse("https://www.nus.edu.sg");
            intent = new Intent(Intent.ACTION_VIEW, uri);
        }
        else if (id == R.id.locate_nus) {
            uri = Uri.parse("geo:1.296643,103.776398");
            intent = new Intent(Intent.ACTION_VIEW, uri);
        }
        else if (id == R.id.call_nus) {
            uri = Uri.parse("tel:(+6565166666");
            intent = new Intent(Intent.ACTION_DIAL, uri);
        }
        else if (id == R.id.email_nus) {
            uri = Uri.parse("mailto:null@nus.edu.sg");
            intent = new Intent(Intent.ACTION_SENDTO, uri);
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.subject));
            intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.email_text));
        }

        if (intent != null) {
            if (intent.resolveActivity(getPackageManager()) != null) {
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }
    }

}































