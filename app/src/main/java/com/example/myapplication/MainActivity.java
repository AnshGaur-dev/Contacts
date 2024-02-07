package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatingActionButton=findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, AddContactActivity.class);
                startActivity(intent);

            }
        });
        recyclerView=findViewById(R.id.RecycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


    }
    //isse data main screen pe add hote sath show hone laagega
    @Override
    protected void onResume() {
        super.onResume();
        DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
        ArrayList<StructArraylist> arraylists = databaseHelper.fetchContact();
        CustomAdapter customAdapter = new CustomAdapter(MainActivity.this, arraylists);

        if (recyclerView != null) {
            recyclerView.setAdapter(customAdapter);

        }
        else {
            Toast.makeText(this,"NO DATA",Toast.LENGTH_SHORT);
        }
    }
    protected void onActivityResult(int resultcode, int requestcode, @Nullable Intent data) {
        super.onActivityResult(resultcode, requestcode, data);
        if (requestcode==1){
            recreate();
        }
    }

}