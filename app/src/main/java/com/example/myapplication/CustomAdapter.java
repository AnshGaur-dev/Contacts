package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;

 public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    ArrayList<StructArraylist> Arraylist;

    CustomAdapter(Context context,ArrayList<StructArraylist> Arraylist){
        this.context=context;
        this.Arraylist=Arraylist;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.row,parent,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder,final int position) {
        holder.ContactId.setText(String.valueOf(Arraylist.get(position).id));
        holder.name.setText(String.valueOf(Arraylist.get(position).name));
        holder.phone_number.setText(String.valueOf(Arraylist.get(position).phone_number));
        //isse delete hoga long press se
        holder.row.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("Delete");
                builder.setIcon(R.drawable.baseline_delete_24);
                builder.setMessage("Are you sure that you want to delete this contact? ");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                return true;
            }
        });
        holder.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,UpdateActivity.class);
                intent.putExtra("name",Arraylist.get(position).name);
                intent.putExtra("phone_number",Arraylist.get(position).phone_number);
                intent.putExtra("id",Arraylist.get(position).id);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {

        return Arraylist.size() ;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,phone_number,ContactId;
        LinearLayout row;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.Name_txt);
            phone_number=itemView.findViewById(R.id.Phone_number);
            row=itemView.findViewById(R.id.my_row);
            ContactId=itemView.findViewById(R.id.contact_id);
        }
    }
}
