package com.mustafatunc.simplesnackbarbuilder.tsnackbar

import android.view.View
import com.mustafatunc.simplesnackbarbuilder.SSnackbar

interface ISnackbarTopBottom {
    fun make(view: View, text: CharSequence, duration: SSnackbar.Duration)
    fun backgroundColor(color: Int)
    fun setTextColor(color: Int)
    fun setAction(text: CharSequence, color: Int, callback: View.OnClickListener)
    fun setDuration(duration: SSnackbar.Duration)
    fun textAlignment(alignment: SSnackbar.Alignment)
}