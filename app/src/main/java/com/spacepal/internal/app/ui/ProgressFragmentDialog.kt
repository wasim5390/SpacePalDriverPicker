package com.spacepal.internal.app.ui

import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.spacepal.internal.app.R


class ProgressFragmentDialog : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_FRAME, 0)
    }

    override fun onStart() {
        super.onStart()
        val d = dialog
        if (d != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            if (d.window != null)
                d.window!!.setLayout(width, height)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_progress, container, false)
        (view.findViewById(R.id.progressBar) as ProgressBar)
                .indeterminateDrawable
                .setColorFilter(ContextCompat.getColor(context!!, R.color.colorAccent), PorterDuff.Mode.SRC_IN)
        return view
    }

    companion object {

        fun newInstance(): ProgressFragmentDialog {
            val args = Bundle()
            val fragment = ProgressFragmentDialog()
            fragment.arguments = args
            return fragment
        }
    }
}
