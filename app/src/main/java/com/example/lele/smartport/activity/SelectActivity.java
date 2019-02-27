package com.example.lele.smartport.activity;

import android.content.Intent;
import android.support.percent.PercentFrameLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lele.smartport.R;
import com.example.lele.smartport.entity.Type;

import java.util.ArrayList;

public class SelectActivity extends AppCompatActivity {

    String family;//科
    TextView t_title;
    Button b_family[] = new Button[6];
    int id[]={R.id.b_family_1,R.id.b_family_2,R.id.b_family_3,R.id.b_family_4,R.id.b_family_5,R.id.b_family_6};
    LinearLayout layout_context;
    int pot = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        pot = Integer.parseInt(getIntent().getStringExtra("pot"));
        init_self_button();
    }
    public void init_self_button(){
        t_title = findViewById(R.id.t_title);
        layout_context = findViewById(R.id.layout_context);
        /*DrawerLayout上按钮（科）的响应函数*/
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button)view;
                family = button.getText().toString();
                layout_context.removeAllViews();
                t_title.setText(family);
                //AddFlowerType();
                DrawerLayout drawerLayout = findViewById(R.id.drawerlayout);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        };
        for(int i = 0;i<6;i++)
        {
            b_family[i] = findViewById(id[i]);
            b_family[i].setOnClickListener(listener);
        }
        family = b_family[0].getText().toString();
        //AddFlowerType();
    }
}
