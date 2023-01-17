package com.example.counttomax;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener {

    Thread bkgdThread;
    protected Button btn;
    protected TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(this);

        // cache this for use later
        result = findViewById(R.id.result);
    }

    public void showReadyUI()
    {
        btn.setText(getString(R.string.start));
        result.setText("");
    }

    public void showWorkingUI() {
        btn.setText(getString(R.string.stop));
        result.setText(R.string.computing);
    }

    public void showDoneUI(long sum)
    {
        result.setText(String.valueOf(sum));
        btn.setText(R.string.start);
    }

    public Thread createThread() {
        return new Thread(new Runnable() {
            long sum = 0;

            @Override
            public void run() {
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    sum += i;

                    if (Thread.interrupted()) {
                        bkgdThread = null;
                        return;
                    }
                }

                // done computing; release thread
                bkgdThread = null;

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showDoneUI(sum);
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (bkgdThread != null) {
            bkgdThread.interrupt();
            showReadyUI();
            return;
        }

        // note that start() can only be called once on
        // a worker thread
        bkgdThread = createThread();
        bkgdThread.start();

        showWorkingUI();
    }
}