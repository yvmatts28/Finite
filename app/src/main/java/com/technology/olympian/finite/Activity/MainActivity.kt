package com.technology.olympian.finite.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import com.franmontiel.fullscreendialog.FullScreenDialogFragment
import com.technology.olympian.finite.Data.ToDoAdapter
import com.technology.olympian.finite.Data.ToDoDatabaseHandler
import com.technology.olympian.finite.Data.ToDoItem
import com.technology.olympian.finite.Model.SwipeToDeleteCallback
import com.technology.olympian.finite.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),FullScreenDialogFragment.OnDiscardListener {

    var dbHandler:ToDoDatabaseHandler? = null
    var adapter:ToDoAdapter? = null
    var list:MutableList<ToDoItem>? = null
    var layoutManager:RecyclerView.LayoutManager? = null

    override fun onDiscard() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView3.visibility = View.INVISIBLE
        textView4.visibility = View.INVISIBLE


        var t = ToDoItem()
        t.setId(1)
        t.setName("Digital Assignment")
        t.setDate("2 March")
        t.setAssignedBy("Software")

        dbHandler = ToDoDatabaseHandler(this)

        list = ArrayList()
        list = dbHandler!!.readAllItems()
        list!!.add(t)

        if((list as ArrayList<ToDoItem>).isEmpty()){
            imageView3.visibility = View.VISIBLE
            textView4.visibility = View.VISIBLE
        }
        else
        {
            imageView3.visibility = View.INVISIBLE
            textView4.visibility = View.INVISIBLE
        }
        recyclerView.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        layoutManager = LinearLayoutManager(this)
        adapter = ToDoAdapter(list!!,this)

        recyclerView.layoutManager = layoutManager

        recyclerView.adapter = adapter
        val swipeHandler = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = recyclerView.adapter as ToDoAdapter
                adapter.removeAt(viewHolder.adapterPosition)
                if((list as ArrayList<ToDoItem>).isEmpty()){

                    imageView3.visibility = View.VISIBLE
                    textView4.visibility = View.VISIBLE
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerView)



        fab_add.setOnClickListener {

            var saveFragment = FullScreenDialogFragment.Builder(this@MainActivity)
                    .setTitle(" ")
                    //.setConfirmButton()
                    //.setOnConfirmListener(onConfirmListener)
                    //.setOnDiscardListener(onDiscardListener)
                    .setContent(SaveTaskFragment::class.java,null)
                    .build()
            saveFragment.show(supportFragmentManager,"Save Task")

        }
    }


}
