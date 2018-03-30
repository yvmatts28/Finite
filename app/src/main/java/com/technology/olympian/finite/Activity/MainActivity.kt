package com.technology.olympian.finite.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.franmontiel.fullscreendialog.FullScreenDialogFragment
import com.technology.olympian.finite.Data.ToDoAdapter
import com.technology.olympian.finite.Data.ToDoDatabaseHandler
import com.technology.olympian.finite.Data.ToDoItem
import com.technology.olympian.finite.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*


class MainActivity : AppCompatActivity(),FullScreenDialogFragment.OnDiscardListener {

    var dbHandler:ToDoDatabaseHandler? = null
    var adapter:ToDoAdapter? = null
    var list:ArrayList<ToDoItem>? = null
    var layoutManager:RecyclerView.LayoutManager? = null

    override fun onDiscard() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHandler = ToDoDatabaseHandler(this)
        list = ArrayList<ToDoItem>()
        list = dbHandler!!.readAllItems()
        layoutManager = LinearLayoutManager(this)
        adapter = ToDoAdapter(list!!,this)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter



        fab_menu.addTask.setOnClickListener {

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
