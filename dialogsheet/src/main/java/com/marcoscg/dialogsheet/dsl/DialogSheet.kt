package com.marcoscg.dialogsheet.dsl

import android.content.Context
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.material.button.MaterialButton
import com.marcoscg.dialogsheet.DialogSheet
import com.marcoscg.dialogsheet.ExpandedBottomSheetDialog

data class DialogSheet(
        private var context: Context,
        private var bottomSheetDialog: ExpandedBottomSheetDialog,
        private var backgroundColor: Int = 0,
        private var titleTextColor: Int = 0,
        private var messageTextColor: Int = 0,
        private var coloredNavigationBar: Boolean = false,
        private var titleTextView: AppCompatTextView,
        private var messageTextView: AppCompatTextView,
        private var iconImageView: AppCompatImageView,
        private var positiveButton: MaterialButton,
        private var negativeButton: MaterialButton,
        private var neutralButton: MaterialButton,
        private var textContainer: RelativeLayout,
        private var messageContainer: LinearLayout
//        var inflatedView: View
)

fun dialogSheet(context: Context, block: DialogSheetBuilder.() -> Unit): DialogSheet {
    return DialogSheetBuilder(context).apply(block).build() as DialogSheet
}


