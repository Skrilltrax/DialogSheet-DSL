package com.marcoscg.dialogsheet.dsl.button

import android.content.Context
import android.graphics.Typeface
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

class ButtonBuilder(private val context: Context) {
    @StringRes
    var textRes: Int = -1
    var text: String = ""
    var textSequence: CharSequence = ""

    @ColorRes
    var buttonColorRes: Int = -1
    @ColorInt
    var buttonColor: Int = -1

    var typeface: Typeface? = null

    private var _text: String = ""
        get() {
            _text = when {
                text.isNotEmpty() -> text
                textSequence.isNotEmpty() -> textSequence.toString()
                textRes != -1 -> context.getString(textRes)
                else -> ""
            }
            return field
        }

    @ColorInt
    private var _buttonColor = -1
        get() {
            field = when {
                buttonColor != -1 -> buttonColor
                buttonColorRes != -1 -> ContextCompat.getColor(context, buttonColorRes)
                else -> -1
            }
            return field
        }

    var shouldDismiss: Boolean = true
    private var onClick: (View) -> Unit = {}

    fun onClick(block: (View) -> Unit) {
        onClick = block
    }

    fun build(): Button = Button(_text, _buttonColor, shouldDismiss, onClick, typeface)
}
