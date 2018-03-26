package com.technology.olympian.finite.Data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * Created by Yash on 25-03-2018.
 */
class ToDoItem  {

    var taskId : Int? = null
    var taskAssignedBy:String?= null
    var taskName:String? = null
    var taskDate:String? = null

    fun setId(id:Int){
        this.taskId = id
    }

    fun getId(): Int? {
        return taskId
    }

    fun setAssignedBy(assgnBy:String)
    {
        this.taskAssignedBy = assgnBy
    }

    fun getAssignedBy(): String? {
        return this.taskAssignedBy
    }

    fun setName(name:String){
        this.taskName = name
    }

    fun getName(): String? {
        return taskName
    }

    fun setDate(due:String){
        this.taskDate = due
    }

    fun getDate(): String? {
        return this.taskDate
    }
}