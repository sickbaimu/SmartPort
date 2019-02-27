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

import com.example.lele.smartport.R;
import com.example.lele.smartport.entity.Type;
import com.example.lele.smartport.faker.Server;

import java.util.ArrayList;

public class LibraryActivity extends AppCompatActivity {

    String family;//科
    TextView t_title;
    Button b_family[] = new Button[6];
    int id[]={R.id.b_family_1,R.id.b_family_2,R.id.b_family_3,R.id.b_family_4,R.id.b_family_5,R.id.b_family_6};
    LinearLayout layout_context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        init_bottom_button();
        init_self_button();
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

    public void init_self_button(){
        t_title = findViewById(R.id.t_title);
        layout_context = findViewById(R.id.layout_context);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button)view;
                family = button.getText().toString();
                layout_context.removeAllViews();
                t_title.setText(family);
                AddFlower();
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
                AddFlower();
    }

    public void AddFlower()
    {
        ArrayList<Type> typelist = Server.getTypelist();
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PercentFrameLayout children = (PercentFrameLayout)view;
                TextView textView = (TextView)((PercentFrameLayout)(children.getChildAt(1))).getChildAt(0);
                Intent intent = new Intent(getApplicationContext(),WebViewActivity.class);
                intent.putExtra("item",textView.getText().toString());
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        };
        int i = 0;
        for(Type flowerType : typelist)
        {
            if(flowerType.getFamilyid().equals(family))
            {
                LinearLayout root = (LinearLayout)getLayoutInflater().inflate(R.layout.item_flower,layout_context,true);
                PercentFrameLayout children = (PercentFrameLayout)root.getChildAt(i++);
                children.setOnClickListener(listener);
                TextView textView = (TextView)((PercentFrameLayout)(children.getChildAt(1))).getChildAt(0);
                textView.setText(flowerType.getName());
            }
        }
    }
}
