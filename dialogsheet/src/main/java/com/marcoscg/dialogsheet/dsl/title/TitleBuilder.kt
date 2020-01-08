package com.marcoscg.dialogsheet.dsl.title

import android.content.Context
import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

class TitleBuilder(private val context: Context) {

    @StringRes
    var titleRes: Int = -1
    var title: String = ""
    var titleSequence: CharSequence = ""

    @ColorRes
    var titleColorRes: Int = -1
    @ColorInt
    var titleColor: Int = -1

    private var _title: String = ""
        get() {
            _title = when {
                title.isNotEmpty() -> title
                titleSequence.isNotEmpty() -> titleSequence.toString()
                titleRes != -1 -> context.getString(titleRes)
                else -> ""
            }
            return field
        }

    @ColorInt
    private var _titleColor: Int = -1
        get() {
            field = when {
                titleColor != -1 -> titleColor
                titleColorRes != -1 -> ContextCompat.getColor(context, titleColorRes)
                else -> -1
            }
            return field
        }

    fun build(): Title {
        return Title(_title, _titleColor)
    }
}