package com.marcoscg.dialogsheet.dsl.title

import android.graphics.Typeface
import androidx.annotation.ColorInt

data class Title(
        var text: String,
        @ColorInt
        var color: Int,
        var singleLineTitle: Boolean,
        var typeface: Typeface?,
        var textSize: Int
)