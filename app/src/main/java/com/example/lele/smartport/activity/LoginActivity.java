package com.example.lele.smartport.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lele.smartport.R;
import com.example.lele.smartport.faker.Server;

public class LoginActivity extends AppCompatActivity {

    EditText edit_user,edit_pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edit_user = findViewById(R.id.edit_user);
        edit_pwd = findViewById(R.id.edit_pwd);
        init_self_button();
    }
    public void init_self_button()
    {

        new Server();
        Button b_register = findViewById(R.id.b_register);
        b_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                overridePendingTransition(0, 0);
            }
        });
        Button b_login = findViewById(R.id.b_login);
        b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edit_user.getText().toString().equals("")||edit_pwd.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(),"您的用户名或密码为空，请确认用户名或密码！",Toast.LENGTH_SHORT).show();
                else if(edit_user.getText().toString().equals("admin")&&edit_pwd.getText().toString().equals("admin"))
                {
                    Toast.makeText(getApplicationContext(),"登录成功！",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    overridePendingTransition(0, 0);
                }else
                    Toast.makeText(getApplicationContext(),"您的用户名或密码错误，请重新输入！",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
