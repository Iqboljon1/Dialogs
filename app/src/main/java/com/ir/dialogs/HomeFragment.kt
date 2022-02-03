package com.ir.dialogs

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {
    private var boolean = true
    lateinit var root: View
    lateinit var dialogAlertDialog: AlertDialog
    lateinit var dialogCustomAlertDialog: AlertDialog
    lateinit var dialogFragment: AlertDialog
    private lateinit var dialogDatePickerDialog: DatePickerDialog
    lateinit var dialogTimePickerDialog: TimePickerDialog
    lateinit var dialogBottomSheetDialog: BottomSheetDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        root = inflater.inflate(R.layout.fragment_home, container, false)

        root.card_alertDialog.setOnClickListener {
            if (boolean) {
                boolean = false
                buildAlertDialog()
                dialogAlertDialog.show()
            }
        }

        root.card_customDialog.setOnClickListener {
            if (boolean) {
                boolean = false
                buildCustomDialog()
                dialogCustomAlertDialog.show()
            }
        }

        root.card_fragmentDialog.setOnClickListener {
            if (boolean) {
                buildFragmentDialog()
                dialogFragment.show()
            }
        }

        root.card_datePickerDialog.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                dialogDatePickerDialog = DatePickerDialog(requireActivity())
                dialogDatePickerDialog.setOnDateSetListener { view, year, month, dayOfMonth ->
                    Toast.makeText(requireContext(),
                        "${dayOfMonth}.${month + 1}.$year",
                        Toast.LENGTH_SHORT).show()
                }
                dialogDatePickerDialog.show()
            }
        }

        root.card_timePickerDialog.setOnClickListener {
            dialogTimePickerDialog = TimePickerDialog(
                requireContext(),
                { _, hourOfDay, minute ->
                    Toast.makeText(requireContext(),
                        "$hourOfDay:$minute",
                        Toast.LENGTH_SHORT).show()
                },
                24,
                60,
                true
            )
            dialogTimePickerDialog.show()
        }

        root.card_bottomSheetDialog.setOnClickListener {
            if (boolean) {
                boolean = false
                buildBottomSheetDialog()
                dialogBottomSheetDialog.show()
            }
        }

        root.card_snackBar.setOnClickListener {
            val snackbar = Snackbar.make(it, "Salom snackbar", Snackbar.LENGTH_LONG)

            snackbar.setAction("Click"
            ) { Toast.makeText(requireActivity(), "Click", Toast.LENGTH_SHORT).show() }

            snackbar.show()
        }

        return root
    }

    private fun buildBottomSheetDialog() {
        val bottomSheetDialog = BottomSheetDialog(requireActivity())
        val view = layoutInflater.inflate(R.layout.dialog2, null, false)
        val okay = view.findViewById<CardView>(R.id.card_okay)
        bottomSheetDialog.setCancelable(false)
        val cancel = view.findViewById<TextView>(R.id.tv_cancel)

        okay.setOnClickListener {
            dialogBottomSheetDialog.cancel()
        }

        cancel.setOnClickListener {
            dialogBottomSheetDialog.cancel()
        }

        bottomSheetDialog.setOnCancelListener {
            boolean = true
        }

        bottomSheetDialog.setContentView(view)
        dialogBottomSheetDialog = bottomSheetDialog
    }


    private fun buildFragmentDialog() {
        val alertDialog = AlertDialog.Builder(requireActivity())
        val view = layoutInflater.inflate(R.layout.fragment_my_dialog, null, false)
        val ok = view.findViewById<CardView>(R.id.card_ok)
        ok.setOnClickListener {
            dialogFragment.cancel()
        }

        alertDialog.setOnCancelListener {
            boolean = true
        }

        alertDialog.setView(view)
        dialogFragment = alertDialog.create()
        dialogFragment.window!!.attributes.windowAnimations = R.style.MyAnimation
    }

    private fun buildAlertDialog() {
        val alertDialog = AlertDialog.Builder(requireActivity())
        alertDialog.setTitle("Alert Dialog")
        alertDialog.setMessage("Hello World")
        alertDialog.setCancelable(false)

        alertDialog.setPositiveButton("Yes"
        ) { _, _ -> dialogAlertDialog.cancel() }

        alertDialog.setNegativeButton("No"
        ) { _, _ -> dialogAlertDialog.cancel() }

        alertDialog.setOnCancelListener {
            boolean = true
        }

        dialogAlertDialog = alertDialog.create()
        dialogAlertDialog.window!!.attributes.windowAnimations = R.style.MyAnimation
    }

    private fun buildCustomDialog() {
        val alertDialog = AlertDialog.Builder(requireActivity())
        val view = layoutInflater.inflate(R.layout.dialog2, null, false)
        val okay = view.findViewById<CardView>(R.id.card_okay)
        alertDialog.setCancelable(false)
        val cancel = view.findViewById<TextView>(R.id.tv_cancel)
        okay.setOnClickListener {
            dialogCustomAlertDialog.cancel()
        }
        cancel.setOnClickListener {
            dialogCustomAlertDialog.cancel()
        }

        alertDialog.setOnCancelListener {
            boolean = true
        }
        alertDialog.setView(view)
        dialogCustomAlertDialog = alertDialog.create()
        dialogCustomAlertDialog.window!!.attributes.windowAnimations = R.style.MyAnimation
        dialogCustomAlertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}