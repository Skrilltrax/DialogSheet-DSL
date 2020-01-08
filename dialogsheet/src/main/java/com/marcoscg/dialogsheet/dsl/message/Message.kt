package com.marcoscg.dialogsheet.dsl.message

import androidx.annotation.ColorInt

data class Message(
        var text: String,
        @ColorInt
        var color: Int
)