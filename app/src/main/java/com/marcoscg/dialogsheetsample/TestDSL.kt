package com.marcoscg.dialogsheetsample

import android.content.Context
import android.view.View
import com.marcoscg.dialogsheet.dsl.dialogSheet

class TestDSL(private val context: Context) {
    fun noName() {
/*        positiveButton(context) {
            text = "HELLOOO BRO"
            shouldDismiss = false
        }*/
        dialogSheet(context) {
            positiveButton {
                text = "Aditya"
                shouldDismiss = true
                onClick {
                }
            }
        }
    }
}