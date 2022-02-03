package com.ir.dialogs

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import kotlinx.android.synthetic.main.fragment_second.view.*
import java.util.*

class SecondFragment : Fragment() {
    var countDownTimer = object : CountDownTimer(10 , 1) {
        override fun onTick(millisUntilFinished: Long) {
            boolean = false
        }

        override fun onFinish() {
            dialog.show()
        }
    }
    var boolean = true
    lateinit var root: View
    lateinit var dialog: AlertDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        root = inflater.inflate(R.layout.fragment_second, container, false)

        root.card_showDialog.setOnClickListener {
            if (boolean){
                buildDialog()
                dialog.show()
            }
        }

        return root
    }

    private fun buildDialog() {
        boolean = false
        val alertDialog = AlertDialog.Builder(requireActivity())
        val view = layoutInflater.inflate(R.layout.dialog1, null, false)
        val cancel = view.findViewById<CardView>(R.id.card_cancel)
        val retry = view.findViewById<CardView>(R.id.card_retry)

        cancel.setOnClickListener {
            dialog.cancel()
        }
        retry.setOnClickListener {
            dialog.cancel()
            countDownTimer.start()
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