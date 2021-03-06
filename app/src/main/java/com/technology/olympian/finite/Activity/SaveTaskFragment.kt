package com.technology.olympian.finite.Activity


import android.os.Bundle
import android.support.v4.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.awesome.shorty.AwesomeToast
import com.franmontiel.fullscreendialog.FullScreenDialogContent
import com.franmontiel.fullscreendialog.FullScreenDialogController
import com.shagi.materialdatepicker.date.DatePickerFragmentDialog
import com.technology.olympian.finite.Data.ToDoItem
//import android.support.v4.animation.AnimatorCompatHelper
import com.technology.olympian.finite.R
import kotlinx.android.synthetic.main.fragment_save_task.view.*

import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class SaveTaskFragment : FullScreenDialogContent, Fragment() {

    private var dialogController: FullScreenDialogController? = null

    override fun onConfirmClick(dialogController: FullScreenDialogController?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDialogCreated(dialogController: FullScreenDialogController?) {
        this.dialogController = dialogController
    }

    override fun onDiscardClick(dialogController: FullScreenDialogController?): Boolean {
        dialogController!!.discard()
        return true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_save_task, container, false)



        val now = Calendar.getInstance()
        var cal = v.findViewById<ImageButton>(R.id.calendarBtn)
        var taskName = v.findViewById<EditText>(R.id.taskText)
        var date = v.findViewById<TextView>(R.id.dateText)
        var assignedBy = v.findViewById<EditText>(R.id.assignedByText)
        cal.setOnClickListener {


            val dialog = DatePickerFragmentDialog.newInstance({ view, year, monthOfYear, dayOfMonth ->

                v.dateText.text = "$dayOfMonth/$monthOfYear/$year"
            }, Calendar.YEAR+2017, Calendar.MONTH+1, Calendar.DAY_OF_MONTH+25)

            dialog.show(fragmentManager, "tag")

        }
        v.dateText.setOnClickListener {

            val dialog = DatePickerFragmentDialog.newInstance({ view, year, monthOfYear, dayOfMonth ->

                v.dateText.text = "$dayOfMonth/$monthOfYear/$year"
            }, Calendar.YEAR+2017, Calendar.MONTH+1, Calendar.DAY_OF_MONTH+25)

            dialog.show(fragmentManager, "tag")


        }

        val save = v.findViewById<Button>(R.id.saveBtn)

        save.setOnClickListener {

            if (taskName.text.toString().trim() != "" && date.text.toString().trim() != "" && assignedBy.text.toString().trim() != "") {
                var task = ToDoItem()

                task.setId(id)
                task.setName(taskName.text.toString())
                task.setDate(date.text.toString())
                task.setAssignedBy(assignedBy.text.toString())
                task.setName(taskName.text.toString())


            } else {
                AwesomeToast.error(activity, "Enter all fields", Toast.LENGTH_SHORT).show()
            }

        }
        return v
    }


}// Required empty public constructor
