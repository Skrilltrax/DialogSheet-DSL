package com.marcoscg.dialogsheet.dsl.message

import android.content.Context
import android.graphics.Typeface
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.marcoscg.dialogsheet.dsl.DialogSheetDsl

@DialogSheetDsl
class MessageBuilder(private val context: Context) {

    @StringRes
    var textRes: Int = -1
    var text: String = ""
    var textSequence: CharSequence = ""

    @ColorRes
    var textColorRes: Int = -1
    @ColorInt
    var textColor: Int = -1

    var textSize: Int = 16
    var typeface: Typeface? = null

    private var _message: String = ""
        get() {
            _message = when {
                text.isNotEmpty() -> text
                textSequence.isNotEmpty() -> textSequence.toString()
                textRes != -1 -> context.getString(textRes)
                else -> ""
            }
            return field
        }

    @ColorInt
    private var _messageColor = -1
        get() {
            field = when {
                textColor != -1 -> textColor
                textColorRes != -1 -> ContextCompat.getColor(context, textColorRes)
                else -> -1
            }
            return field
        }

    fun build(): Message {
        return Message(_message, _messageColor, typeface, textSize)
    }
}