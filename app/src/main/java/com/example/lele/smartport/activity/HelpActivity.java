package com.example.lele.smartport.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lele.smartport.R;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        init_bottom_button();
    }

    public void init_bottom_button() {
        Button b_pot = findViewById(R.id.b_pot);
        Button b_lib = findViewById(R.id.b_lib);
        Button b_set = findViewById(R.id.b_set);
        b_pot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                overridePendingTransition(0, 0);
            }
        });
        b_lib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LibraryActivity.class));
                overridePendingTransition(0, 0);
            }
        });
        b_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SettingActivity.class));
                overridePendingTransition(0, 0);
            }
        });
    }
}
