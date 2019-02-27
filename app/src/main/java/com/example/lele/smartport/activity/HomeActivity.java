package com.example.lele.smartport.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.lele.smartport.R;
import com.example.lele.smartport.faker.Server;


public class HomeActivity extends AppCompatActivity {

    LinearLayout layout[] = new LinearLayout[16];//16个花盆的线性图层
    int id[]={
            R.id.layout_1_1,R.id.layout_1_2,R.id.layout_1_3,R.id.layout_1_4,
            R.id.layout_2_1,R.id.layout_2_2,R.id.layout_2_3,R.id.layout_2_4,
            R.id.layout_3_1,R.id.layout_3_2,R.id.layout_3_3,R.id.layout_3_4,
            R.id.layout_4_1,R.id.layout_4_2,R.id.layout_4_3,R.id.layout_4_4};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                overridePendingTransition(0, 0);
            }
        });
        b_lib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LibraryActivity.class));
                overridePendingTransition(0, 0);
            }
        });
        b_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SettingActivity.class));
                overridePendingTransition(0, 0);
            }
        });
    }
    public void init_self_button()
    {
        /*花盆为空的时候的响应函数*/
        View.OnClickListener gone_listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout layout = (LinearLayout)view;
                TextView textView = (TextView)layout.getChildAt(2);
                String pot_num = textView.getText().toString();
                Intent intent = new Intent(getApplicationContext(),SelectActivity.class);
                intent.putExtra("pot",pot_num);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        };

        /*花盆有花的时候的响应函数*/
        View.OnClickListener exist_listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout layout = (LinearLayout)view;
                TextView textView = (TextView)layout.getChildAt(2);
                int address = Integer.parseInt(textView.getText().toString());
                Intent intent = new Intent(getApplicationContext(),PotActivity.class);
                intent.putExtra("flower",address);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        };

        /*绑定花盆图层与id*/
        for(int i = 0;i<16;i++)
        {
            layout[i] = findViewById(id[i]);
        }

        for(int i = 0;i<16;i++)
        {
            if(Server.getPort(i).getTypeID().equals("NULL"))
            {
                ImageView imageView = (ImageView)layout[i].getChildAt(0);
                imageView.setImageResource(R.drawable.empty);
                layout[i].setOnClickListener(gone_listener);
            }else {
                TextView textView = (TextView)layout[i].getChildAt(1);
                textView.setText(Server.TypeIDtoName(Server.getPort(i).getTypeID()));
                layout[i].setOnClickListener(exist_listener);
            }
        }

    }
}
