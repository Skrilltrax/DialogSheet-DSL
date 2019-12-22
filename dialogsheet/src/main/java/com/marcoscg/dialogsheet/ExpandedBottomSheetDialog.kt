package com.marcoscg.dialogsheet

import android.content.Context
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import android.widget.FrameLayout

internal class ExpandedBottomSheetDialog @JvmOverloads constructor(context: Context, theme: Int = 0)
    : BottomSheetDialog(context, theme) {
    private var behavior: BottomSheetBehavior<FrameLayout>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            val privateField = BottomSheetDialog::class.java.getDeclaredField("behavior")
            privateField.isAccessible = true
            behavior = privateField[this] as BottomSheetBehavior<FrameLayout>
        } catch (e: NoSuchFieldException) { // do nothing
        } catch (e: IllegalAccessException) { // do nothing
        }
    }

    override fun onStart() {
        super.onStart()
        if (behavior != null) {
            behavior?.skipCollapsed = true
            behavior?.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }
}