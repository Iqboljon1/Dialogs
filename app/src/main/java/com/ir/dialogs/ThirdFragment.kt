package com.ir.dialogs

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import kotlinx.android.synthetic.main.fragment_third.view.*

class ThirdFragment : Fragment() {
    var boolean = true
    lateinit var root: View
    lateinit var dialog: AlertDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        root = inflater.inflate(R.layout.fragment_third, container, false)
        root.card_showAwesomeDialog.setOnClickListener {
            if (boolean){
                boolean = false
                buildDialog()
                dialog.show()
            }
        }
        return root
    }

    private fun buildDialog() {
        val alertDialog = AlertDialog.Builder(requireActivity())
        val view = layoutInflater.inflate(R.layout.dialog2 , null , false)
        val okay = view.findViewById<CardView>(R.id.card_okay)
        val cancel = view.findViewById<TextView>(R.id.tv_cancel)
        okay.setOnClickListener {
            dialog.cancel()
        }
        cancel.setOnClickListener {
            dialog.cancel()
        }

        alertDialog.setOnCancelListener {
            boolean = true
        }
        alertDialog.setView(view)
        dialog = alertDialog.create()
        dialog.window!!.attributes.windowAnimations = R.style.MyAnimation
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

}