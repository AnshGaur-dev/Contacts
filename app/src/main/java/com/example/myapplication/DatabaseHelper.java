package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.sql.Array;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME="ContactDB";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME ="CONTACTS";
    private static final String ID ="_id";
    private static final String PHONENUMBER= "phone_no";
    private static final String NAME="name";


    public DatabaseHelper( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // .execSQL cursor return nhi karta
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " TEXT, "
                + PHONENUMBER + " TEXT UNIQUE"
                + ")");


//        sqLiteDatabase=this.getReadableDatabase();
//        sqLiteDatabase.close();//use to close the database
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    SQLiteDatabase db= this.getWritableDatabase();
    public void addData(String name,String phone_no){

        ContentValues values=new ContentValues();//direct nhi dalte values apan iske through dalte h
        values.put(PHONENUMBER,phone_no);
        values.put(NAME,name);

        db.insert(TABLE_NAME,null,values);
    }
    public void update(String row_id, String name, String phone_number) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        Log.d("Data: " , "Name: " + name +"  " + "Phone_NO.: "+ phone_number);
        cv.put(NAME, name);
        cv.put(PHONENUMBER, phone_number);

        int result = db.update(TABLE_NAME, cv, ID + " = ?", new String[]{row_id});

        Log.d("Database Update", "SQL Query: " + db.compileStatement("UPDATE " + TABLE_NAME +
                " SET " + NAME + "='" + name + "', " + PHONENUMBER + "='" + phone_number + "' WHERE " + ID + "='" + row_id + "'").toString());

        Log.d("Database Update", "Rows updated: " + result);

        if (result == 1) {
            Log.d("Database Update", "Row with ID " + row_id + " updated successfully.");
            Toast.makeText(context, "Successfully updated", Toast.LENGTH_SHORT).show();
        } else if (result == 0) {
            Log.d("Database Update", "No rows were updated for ID " + row_id);
            Toast.makeText(context, "No rows were updated", Toast.LENGTH_SHORT).show();
        } else {
            Log.d("Database Update", "Unexpected update result: " + result);
            Toast.makeText(context, "Unexpected update result: " + result, Toast.LENGTH_SHORT).show();
        }

        db.close();
    }

    public int getIdFromName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        int id = -1; // Default value if name is not found

        Cursor cursor = db.query(TABLE_NAME, new String[]{ID}, PHONENUMBER + " = ?", new String[]{name}, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int idColumnIndex = cursor.getColumnIndex(ID);
                if (idColumnIndex != -1) {
                    id = cursor.getInt(idColumnIndex);
                } else {
                    Log.e("DatabaseHelper", "ID column not found in cursor.");
                }
            } else {
                Log.e("DatabaseHelper", "No data found for name: " + name);
            }
            cursor.close();
        } else {
            Log.e("DatabaseHelper", "Cursor is null.");
        }

        db.close();

        return id;
    }


    //yeah StructArraylist k bina ho sakta tah apan 3 array list bana lete har type k liye par yeah better approach h
    public ArrayList<StructArraylist> fetchContact(){
       // SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        db= this.getReadableDatabase();
        // .rawQuery cursor return karta h
        Cursor cursor=db.rawQuery(" SELECT * FROM "+ TABLE_NAME,null);
        //aab cursor mil gaya h toh while loop use hoga jisse hum table k end tak ka data read kar paye
        ArrayList<StructArraylist> structArraylists=new ArrayList<>();
        while (cursor.moveToNext()){
            StructArraylist arraylist=new StructArraylist();
            arraylist.id=cursor.getInt(0);
            arraylist.name=cursor.getString(1);
            arraylist.phone_number=cursor.getString(2);
            structArraylists.add(arraylist);
        }
        return  structArraylists;
    }
}
