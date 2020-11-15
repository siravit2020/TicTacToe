package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class first extends AppCompatActivity {
    private Button page1;
    private Button page2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        page1 = findViewById(R.id.page1);
        page2 = findViewById(R.id.page2);
        page1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(first.this,MainActivity.class);
                intent.putExtra("type","ai");
                startActivity(intent);
            }
        });
        page2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(first.this,Choose.class);
                intent.putExtra("type","online");
                startActivity(intent);
            }
        });
    }
}