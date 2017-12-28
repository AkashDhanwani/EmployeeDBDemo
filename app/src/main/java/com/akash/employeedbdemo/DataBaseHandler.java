package com.akash.employeedbdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.StringBuilderPrinter;
import android.widget.Toast;

import static android.R.attr.id;

/**
 * Created by AKASH on 1/29/2017.
 */

public class DataBaseHandler extends SQLiteOpenHelper {

    Context context;
    SQLiteDatabase db;

    DataBaseHandler(Context context)
    {
        super(context,"employeedb",null,1);
        this.context = context;
        db = this.getWritableDatabase();
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "create table employee(id integer primary key,name text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public void addRecord(int id,String name)
    {
        ContentValues values = new ContentValues();
        values.put("id",id);
        values.put("name",name);
        long rid = db.insert("employee",null,values);
        if(rid<0)
        {
            Toast.makeText(context, "Insert issue", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(context, "Insert success", Toast.LENGTH_LONG).show();
        }
    }

    public String getRecord()
    {
        Cursor cursor = db.query("employee",null,null,null,null,null,null);
        StringBuffer sb = new StringBuffer();

        cursor.moveToFirst();

        if(cursor.getCount() > 0)
        {
            do {
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                sb.append("Id= " + id + "   Name= " +name+ "\n");
            }while(cursor.moveToNext());
        }
        else
        {
            sb.append("No records to show");
        }
        return sb.toString();
    }

    public void removeRecord(int id)
    {
        long rid = db.delete("employee", "id="+id,null);
        if(rid == 0)
        {
            Toast.makeText(context, "Zero records deleted", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, rid+" Records deleted", Toast.LENGTH_SHORT).show();
        }
    }

    public void modifyRecord(int id, String name)
    {
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("name", name);
        long rid = db.update("employee",values, "id="+id,null);
        if(rid == 0)
        {
            Toast.makeText(context, "0 records updated", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, rid+" records updated", Toast.LENGTH_SHORT).show();
        }
    }
}



