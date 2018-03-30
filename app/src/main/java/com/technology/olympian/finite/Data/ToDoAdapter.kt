package com.technology.olympian.finite.Data

import android.animation.ValueAnimator
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.agilie.swipe2delete.ModelOptions
import com.agilie.swipe2delete.SwipeToDeleteDelegate
import com.agilie.swipe2delete.interfaces.IAnimationUpdateListener
import com.agilie.swipe2delete.interfaces.IAnimatorListener
import com.agilie.swipe2delete.interfaces.ISwipeToDeleteAdapter
import com.agilie.swipe2delete.interfaces.ISwipeToDeleteHolder
import com.technology.olympian.finite.R
import io.realm.RealmBasedRecyclerViewAdapter
import io.realm.RealmResults
import io.realm.RealmViewHolder

/**
 * Created by Yash on 25-03-2018.
 */
class ToDoAdapter(private val list:ArrayList<ToDoItem>,private val context: Context):RecyclerView.Adapter<ToDoAdapter.ViewHolder>(),ISwipeToDeleteAdapter<Int,ToDoItem,ToDoAdapter.ViewHolder>, IAnimationUpdateListener, IAnimatorListener {

    val swipeToDeleteDelegate = SwipeToDeleteDelegate(items = list, swipeToDeleteAdapter = this)
    var animationEnabled = true
    var bottomContainer = true




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



    override fun findItemPositionByKey(key: Int) = (0..list.lastIndex).firstOrNull{list[it].getId() == key}?: -1

    override fun onBindCommonItem(holder: ViewHolder, key: Int, item: ToDoItem, position: Int) {
        holder.apply {
            var tName = itemView.findViewById<TextView>(R.id.displayName)
            var tDate = itemView.findViewById<TextView>(R.id.dateDisplay)
            var tAssignedBy = itemView.findViewById<TextView>(R.id.displayAssignedBy)

            tName.text = item.getName().toString()
            tDate.text = item.getDate().toString()
            tAssignedBy.text = item.getAssignedBy().toString()
        }
    }

    override fun removeItem(key: Int) {
        swipeToDeleteDelegate.removeItem(key)
    }


    override fun onAnimationUpdated(animation: ValueAnimator?, options: ModelOptions<*>) {
       TODO()
    }



    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView),ISwipeToDeleteHolder<Int> {

        override var key: Int = -1

        override var pendingDelete: Boolean = false

        override val topContainer: View
            get() = TODO()

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