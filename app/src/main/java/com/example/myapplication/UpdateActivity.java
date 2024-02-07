package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
String name,phone_number,id;
EditText name_edt,phone_number_edt;
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        name_edt=findViewById(R.id.Name_EdtUpdate);
        phone_number_edt=findViewById(R.id.Phone_numberEdtUpdate);
        button=findViewById(R.id.update_btn);
        GetandSetIntentData();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper databaseHelper=new DatabaseHelper(UpdateActivity.this);
                databaseHelper.update(id,name,phone_number);
                finish();
            }
        });
    }
void GetandSetIntentData(){
if (getIntent().hasExtra("name") && getIntent().hasExtra("phone_number") && getIntent().hasExtra("id")){
    name=getIntent().getStringExtra("name");
    phone_number=getIntent().getStringExtra("phone_number");
    id=getIntent().getStringExtra("id");
    name_edt.setText(name);
    phone_number_edt.setText(phone_number);
        }
else {
    Toast.makeText(UpdateActivity.this,"NO DATA",Toast.LENGTH_SHORT).show();
}
    }
}