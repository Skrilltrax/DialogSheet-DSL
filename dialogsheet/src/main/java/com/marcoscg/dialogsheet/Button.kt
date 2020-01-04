package com.marcoscg.dialogsheet

import android.view.View

/*fun setPositiveButton(text: CharSequence?, shouldDismiss: Boolean, onPositiveClickListener: DialogSheet.OnPositiveClickListener?): DialogSheet {
    if (text == null) positiveButton.visibility = View.GONE else {
        positiveButton.visibility = View.VISIBLE
        positiveButton.text = text
        positiveButton.setOnClickListener { view ->
            if (shouldDismiss) bottomSheetDialog.dismiss()
            onPositiveClickListener?.onClick(view)
        }
    }
    return this
}

fun setPositiveButton(text: CharSequence?, onPositiveClickListener: DialogSheet.OnPositiveClickListener?): DialogSheet {
    setPositiveButton(text, true, onPositiveClickListener)
    return this
}

fun setPositiveButton(@StringRes textRes: Int, shouldDismiss: Boolean, onPositiveClickListener: DialogSheet.OnPositiveClickListener?): DialogSheet {
    setPositiveButton(context.resources.getString(textRes), shouldDismiss, onPositiveClickListener)
    return this
}

fun setPositiveButton(@StringRes textRes: Int, onPositiveClickListener: DialogSheet.OnPositiveClickListener?): DialogSheet {
    setPositiveButton(context.resources.getString(textRes), true, onPositiveClickListener)
    return this
}*/

data class Button(
        var text: String = "",
        var shouldDismiss: Boolean = true,
        var onClick: (View) -> Unit = {}
)
