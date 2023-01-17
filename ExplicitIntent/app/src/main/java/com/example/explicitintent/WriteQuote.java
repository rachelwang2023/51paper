package com.example.explicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class WriteQuote extends AppCompatActivity
    implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_quote);

        Button ok = findViewById(R.id.ok);
        if (ok != null) {
            ok.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        EditText newQuote = findViewById(R.id.newQuote);
        if (newQuote != null) {
            Intent intent = new Intent();
            String quote = newQuote.getText().toString();
            intent.putExtra("newQuote", quote);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}