package me.skrilltrax.dialogsheet.dialogsheet.dsl

import android.content.Context
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.material.button.MaterialButton
import me.skrilltrax.dialogsheet.ExpandedBottomSheetDialog

data class DialogSheet(
        private var context: Context,
        private var bottomSheetDialog: ExpandedBottomSheetDialog,
        private var coloredNavigationBar: Boolean = true,
        private var setRoundedCorner: Boolean = true,
        private var iconImageView: AppCompatImageView,
        private var titleTextView: AppCompatTextView,
        private var messageTextView: AppCompatTextView,
        private var positiveButton: MaterialButton,
        private var negativeButton: MaterialButton,
        private var neutralButton: MaterialButton
//        var inflatedView: View
)

fun dialogSheet(context: Context, block: DialogSheetBuilder.() -> Unit): DialogSheet
        = DialogSheetBuilder(context).apply(block).build()



