package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private final Context context;
    private static final String DATABASE_NAME="ContactDB";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME ="CONTACTS";
    private static final String ID ="_id";
    private static final String PHONENUMBER= "phone_no";
    private static final String NAME="name";


     DatabaseHelper( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
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
    public void addData(String name,String phone_no){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();//direct nhi dalte values apan iske through dalte h
        values.put(PHONENUMBER,phone_no);
        values.put(NAME,name);
        db.insert(TABLE_NAME,null,values);
    }
     void update(String row_id, String name, String phone_number){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(NAME, name);
        cv.put(PHONENUMBER, phone_number);
        long result=db.update(TABLE_NAME,cv,"_id=?",new String[]{row_id});
        if (result==-1){
            Toast.makeText(context, "Fail to  Update", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Sucessfully Update", Toast.LENGTH_SHORT).show();
            Log.d("Sucesss mili","HO GAYA ");
        }
    }
    //yeah StructArraylist k bina ho sakta tah apan 3 array list bana lete har type k liye par yeah better approach h
    public ArrayList<StructArraylist> fetchContact(){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        // .rawQuery cursor return karta h
        Cursor cursor=sqLiteDatabase.rawQuery(" SELECT * FROM "+ TABLE_NAME,null);
        //aab cursor mil gaya h toh while loop use hoga jisse hum table k end tak ka data read kar paye
        ArrayList<StructArraylist> structArraylists=new ArrayList<>();
        while (cursor.moveToNext()){
            StructArraylist arraylist=new StructArraylist();
            arraylist.id=cursor.getInt(0);
            arraylist.name=cursor.getString(1);
            arraylist.phone_number=cursor.getString(2);
            structArraylists.add(arraylist);
        }
        cursor.close();
        return  structArraylists;
    }
}
