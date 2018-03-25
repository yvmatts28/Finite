package com.technology.olympian.finite.Data

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.technology.olympian.finite.R
import io.realm.RealmBasedRecyclerViewAdapter
import io.realm.RealmResults
import io.realm.RealmViewHolder

/**
 * Created by Yash on 25-03-2018.
 */
class ToDoAdapter(private val list: RealmResults<ToDoItem>, context: Context,private val automaticUpdate:Boolean,private val animateResults:Boolean): RealmBasedRecyclerViewAdapter<ToDoItem, ToDoAdapter.ViewHolder>(context,list,automaticUpdate,animateResults) {
    override fun onBindRealmViewHolder(p0: ViewHolder?, position: Int) {
       val item = list[position]
        p0!!.bindViews(item)

    }

    override fun onCreateRealmViewHolder(p0: ViewGroup?, p1: Int): ViewHolder {
        val v = inflater.inflate(R.layout.task_row,p0,false)
        return ViewHolder(v)
    }

    class ViewHolder(itemView:View): RealmViewHolder(itemView) {

        var tName = itemView.findViewById<TextView>(R.id.displayName)
        var tDate = itemView.findViewById<TextView>(R.id.dateDisplay)
        var tAssignedBy = itemView.findViewById<TextView>(R.id.displayAssignedBy)

        fun bindViews(t:ToDoItem){
            tName.text = t.getName().toString()
            tDate.text = t.getDate().toString()
            tAssignedBy.text = t.getAssignedBy().toString()
        }
    }
}