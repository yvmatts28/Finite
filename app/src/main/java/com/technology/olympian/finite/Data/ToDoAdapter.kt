package com.technology.olympian.finite.Data

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.technology.olympian.finite.R

/**
 * Created by Yash on 25-03-2018.
 */
class ToDoAdapter(private val list:MutableList<ToDoItem>,private val context: Context):RecyclerView.Adapter<ToDoAdapter.ViewHolder>(){




    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder{
        val v = LayoutInflater.from(context).inflate(R.layout.task_row,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder:ViewHolder ?, position: Int) {
        holder!!.bindViews(list[position])
    }


    fun addItem(t:ToDoItem){
        list.add(t)
        notifyItemInserted(list.size)
    }

    fun removeAt(position: Int){

        list.removeAt(position)
        notifyItemRemoved(position)

    }



    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {


        var tName = itemView.findViewById<TextView>(R.id.displayName)
        var tDate = itemView.findViewById<TextView>(R.id.dateDisplay)
        var tAssignedBy = itemView.findViewById<TextView>(R.id.displayAssignedBy)

        fun bindViews(task:ToDoItem){

            tName.text = task.getName()
            tDate.text = task.getDate()
            tAssignedBy.text = task.getAssignedBy()
        }
    }

}