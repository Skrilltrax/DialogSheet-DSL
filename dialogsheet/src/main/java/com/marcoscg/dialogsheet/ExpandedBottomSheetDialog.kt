package com.marcoscg.dialogsheet

import android.content.Context
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class ExpandedBottomSheetDialog @JvmOverloads constructor(context: Context, theme: Int = 0)
    : BottomSheetDialog(context, theme) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        behavior.skipCollapsed = true
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun onStop() {
        super.onStop()
        behavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }
}