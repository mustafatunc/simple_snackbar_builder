package com.mustafatunc.simplesnackbarbuilder.tsnackbar

import android.view.View
import com.mustafatunc.simplesnackbarbuilder.SSnackbar
import java.util.*

internal class TopSnackbar : SSnackbar(),
    ISnackbarTopBottom {
    private lateinit var snack: TSnackbar

    companion object {
        private val queue = ArrayDeque<TSnackbar>()
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

    //---------------------------------------------//

    override fun make(view: View, text: CharSequence, duration: Duration) {
        val d = getDuration(duration)
        snack = TSnackbar.make(view, text, d)
        snack.setCallback(object : TSnackbar.Callback() {
            override fun onDismissed(transientBottomBar: TSnackbar?, event: Int) {
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
        snack.setTextColor(color)
    }

    override fun textAlignment(alignment: Alignment) {
        val a = when (alignment) {
            Alignment.LEFT -> TSnackbar.Alignment.LEFT
            Alignment.CENTER -> TSnackbar.Alignment.CENTER
        }
        snack.setTextAlignment(a)
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
        Duration.LENGTH_SHORT -> TSnackbar.LENGTH_SHORT
        Duration.LENGTH_LONG -> TSnackbar.LENGTH_LONG
        Duration.LENGTH_INDEFINITE -> TSnackbar.LENGTH_INDEFINITE
    }

}