package com.marcoscg.dialogsheet.dsl.button

import android.content.Context
import android.graphics.Color
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
    var textColorRes: Int = -1
    @ColorInt
    var textColor: Int = -1

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
    private var _textColor = -1
        get() {
            field = when {
                textColor != -1 -> textColor
                textColorRes != -1 -> ContextCompat.getColor(context, textColorRes)
                else -> -1
            }
            return field
        }

    var shouldDismiss: Boolean = true
    private var onClick: (View) -> Unit = {}

    fun onClick(block: (View) -> Unit) {
        onClick = block
    }

    fun build(): Button = Button(_text, _textColor, shouldDismiss, onClick)
}
