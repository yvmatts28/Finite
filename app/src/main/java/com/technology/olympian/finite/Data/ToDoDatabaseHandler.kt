package com.technology.olympian.finite.Data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.technology.olympian.finite.Model.*

/**
 * Created by Yash on 26-03-2018.
 */
class ToDoDatabaseHandler(context: Context): SQLiteOpenHelper(context, dbName,null, dbVersion) {
    override fun onCreate(db: SQLiteDatabase?) {
        var createTable = "CREATE TABLE ${tableName}(${task_id} INTEGER PRIMARY KEY,${task_name} TEXT,${assigned_by} TEXT,${task_date} TEXT)"
        db!!.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
       db!!.execSQL("DROP TABLE IF EXISTS ${tableName}")
        onCreate(db)
    }

    fun createItem(task:ToDoItem){

        var db:SQLiteDatabase = writableDatabase

        var values:ContentValues = ContentValues()
        values.put(task_id,task.getId())
        values.put(task_name,task.getName())
        values.put(assigned_by,task.getAssignedBy())
        values.put(task_date,task.getDate())

        db.insert(tableName,null,values)
    }

    fun readItem(id:String){

        var db:SQLiteDatabase = readableDatabase
        var cursor:Cursor = db.query(tableName, arrayOf(task_id, task_name, assigned_by, task_date), "${task_id} =?", arrayOf(task_id.toString()),null,null,null,null)

        if(cursor != null)
        {
            cursor.moveToFirst()

            var task = ToDoItem()
            task.setName(cursor.getString(cursor.getColumnIndex(task_id)))
            task.setAssignedBy(cursor.getString(cursor.getColumnIndex(assigned_by)))
            task.setDate(cursor.getString(cursor.getColumnIndex(task_date)))
            task.setId(cursor.getInt(cursor.getColumnIndex(task_id)))
        }
    }

    fun readAllItems():ArrayList<ToDoItem>{

        var db:SQLiteDatabase = readableDatabase

        var list:ArrayList<ToDoItem> = ArrayList()

        var select = " SELECT * FROM ${tableName}"

        var cursor:Cursor = db.rawQuery(select,null)

        if(cursor.moveToFirst())
        {
            do {
                var task = ToDoItem()
                task.setName(cursor.getString(cursor.getColumnIndex(task_id)))
                task.setAssignedBy(cursor.getString(cursor.getColumnIndex(assigned_by)))
                task.setDate(cursor.getString(cursor.getColumnIndex(task_date)))
                task.setId(cursor.getInt(cursor.getColumnIndex(task_id)))

                list.add(task)
            }while (cursor.moveToNext())
        }
        return list
    }

    fun updateItem(task: ToDoItem):Int{
        var db:SQLiteDatabase = writableDatabase

        var values:ContentValues = ContentValues()
        values.put(task_id,task.getId())
        values.put(task_name,task.getName())
        values.put(assigned_by,task.getAssignedBy())
        values.put(task_date,task.getDate())

        return db.update(tableName,values, "${task_id}=?", arrayOf(task.getId().toString()))
    }

    fun deleteItem(task: ToDoItem){
        var db:SQLiteDatabase = writableDatabase
        db.delete(tableName, "${task_id}=?", arrayOf(task.getId().toString()))
        db.close()
    }

    fun getItemCount():Int{
        var db:SQLiteDatabase = readableDatabase
        var count = "SELECT * FROM ${tableName}"
        var cursor:Cursor = db.rawQuery(count,null)
        return cursor.count
    }
}