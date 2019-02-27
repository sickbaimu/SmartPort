package com.example.lele.smartport.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lele.smartport.R;
import com.example.lele.smartport.entity.Port;
import com.example.lele.smartport.faker.Server;

public class PotActivity extends AppCompatActivity {

    private Port port;
    TextView t_name,t_pot,t_TEMP,t_HUM,t_PH,t_range_TEMP,t_range_HUM,t_range_PH;
    Button b_left_TEMP,b_left_HUM,b_left_PH,b_right_TEMP,b_right_HUM,b_right_PH;
    Button b_back,b_change,b_unstall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pot);
        /*接收来自intent的flower对象*/
        final int address = getIntent().getIntExtra("flower",-1);
        port = Server.getPort(address);

        /*初始化各文字控件*/
        t_name = findViewById(R.id.t_name);
        t_pot = findViewById(R.id.t_pot);
        t_TEMP = findViewById(R.id.t_TEMP);
        t_HUM = findViewById(R.id.t_HUM);
        t_PH = findViewById(R.id.t_PH);
        t_range_TEMP = findViewById(R.id.t_range_TEMP);
        t_range_HUM = findViewById(R.id.t_range_HUM);
        t_range_PH = findViewById(R.id.t_range_PH);
        update();

        /*初始化各调控按钮*/
        b_left_TEMP = findViewById(R.id.b_left_TEMP);
        b_left_HUM = findViewById(R.id.b_left_HUM);
        b_left_PH = findViewById(R.id.b_left_PH);
        b_right_TEMP = findViewById(R.id.b_right_TEMP);
        b_right_HUM = findViewById(R.id.b_right_HUM);
        b_right_PH = findViewById(R.id.b_right_PH);


        /*添加各调控按钮的响应函数*/
        b_left_TEMP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                port.setTEMP(port.getTEMP()-1);
                update();
            }
        });
        b_left_HUM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                port.setHUM(port.getHUM()-1);
                update();
            }
        });
        b_left_PH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                port.setPH(port.getPH()-1);
                update();
            }
        });
        b_right_TEMP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                port.setTEMP(port.getTEMP()+1);
                update();
            }
        });
        b_right_HUM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                port.setHUM(port.getHUM()+1);
                update();
            }
        });
        b_right_PH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                port.setPH(port.getPH()+1);
                update();
            }
        });

        /*初始化各功能按钮*/
        b_back = findViewById(R.id.b_back);
        b_change = findViewById(R.id.b_change);
        b_unstall = findViewById(R.id.b_unstall);

        /*添加各功能按钮的响应函数*/
        b_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                overridePendingTransition(0, 0);
            }
        });
        b_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SelectActivity.class);
                intent.putExtra("pot",address+"");
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
        b_unstall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                port.setTypeID("NULL");
                port.init();
                Toast.makeText(getApplicationContext(),"卸载成功！",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                overridePendingTransition(0, 0);
            }
        });
    }
    public void update()
    {
        /*阈值设置*/
        if(port.getHUM()<0)
            port.setHUM(0);
        if(port.getHUM()>100)
            port.setHUM(100);
        if(port.getPH()<1)
            port.setPH(1);
        if(port.getPH()>13)
            port.setPH(13);

        /*更新数据到UI*/
        String pot = port.getAddress()+"号花盆";
        String TEMP = port.getTEMP()+"°C";
        String HUM = port.getHUM()+"%";
        String PH = port.getPH()+"";
        String range_TEMP = "建议温度:"+port.getTEMP()+"°C";
        String range_HUM = "建议湿度:"+port.getHUM()+"%";
        String range_PH = "建议酸碱度:"+port.getPH();

        t_name.setText(Server.TypeIDtoName(port.getTypeID()));
        t_pot.setText(pot);
        t_TEMP.setText(TEMP);
        t_HUM.setText(HUM);
        t_PH.setText(PH);
        t_range_TEMP.setText(range_TEMP);
        t_range_HUM.setText(range_HUM);
        t_range_PH.setText(range_PH);
    }
}
