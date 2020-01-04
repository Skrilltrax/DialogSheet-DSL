package com.marcoscg.dialogsheet

import android.content.Context
import android.view.View

/*
fun positiveButton(context: Context, block: PositiveButtonBuilder.() -> Unit) : PositiveButton {
    val positiveButtonBuilder = PositiveButtonBuilder(context)
    positiveButtonBuilder.apply(block)
    return positiveButtonBuilder.build(context)
}

fun onClick(view: View, block: PositiveButtonBuilder.onClick.() -> Unit): () -> Unit {
    val positiveButtonBuilder
}
*/


class ButtonBuilder(context: Context) {
    var text: String = ""
    var shouldDismiss: Boolean = true
    private var onClick: (View) -> Unit = {}

    fun onClick(block: (View) -> Unit) {
        onClick = block
    }

    fun build(context: Context): Button = Button(text, shouldDismiss, onClick)
}
