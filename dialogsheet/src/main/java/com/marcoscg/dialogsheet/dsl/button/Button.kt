package com.marcoscg.dialogsheet.dsl.button

import android.graphics.Typeface
import android.view.View
import androidx.annotation.ColorInt

data class Button(
        var text: String = "",
        @ColorInt
        var color: Int = -1,
        var shouldDismiss: Boolean = true,
        var onClick: (View) -> Unit = {},
        var typeface: Typeface?
)
