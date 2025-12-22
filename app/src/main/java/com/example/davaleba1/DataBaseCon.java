package com.example.davaleba1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.core.util.Pair;

import java.util.ArrayList;

public class DataBaseCon extends SQLiteOpenHelper {
    private static final String DataBaseName="DataBase.db";
    private static final int DataBaseVersion=1;
    public DataBaseCon(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){super(context,name,factory,version);}
    public DataBaseCon(Context context){
        super(context,DataBaseName,null,DataBaseVersion);
    }

    public void onCreate(SQLiteDatabase db) {

    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    boolean CheckTable(String tableName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="EXISTS(SELECT 1 FROM "+tableName+")";
        Cursor cursor = db.rawQuery(sql,null);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }
    public ArrayList<String> SelectTable(String tableName){
        ArrayList<String> result = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + tableName, null);
        if (cursor.moveToFirst()) {
            String[] columns = cursor.getColumnNames();
            do {
                StringBuilder st = new StringBuilder();

                for (String column : columns) {
                    int index = cursor.getColumnIndex(column);
                    st.append(column)
                            .append(": ")
                            .append(cursor.getString(index))
                            .append("  ");
                }
                result.add(st.toString());
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }
    public void DeleteTable(String tableName){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+tableName);
    }
    public void DeleteColumn(String tableName,String ColumnName){
        SQLiteDatabase db = this.getWritableDatabase();
      //  db.execSQL("ALTER TABLE "+tableName+" DROP COLUMN "+ColumnName);
    }
    public ArrayList<String>TablesName(){
        ArrayList<String>Tables = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT name FROM sqlite_master " +
                "WHERE type='table' AND name NOT LIKE 'sqlite_%' " +
                "AND name != 'android_metadata'";
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                Tables.add(cursor.getString(0));
            }while(cursor.moveToNext());
        }
        cursor.close();
        return Tables;
    }
    public ArrayList<String>getColumns(String tableName){
        ArrayList<String>Columns = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "PRAGMA table_info("+tableName+")";
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                String Name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String Type = cursor.getString(cursor.getColumnIndexOrThrow("type"));
                Columns.add(Name+"   Type -->"+Type);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return Columns;
    }
    public ArrayList<String>getColumnsName(String tableName){
        ArrayList<String>Columns = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "PRAGMA table_info("+tableName+")";
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                String Name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                Columns.add(Name);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return Columns;
    }
    public Pair<ArrayList<String>,ArrayList<String>> getClmn(String tableName){
        ArrayList<String>Columns = new ArrayList<>();
        ArrayList<String>ColumnsTp = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "PRAGMA table_info("+tableName+")";
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                String Name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String Type = cursor.getString(cursor.getColumnIndexOrThrow("type"));
                Columns.add(Name);
                ColumnsTp.add(Type);
            } while(cursor.moveToNext());
        }
        cursor.close();
        return new Pair<>(Columns,ColumnsTp);
    }
    public void CreateTbl(String sql){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
    }
    public void InsertValues(String tableName,ArrayList<String>Values){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        for(int i=0;i<Values.size();i++){
            cv.put(getColumns(tableName).get(i),Values.get(i));
        }
        db.insert(tableName,null,cv);
    }
}
