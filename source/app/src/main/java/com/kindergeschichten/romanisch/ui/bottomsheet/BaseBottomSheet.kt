package com.kindergeschichten.romanisch.ui.bottomsheet


import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


open class BaseBottomSheet(val showFullScreen:Boolean=false,val isTransparent:Boolean=false): BottomSheetDialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog =
            BottomSheetDialog(requireContext())
        bottomSheetDialog.window?.setDimAmount(0f)
        bottomSheetDialog.setCanceledOnTouchOutside(true)
        bottomSheetDialog.setOnShowListener { dialog ->
            val dialogc =
                dialog as BottomSheetDialog
            val bottomSheet: FrameLayout? =
                dialogc.findViewById(com.google.android.material.R.id.design_bottom_sheet)
            if(showFullScreen)
                setupFullHeight(bottomSheet!!)
            val behavior = BottomSheetBehavior.from(bottomSheet!!)
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.isDraggable = false
            if(isTransparent) {
                bottomSheet.setBackgroundColor(Color.TRANSPARENT)
                // bottomSheet.setBackgroundResource(R.drawable.upper_curve)
            }
        }

        return bottomSheetDialog
    }

    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams

        val behavior = BottomSheetBehavior.from(bottomSheet)
        behavior.peekHeight = 5000
    }

    var base: AppCompatActivity? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.base = context as? AppCompatActivity
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        this.base = activity as? AppCompatActivity
    }

    override fun onDetach() {
        super.onDetach()
        base = null
    }
}