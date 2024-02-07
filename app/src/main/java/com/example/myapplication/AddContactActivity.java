package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddContactActivity extends AppCompatActivity {
    EditText edtNameAdd,edtPhoneAdd;
    Button addbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        edtNameAdd=findViewById(R.id.edtNameAdd);
        edtPhoneAdd=findViewById(R.id.edtPhoneAdd);
        addbutton=findViewById(R.id.Addbtn);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper=new DatabaseHelper(AddContactActivity.this);
                databaseHelper.addData(edtNameAdd.getText().toString().trim(),edtPhoneAdd.getText().toString().trim());
                finish();
            }
        });


    }
}