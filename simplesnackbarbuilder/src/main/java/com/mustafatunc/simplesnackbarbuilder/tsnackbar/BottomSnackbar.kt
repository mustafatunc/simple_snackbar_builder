package com.mustafatunc.simplesnackbarbuilder.tsnackbar

import android.os.Build
import android.support.design.widget.Snackbar
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.mustafatunc.simplesnackbarbuilder.SSnackbar
import java.util.*

internal class BottomSnackbar : SSnackbar(),
    ISnackbarTopBottom {

    private lateinit var snack: Snackbar

    companion object {
        private val queue = ArrayDeque<Snackbar>()
    }

    override fun show() {
        queue.add(snack)
        if (queue.first == snack) {
            snack.show()
        }
    }

    override fun dismiss() {
        snack.dismiss()
    }

    override fun isShowing(): Boolean {
        return snack.isShown
    }

    override fun make(view: View, text: CharSequence, duration: Duration) {
        val d = getDuration(duration)
        snack = Snackbar.make(view, text, d)
        snack.addCallback(object : Snackbar.Callback() {
            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                super.onDismissed(transientBottomBar, event)
                if (queue.isNotEmpty()) {
                    queue.pop()
                }
                if (queue.isNotEmpty()) {
                    queue.first.show()
                }
            }
        })
    }

    override fun setTextColor(color: Int) {
        val tv = snack.view.findViewById<TextView>(android.support.design.R.id.snackbar_text)
        tv.setTextColor(color)
    }

    override fun textAlignment(alignment: Alignment) {
        val tv = snack.view.findViewById<TextView>(android.support.design.R.id.snackbar_text)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tv.textAlignment =
                if (alignment == Alignment.CENTER) View.TEXT_ALIGNMENT_CENTER else View.TEXT_ALIGNMENT_TEXT_START
        } else {
            tv.gravity = if (alignment == Alignment.CENTER) Gravity.CENTER else Gravity.START
        }
    }

    override fun setAction(text: CharSequence, color: Int, callback: View.OnClickListener) {
        snack.setAction(text, callback)
        snack.setActionTextColor(color)
    }

    override fun backgroundColor(color: Int) {
        snack.view.setBackgroundColor(color)
    }

    override fun setDuration(duration: Duration) {
        snack.duration = getDuration(duration)
    }

    private fun getDuration(duration: SSnackbar.Duration) = when (duration) {
        Duration.LENGTH_SHORT -> Snackbar.LENGTH_SHORT
        Duration.LENGTH_LONG -> Snackbar.LENGTH_LONG
        Duration.LENGTH_INDEFINITE -> Snackbar.LENGTH_INDEFINITE
    }
}