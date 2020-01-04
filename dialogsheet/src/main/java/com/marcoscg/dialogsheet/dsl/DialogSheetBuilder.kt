package com.marcoscg.dialogsheet.dsl

import android.content.Context
import android.content.res.Configuration
import android.graphics.*
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.material.button.MaterialButton
import com.marcoscg.dialogsheet.*
import com.marcoscg.dialogsheet.Utils
import com.marcoscg.dialogsheet.Utils.dpToPx

class DialogSheetBuilder constructor(private val context: Context) {

    /*
     * User accessible properties
     */

    @StringRes
    var messageRes: Int = -1
    var message: String = ""
    var messageSequence: CharSequence = ""

    @StringRes
    var titleRes: Int = -1
    var title: String = ""
    var titleSequence: CharSequence = ""

    var coloredNavigationBar = false

    @ColorRes
    var titleColorRes: Int = -1
    @ColorInt
    var titleColor: Int = -1

    @ColorRes
    var backgroundColorRes: Int = -1
    @ColorInt
    var backgroundColor: Int = -1

    @ColorRes
    var messageColorRes: Int = -1
    @ColorInt
    var messageColor: Int = -1

    private var bottomSheetDialog: ExpandedBottomSheetDialog
    private var messageTextColor = 0
    private lateinit var titleTextView: AppCompatTextView
    private lateinit var messageTextView: AppCompatTextView
    private lateinit var iconImageView: AppCompatImageView
    private lateinit var positiveButton: MaterialButton
    private lateinit var negativeButton: MaterialButton
    private lateinit var neutralButton: MaterialButton
    private lateinit var textContainer: RelativeLayout
    private lateinit var messageContainer: LinearLayout
    lateinit var inflatedView: View
        private set

    private var _title: String? = null
        get() {
            _title = when {
                title.isNotEmpty() -> {
                    title
                }
                titleSequence.isNotEmpty() -> {
                    titleSequence.toString()
                }
                titleRes != -1 -> {
                    context.getString(titleRes)
                }
                else -> {
                    ""
                }
            }
            return field
        }

    private var _message: String? = null
        get() {
            _message = when {
                message.isNotEmpty() -> {
                    title
                }
                messageSequence.isNotEmpty() -> {
                    messageSequence.toString()
                }
                messageRes != -1 -> {
                    context.getString(messageRes)
                }
                else -> {
                    ""
                }
            }
            return field
        }

    private var _titleColor: Int = -1
        get() {
            field = when {
                titleColor != -1 -> titleColor
                titleColorRes != -1 -> titleColorRes
                else -> -1
            }
            return field
        }

    private var _backgroundColor = 0
        get() {
            field = when {
                backgroundColor != -1 -> backgroundColor
                backgroundColorRes != -1 -> backgroundColorRes
                else -> -1
            }
            return field
        }

    private var _messageColor = 0
        get() {
            field = when {
                messageColor != -1 -> messageColor
                messageColorRes != -1 -> messageColorRes
                else -> -1
            }
            return field
        }

    init {
        val accentColor = Utils.getAttrColor(context, R.attr.dialogSheetAccent)
        var posButtonTextColor = Color.WHITE
        if (accentColor != -1) {
            bottomSheetDialog = ExpandedBottomSheetDialog(context, R.style.DialogSheetTheme_Colored)
            posButtonTextColor = Utils.getTextColor(accentColor)
        } else {
            bottomSheetDialog = ExpandedBottomSheetDialog(context, R.style.DialogSheetTheme)
        }
        bottomSheetDialog.setContentView(R.layout.layout_bottomdialog)
        if (bottomSheetDialog.window != null) bottomSheetDialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        findViews()
        positiveButton.setTextColor(posButtonTextColor)
    }

    private fun findViews() {
        bottomSheetDialog.apply {
            titleTextView = findViewById(R.id.dialogTitle)!!
            messageTextView = findViewById(R.id.dialogMessage)!!
            iconImageView = findViewById(R.id.dialogIcon)!!
            positiveButton = findViewById(R.id.buttonPositive)!!
            negativeButton = findViewById(R.id.buttonNegative)!!
            neutralButton = findViewById(R.id.buttonNeutral)!!
            textContainer = findViewById(R.id.textContainer)!!
            messageContainer = findViewById(R.id.messageContainer)!!
        }
    }

    fun positiveButton(block: ButtonBuilder.() -> Unit)  {
        val button: Button
        val positiveButtonBuilder = ButtonBuilder(context)
        button = positiveButtonBuilder.apply(block).build(context)
        positiveButton.visibility = View.VISIBLE
        positiveButton.text = button.text
        positiveButton.setOnClickListener { button.onClick(positiveButton) }
    }

    fun negativeButton(block: ButtonBuilder.() -> Unit)  {
        val button: Button
        val negativeButtonBuilder = ButtonBuilder(context)
        button = negativeButtonBuilder.apply(block).build(context)
        negativeButton.visibility = View.VISIBLE
        negativeButton.text = button.text
        negativeButton.setOnClickListener { button.onClick(negativeButton) }
    }

    fun neutralButton(block: ButtonBuilder.() -> Unit)  {
        val button: Button
        val neutralButtonBuilder = ButtonBuilder(context)
        button = neutralButtonBuilder.apply(block).build(context)
        neutralButton.visibility = View.VISIBLE
        neutralButton.text = button.text
        neutralButton.setOnClickListener { button.onClick(neutralButton) }
    }


    fun build(): DialogSheet {
        titleTextView.text = _title
        titleTextView.visibility = View.VISIBLE
        messageTextView.text = _message
        messageTextView.visibility = View.VISIBLE
        show()
        return DialogSheet(context, bottomSheetDialog, _backgroundColor, _titleColor, _messageColor, coloredNavigationBar, titleTextView, messageTextView, iconImageView, positiveButton, negativeButton, neutralButton, textContainer, messageContainer/*, inflatedView*/)
    }

    fun show() {
        setColors()
        setVisibility()
        bottomSheetDialog.show()
        // Landscape fixed width
        val configuration = context.resources.configuration
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE &&
                configuration.screenWidthDp > 400) {
            if (bottomSheetDialog.window != null) bottomSheetDialog.window!!.setLayout(400.dpToPx(), -1)
        }
    }

    private fun setVisibility() {
        if (positiveButton.visibility != View.VISIBLE) {
            (negativeButton.layoutParams as RelativeLayout.LayoutParams).addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
        }

        if (!areButtonsVisible()) {
            var bottomPadding = 0
            var topPadding = 0
            if (!messageTextView.text.isNullOrEmpty()) {
                bottomPadding = 24.dpToPx()
                if (titleTextView.text.isNullOrEmpty()) {
                    topPadding = 24.dpToPx()
                }
            }
            textContainer.setPadding(0, topPadding, 0, bottomPadding)
        } else {
            if ((titleTextView.text == null || TextUtils.isEmpty(titleTextView.text))
                    && messageTextView.text != null && !TextUtils.isEmpty(messageTextView.text)) textContainer.setPadding(0, 24.dpToPx(), 0, 0)
        }
    }

    private fun setColoredNavBar(coloredNavigationBar: Boolean) {
        if (coloredNavigationBar && bottomSheetDialog.window != null && Build.VERSION.SDK_INT >= 21) {
            if (Utils.isColorLight(backgroundColor)) {
                if (Build.VERSION.SDK_INT >= 26) {
                    bottomSheetDialog.window?.navigationBarColor = backgroundColor
                    var flags = bottomSheetDialog.window?.decorView?.systemUiVisibility
                    if (flags != null) {
                        flags = flags or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                    }
                    bottomSheetDialog.window?.decorView?.systemUiVisibility = flags!!
                }
            } else {
                bottomSheetDialog.window?.navigationBarColor = backgroundColor
                if (Build.VERSION.SDK_INT >= 26) {
                    var flags = bottomSheetDialog.window?.decorView?.systemUiVisibility
                    if (flags != null) {
                        flags = flags and View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR.inv()
                    }
                    bottomSheetDialog.window?.decorView?.systemUiVisibility = flags!!
                }
            }
        }
    }

    fun setColors() {
        if (_backgroundColor == -1) {
            backgroundColor = Utils.getAttrColor(context, android.R.attr.windowBackground)
        } else {
            bottomSheetDialog.apply {
                val bgView = findViewById<View>(R.id.mainDialogContainer)
                bgView?.background?.colorFilter =
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            BlendModeColorFilter(_backgroundColor, BlendMode.SRC_ATOP)
                        } else {
                            PorterDuffColorFilter(_backgroundColor, PorterDuff.Mode.SRC_IN)
                        }
            }
        }

        if (_titleColor == -1) _titleColor = Utils.getTextColor(backgroundColor)
        if (_messageColor == -1) messageTextColor = Utils.getTextColorSec(backgroundColor)

        titleTextView.setTextColor(_titleColor)
        messageTextView.setTextColor(_messageColor)
        setColoredNavBar(coloredNavigationBar)
    }

    private fun areButtonsVisible(): Boolean {
        return positiveButton.visibility == View.VISIBLE || negativeButton.visibility == View.VISIBLE || neutralButton.visibility == View.VISIBLE
    }
}