package com.mustafatunc.simplesnackbarbuilder

import android.content.Context
import android.graphics.Color
import android.support.annotation.ColorRes
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import android.view.View
import com.mustafatunc.simplesnackbarbuilder.tsnackbar.BottomSnackbar
import com.mustafatunc.simplesnackbarbuilder.tsnackbar.ISnackbarTopBottom
import com.mustafatunc.simplesnackbarbuilder.tsnackbar.TopSnackbar

abstract class SSnackbar protected constructor() {

    enum class Duration { LENGTH_SHORT, LENGTH_LONG, LENGTH_INDEFINITE }
    enum class Alignment { LEFT, CENTER }
    enum class SnackType { TOP, BOTTOM }

    abstract fun show()
    abstract fun dismiss()
    abstract fun isShowing(): Boolean


    class Builder(
        private val context: Context,
        private val view: View,
        type: SnackType
    ) {


        private var snack: ISnackbarTopBottom = if (type == SnackType.BOTTOM) {
            BottomSnackbar()
        } else {
            TopSnackbar()
        }


        private var text: CharSequence = ""
        private var textColor: Int = Color.BLACK

        private var alignment = Alignment.LEFT

        private var backgroundColor = Color.WHITE

        private var actionText: CharSequence = ""
        private var actionColor = Color.RED

        private var duration = Duration.LENGTH_SHORT

        private var callback = View.OnClickListener {
            (snack as SSnackbar).dismiss()
        }

        private var isActionSet = false


        /**
         * Sets the background color using color resource id
         * */
        fun backgroundColorResource(@ColorRes colorId: Int) = apply {
            backgroundColor = getColor(colorId)
        }

        /**
         * Sets the background color
         * Default color is white
         * */
        fun backgroundColor(color: Int) = apply {
            backgroundColor = color
        }

        /**
         * Sets the main text of the snackbar using string resource.
         * Default color is black
         * */
        fun setText(@StringRes stringId: Int) = apply {
            text = getString(stringId)
        }

        /**
         * Sets the main text of the snackbar
         * Default color is black
         * */
        fun setText(text: CharSequence) = apply {
            this.text = text
        }

        /**
         * Sets the main text and its color using resource IDs
         * */
        fun setTextWithColorResource(@StringRes stringId: Int, @ColorRes colorId: Int) = apply {
            text = getString(stringId)
            textColor = getColor(colorId)
        }

        /**
         * Sets the main text and its color
         * */
        fun setTextWithColor(@StringRes stringId: Int, color: Int) = apply {
            text = getString(stringId)
            textColor = color
        }

        /**
         *  Sets the text and color
         * */
        fun setText(text: CharSequence, color: Int) = apply {
            this.text = text
            textColor = color
        }

        /**
         * Sets the text alignment in the snackbar.
         * If an action button is used, it is not recommended to use CENTER
         * */
        fun textAlignment(alignment: Alignment) = apply {
            this.alignment = alignment
        }

        /**
         * Sets the action button with a callback
         * @param textId string ID of the button text
         * @param callback if null, the button will dismiss the snackbar
         * */
        fun setAction(@StringRes textId: Int, callback: View.OnClickListener? = null) = apply {
            actionText = getString(textId)
            if (callback != null)
                this.callback = callback
            isActionSet = true
        }

        /**
         * Sets the action button with a callback
         * @param text action button text
         * @param callback if null, the button will dismiss the snackbar
         * */
        fun setAction(text: CharSequence, callback: View.OnClickListener? = null) = apply {
            actionText = text
            if (callback != null)
                this.callback = callback
            isActionSet = true
        }

        /**
         * Sets the action button with a callback
         * @param textId string resource of the button text
         * @param colorId color resource of the color of the button text
         * @param callback if null, the button will dismiss the snackbar
         * */
        fun setActionWithColorResource(@StringRes textId: Int, @ColorRes colorId: Int, callback: View.OnClickListener? = null) =
            apply {
                actionText = getString(textId)
                actionColor = getColor(colorId)
                if (callback != null)
                    this.callback = callback
                isActionSet = true
            }

        /**
         * Sets the action button with a callback
         * @param text button text
         * @param colorId color resource of the color of the button text
         * @param callback if null, the button will dismiss the snackbar
         * */
        fun setActionWithColorResource(
            text: CharSequence, @ColorRes colorId: Int,
            callback: View.OnClickListener? = null
        ) =
            apply {
                actionText = text
                actionColor = getColor(colorId)
                if (callback != null)
                    this.callback = callback
                isActionSet = true
            }

        /**
         * Sets the action button with a callback
         * @param text button text
         * @param color color of the button text
         * @param callback if null, the button will dismiss the snackbar
         * */
        fun setActionWithColor(text: CharSequence, color: Int, callback: View.OnClickListener? = null) = apply {
            actionText = text
            actionColor = color
            if (callback != null)
                this.callback = callback
            isActionSet = true
        }

        /**
         * Sets the action button with a callback
         * @param textId string resource of the button text
         * @param color color of the button text
         * @param callback if null, the button will dismiss the snackbar
         * */
        fun setActionWithColor(@StringRes textId: Int, color: Int, callback: View.OnClickListener? = null) = apply {
            actionText = getString(textId)
            actionColor = color
            if (callback != null)
                this.callback = callback
            isActionSet = true
        }

        /**
         * Sets the display time of the snackbar.
         * If the snackbar has an action button, this will be omitted
         * and the duration will be set to LENGTH_INDEFINITE
         * */
        fun setDuration(duration: Duration): Builder = apply {
            this.duration = duration
        }

        /**
         * Builds the snackbar whose values are set.
         * */
        fun build(): SSnackbar {
            with(snack) {
                make(view, text, duration)
                setTextColor(textColor)
                if (isActionSet) {
                    setAction(actionText, actionColor, callback)
                    setDuration(Duration.LENGTH_INDEFINITE)
                }
                backgroundColor(backgroundColor)
                textAlignment(alignment)
            }

            return snack as SSnackbar
        }


        private fun getString(@StringRes stringId: Int): CharSequence = context.getString(stringId)
        private fun getColor(@ColorRes colorId: Int): Int = ContextCompat.getColor(context, colorId)

    }

}