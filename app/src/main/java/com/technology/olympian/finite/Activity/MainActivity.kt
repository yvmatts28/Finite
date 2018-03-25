package com.technology.olympian.finite.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.technology.olympian.finite.Data.ToDoItem
import com.technology.olympian.finite.R
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults
import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView
import com.technology.olympian.finite.Data.ToDoAdapter
import io.realm.Sort



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        var realm = Realm.getInstance(this as RealmConfiguration)
//        var list:RealmResults<ToDoItem> = realm
//                                        .where(>)

       var realm = Realm.getInstance(this as RealmConfiguration)
        val list = realm
                .where(ToDoItem::class.java)
                .findAllSorted("taskId",Sort.ASCENDING)
        val toDoRealmAdapter = ToDoAdapter(list, this, true, true)
        val realmRecyclerView = findViewById<View>(R.id.realm_recycler_view) as RealmRecyclerView
        realmRecyclerView.setAdapter(toDoRealmAdapter)
    }
}
