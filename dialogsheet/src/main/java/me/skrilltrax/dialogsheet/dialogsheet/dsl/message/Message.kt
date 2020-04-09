package me.skrilltrax.dialogsheet.dialogsheet.dsl.message

import android.graphics.Typeface
import androidx.annotation.ColorInt

data class Message(
        var text: String,
        @ColorInt
        var color: Int,
        var typeface: Typeface?,
        var textSize: Int
)