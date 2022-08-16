package com.lzx.androidutils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lzx.utils.RxToast;

public class MainActivity extends AppCompatActivity {
    private TextView test_toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test_toast = findViewById(R.id.test_toast);
        test_toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxToast.success("成功");
            }
        });
    }
}