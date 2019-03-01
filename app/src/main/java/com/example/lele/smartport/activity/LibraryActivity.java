package com.example.lele.smartport.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lele.smartport.R;
import com.example.lele.smartport.entity.Family;
import com.example.lele.smartport.entity.Type;
import com.example.lele.smartport.faker.Server;

import java.util.List;

public class LibraryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        init_bottom_button();
        AddFamily();
        AddType("0");
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

    public void AddFamily(){
        RecyclerView recyclerView =  findViewById(R.id.recyclerview_family);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        FamilyAdapter familyAdapter = new FamilyAdapter(Server.getFamilylist());
        recyclerView.setAdapter(familyAdapter);
    }

    public void AddType(String familyid){
        RecyclerView recyclerView =  findViewById(R.id.recyclerview_type);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        TypeAdapter typeAdapter = new TypeAdapter(Server.getTypelist(familyid));
        recyclerView.setAdapter(typeAdapter);
    }

    class FamilyAdapter extends RecyclerView.Adapter<FamilyAdapter.ViewHolder>{
        private List<Family> familylist;
        class ViewHolder extends RecyclerView.ViewHolder{
            View view;//整个控件组
            TextView textView;//显示文字信息
            TextView id;
            ViewHolder(View view) {
                super(view);
                this.view = view;
                textView = view.findViewById(R.id.text);
                id = view.findViewById(R.id.id);
            }
        }

        FamilyAdapter(List<Family> familylist)
        {
            this.familylist = familylist;
        }

        @Override
        public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType){
            final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_family,parent,false);
            ViewHolder holder = new FamilyAdapter.ViewHolder(view);
            holder.view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    for(int i=0;i<parent.getChildCount();i++)
                    {
                        LinearLayout linearLayout = (LinearLayout)parent.getChildAt(i);
                        TextView textView = linearLayout.findViewById(R.id.text);
                        if(linearLayout.equals(view))
                            textView.setTextColor(Color.RED);
                        else
                            textView.setTextColor(Color.BLACK);
                    }
                    TextView textView = view.findViewById(R.id.id);
                    String id = textView.getText().toString();
                    AddType(id);
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(FamilyAdapter.ViewHolder holder, int position){
            Family family = familylist.get(position);
            holder.textView.setText(family.getName());
            holder.id.setText(family.getId());
        }

        @Override
        public int getItemCount(){
            return familylist.size();
        }
    }

    class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.ViewHolder>{
        private List<Type> typelist;

        class ViewHolder extends RecyclerView.ViewHolder{
            View view;//整个控件组
            ImageView imageView;//显示图片
            TextView textView;//显示文字信息
            ViewHolder(View view) {
                super(view);
                this.view = view;
                imageView = view.findViewById(R.id.img);
                textView = view.findViewById(R.id.text);
            }
        }

        TypeAdapter(List<Type> typelist)
        {
            this.typelist = typelist;
        }

        @Override
        public TypeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type,parent,false);
            TypeAdapter.ViewHolder holder = new TypeAdapter.ViewHolder(view);
            holder.view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    CardView cardView = (CardView)view;
                    TextView textView = cardView.findViewById(R.id.text);
                    String item = textView.getText().toString();
                    Intent intent = new Intent(getApplicationContext(),WebViewActivity.class);
                    intent.putExtra("item",item);
                    startActivity(intent);
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(TypeAdapter.ViewHolder holder, int position){
            Type type = typelist.get(position);
            holder.imageView.setImageResource(R.drawable.applogo);
            holder.textView.setText(type.getName());
        }

        @Override
        public int getItemCount(){
            return typelist.size();
        }
    }
}
