package com.technology.olympian.finite.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.franmontiel.fullscreendialog.FullScreenDialogContent
import com.technology.olympian.finite.Data.ToDoItem
import com.technology.olympian.finite.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import com.franmontiel.fullscreendialog.FullScreenDialogFragment




class MainActivity : AppCompatActivity(),FullScreenDialogFragment.OnDiscardListener {
    override fun onDiscard() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
