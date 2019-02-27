package com.example.lele.smartport.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lele.smartport.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init_self_button();
    }
    public void init_self_button()
    {
        final EditText edit_user = (EditText)findViewById(R.id.edit_user);
        final EditText edit_pwd = (EditText)findViewById(R.id.edit_pwd);
        final EditText edit_pwd_confirm = (EditText)findViewById(R.id.edit_pwd_confirm);
        Button b_register = (Button)findViewById(R.id.b_register);
        b_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edit_user.getText().toString().equals("")||edit_pwd.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(),"您的用户名或密码为空，请确认用户名或密码！",Toast.LENGTH_SHORT).show();
                else if(!edit_pwd.getText().toString().equals(edit_pwd_confirm.getText().toString()))
                    Toast.makeText(getApplicationContext(),"您的确认密码与密码不一致，请确认密码！",Toast.LENGTH_SHORT).show();
                else if(!edit_user.getText().toString().equals("123"))
                    Toast.makeText(getApplicationContext(),"您的用户名已经存在，请填写新的用户名",Toast.LENGTH_SHORT).show();
                else register(edit_user.getText().toString(),edit_pwd.getText().toString());
            }
        });

        Button b_back = (Button)findViewById(R.id.b_back);
        b_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void register(String user,String pwd)
    {
        Toast.makeText(getApplicationContext(),"注册成功！",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        overridePendingTransition(0, 0);
    }
}
