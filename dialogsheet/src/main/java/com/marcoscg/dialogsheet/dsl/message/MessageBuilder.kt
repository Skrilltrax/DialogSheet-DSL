package com.marcoscg.dialogsheet.dsl.message

import android.content.Context
import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

class MessageBuilder(private val context: Context) {

    @StringRes
    var messageRes: Int = -1
    var message: String = ""
    var messageSequence: CharSequence = ""

    @ColorRes
    var messageColorRes: Int = -1
    @ColorInt
    var messageColor: Int = -1

    private var _message: String = ""
        get() {
            _message = when {
                message.isNotEmpty() -> message
                messageSequence.isNotEmpty() -> messageSequence.toString()
                messageRes != -1 -> context.getString(messageRes)
                else -> ""
            }
            return field
        }

    @ColorInt
    private var _messageColor = -1
        get() {
            field = when {
                messageColor != -1 -> messageColor
                messageColorRes != -1 -> ContextCompat.getColor(context, messageColorRes)
                else -> -1
            }
            return field
        }

    fun build(): Message {
        return Message(_message, _messageColor)
    }
}