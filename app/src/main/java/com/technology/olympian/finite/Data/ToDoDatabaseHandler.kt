package com.technology.olympian.finite.Data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import com.awesome.shorty.AwesomeToast
import com.technology.olympian.finite.Model.*

/**
 * Created by Yash on 26-03-2018.
 */
class ToDoDatabaseHandler(var context: Context): SQLiteOpenHelper(context, dbName,null, dbVersion) {
    override fun onCreate(db: SQLiteDatabase?) {
        var createTable = "CREATE TABLE $tableName($id INTEGER PRIMARY KEY,$name TEXT,$assigned TEXT,$date TEXT);"
        db!!.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
       db!!.execSQL("DROP TABLE IF EXISTS $tableName;")
        onCreate(db)
    }

    fun createItem(task:ToDoItem){

        var db:SQLiteDatabase = writableDatabase

        var values = ContentValues()
        values.put(id,task.getId())
        values.put(name,task.getName())
        values.put(assigned,task.getAssignedBy())
        values.put(date,task.getDate())




        var result  = db.insert(tableName,null,values)
        if(result == (-1).toLong())
        {
            AwesomeToast.error(context,"Unable to create Task",Toast.LENGTH_SHORT).show()
        }
        else{
            AwesomeToast.success(context,"Data Inserted",Toast.LENGTH_SHORT).show()
        }
        Log.d("Function","createItem")
    }

    fun readItem(id:String){

        var db:SQLiteDatabase = readableDatabase
        var cursor:Cursor = db.query(tableName, arrayOf(com.technology.olympian.finite.Model.id, name, assigned, date), "${com.technology.olympian.finite.Model.id} =?", arrayOf(com.technology.olympian.finite.Model.id.toString()),null,null,null,null)

        if(cursor != null)
        {
            cursor.moveToFirst()

            var task = ToDoItem()
            task.setName(cursor.getString(cursor.getColumnIndex(name)))
            task.setAssignedBy(cursor.getString(cursor.getColumnIndex(assigned)))
            task.setDate(cursor.getString(cursor.getColumnIndex(date)))
            task.setId(cursor.getInt(cursor.getColumnIndex(com.technology.olympian.finite.Model.id)))
            Log.d("Function","readItem")
        }
    }

    fun readAllItems():ArrayList<ToDoItem>{

        var db:SQLiteDatabase = readableDatabase

        var list:ArrayList<ToDoItem> = ArrayList()

        var select = " SELECT * FROM $tableName"

        var cursor:Cursor = db.rawQuery(select,null)

        if(cursor.moveToFirst())
        {
            do {
                var task = ToDoItem()
                task.setName(cursor.getString(cursor.getColumnIndex(name)))
                task.setAssignedBy(cursor.getString(cursor.getColumnIndex(assigned)))
                task.setDate(cursor.getString(cursor.getColumnIndex(date)))
                task.setId(cursor.getInt(cursor.getColumnIndex(id)))

                list.add(task)
            }while (cursor.moveToNext())
        }
        Log.d("Function","readAllItems")
        return list
    }

    fun updateItem(task: ToDoItem):Int{
        var db:SQLiteDatabase = writableDatabase

        var values = ContentValues()
        values.put(id,task.getId())
        values.put(name,task.getName())
        values.put(assigned,task.getAssignedBy())
        values.put(date,task.getDate())

        return db.update(tableName,values, "${id}=?", arrayOf(task.getId().toString()))
    }

    fun deleteItem(task: ToDoItem){
        var db:SQLiteDatabase = writableDatabase
        db.delete(tableName, "${id}=?", arrayOf(task.getId().toString()))
        db.close()
    }

    fun getItemCount():Int{
        var db:SQLiteDatabase = readableDatabase
        var count = "SELECT * FROM $tableName;"
        var cursor:Cursor = db.rawQuery(count,null)
        return cursor.count
    }
}