package com.marcoscg.dialogsheetsample

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatCheckBox
import me.skrilltrax.dialogsheet.DialogSheet
import me.skrilltrax.dialogsheet.DialogSheet.OnPositiveClickListener
import me.skrilltrax.dialogsheet.dialogsheet.dsl.dialogSheet

class MainActivity : AppCompatActivity() {
    lateinit var iconCheckBox: AppCompatCheckBox
    lateinit var customViewCheckBox: AppCompatCheckBox
    lateinit var cornerCheckBox: AppCompatCheckBox
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iconCheckBox = findViewById(R.id.iconCheckBox)
        cornerCheckBox = findViewById(R.id.cornersCheckBox)
        customViewCheckBox = findViewById(R.id.customViewCheckBox)
        button = findViewById(R.id.button)
        button.setOnClickListener { createAndShowDSLDialog() }
    }

    private fun createAndShowDSLDialog() {
        val dialogSheet = dialogSheet(this) {
            coloredNavigationBar = true

            if (iconCheckBox.isChecked) {
                dialogIconRes = R.mipmap.ic_launcher
            }

            if (cornerCheckBox.isChecked) {
                setRoundedCorner = true
            }

            if (customViewCheckBox.isChecked) {
                viewRes = R.layout.custom_dialog_view
            }

            title {
                textRes = R.string.app_name
                textColorRes = R.color.colorAccent
            }
            message {
                textRes = R.string.lorem
            }
            positiveButton {
                text = "POSITIVE"
                onClick {
                    Log.d("positive", "Positive clicked")
                }
            }
            negativeButton {
                text = "NEGATIVE"
            }
            neutralButton {
                text = "NEUTRAL"
            }
        }
    }
    private fun createAndShowDialog() {
        val dialogSheet = DialogSheet(this@MainActivity)
                .setTitle(R.string.app_name)
                .setMessage(R.string.lorem)
                .setSingleLineTitle(true)
                .setColoredNavigationBar(true) //.setButtonsColorRes(R.color.colorAccent) // You can use dialogSheetAccent style attribute instead
                .setPositiveButton(android.R.string.ok, object : OnPositiveClickListener {
                    override fun onClick(view: View?) {
                        Toast.makeText(this@MainActivity, "Positive button clicked!", Toast.LENGTH_SHORT).show()
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .setNeutralButton("Neutral", null)
        if ((findViewById<View>(R.id.customViewCheckBox) as AppCompatCheckBox).isChecked) {
            dialogSheet.setView(R.layout.custom_dialog_view)
            // Access dialog custom inflated view
            val inflatedView = dialogSheet.inflatedView
            val button = inflatedView.findViewById<Button>(R.id.customButton)
            button.setOnClickListener { Toast.makeText(this@MainActivity, "I'm a custom button", Toast.LENGTH_SHORT).show() }
        }
        if (!(findViewById<View>(R.id.cornersCheckBox) as AppCompatCheckBox).isChecked) dialogSheet.setRoundedCorners(false)
        if ((findViewById<View>(R.id.iconCheckBox) as AppCompatCheckBox).isChecked) dialogSheet.setIconResource(R.mipmap.ic_launcher)
        dialogSheet.show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_github) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/skrilltrax/DialogSheet")))
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}