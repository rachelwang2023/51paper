package com.example.explicitintent;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener {

    private String lastQuote = "";
    ActivityResultLauncher<Intent> rlWriteQuote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupBtns();
        registerForResult();
    }

    protected void setupBtns() {
        int[] ids = { R.id.writeQuote, R.id.showQuote };

        for (int i=0; i<ids.length; i++) {
            Button btn = findViewById(ids[i]);
            if (btn != null) {
                btn.setOnClickListener(this);
            }
        }
    }

    protected void registerForResult() {
        rlWriteQuote = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == AppCompatActivity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            String quote = data.getStringExtra("newQuote");
                            if (quote != null) {
                                lastQuote = quote;
                            }
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.writeQuote) {
            Intent intent = new Intent(this, WriteQuote.class);
            rlWriteQuote.launch(intent);
        }
        else if (id == R.id.showQuote) {
            Intent intent = new Intent(this, ShowQuote.class);
            intent.putExtra("quote", lastQuote);
            startActivity(intent);
        }
    }
}




































