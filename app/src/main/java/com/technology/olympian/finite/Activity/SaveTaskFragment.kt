package com.technology.olympian.finite.Activity


import android.os.Bundle
import android.support.v4.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.franmontiel.fullscreendialog.FullScreenDialogContent
import com.franmontiel.fullscreendialog.FullScreenDialogController

import com.technology.olympian.finite.R


/**
 * A simple [Fragment] subclass.
 */
class SaveTaskFragment : FullScreenDialogContent, Fragment(){

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

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_save_task,container,false)
        return v
    }
}// Required empty public constructor
