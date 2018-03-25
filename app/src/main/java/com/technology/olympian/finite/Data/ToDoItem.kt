package com.technology.olympian.finite.Data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * Created by Yash on 25-03-2018.
 */
class ToDoItem : RealmObject() {

    @PrimaryKey
    var taskId : Long? = null
    var taskAssignedBy:String?= null
    var taskName:String? = null
    var taskDate:Date? = null

    fun setId(id:Long){
        this.taskId = id
    }

    fun getId(): Long? {
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

    fun setDate(due:Date){
        this.taskDate = due
    }

    fun getDate(): Date? {
        return this.taskDate
    }
}