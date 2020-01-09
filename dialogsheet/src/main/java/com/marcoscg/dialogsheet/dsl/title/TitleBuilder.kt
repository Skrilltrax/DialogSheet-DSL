package com.marcoscg.dialogsheet.dsl.title

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

class TitleBuilder(private val context: Context) {

    @StringRes
    var textRes: Int = -1
    var text: String = ""
    var textSequence: CharSequence = ""

    @ColorRes
    var textColorRes: Int = -1
    @ColorInt
    var textColor: Int = -1

    var textSize: Int = 20
    var typeface: Typeface? = null
    var useSingleLineTitle = false

    private var _title: String = ""
        get() {
            _title = when {
                text.isNotEmpty() -> text
                textSequence.isNotEmpty() -> textSequence.toString()
                textRes != -1 -> context.getString(textRes)
                else -> ""
            }
            return field
        }

    @ColorInt
    private var _titleColor: Int = -1
        get() {
            field = when {
                textColor != -1 -> textColor
                textColorRes != -1 -> ContextCompat.getColor(context, textColorRes)
                else -> -1
            }
            return field
        }

    fun build(): Title {
        return Title(_title, _titleColor, useSingleLineTitle, typeface, textSize)
    }
}