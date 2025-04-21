package com.kindergeschichten.romanisch.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.kindergeschichten.romanisch.R

class CustomImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val imageView: ImageView
    private val textView: TextView

    init {
        orientation = VERTICAL
        LayoutInflater.from(context).inflate(R.layout.my_imageview, this, true)
        imageView = findViewById(R.id.customImageView)
        textView = findViewById(R.id.customTextView)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.CustomImageView, 0, 0)
            val imageRes = typedArray.getResourceId(R.styleable.CustomImageView_imageSrc, 0)
            val text = typedArray.getString(R.styleable.CustomImageView_text)

            if (imageRes != 0) {
                imageView.setImageResource(imageRes)
            }

            textView.text = text
            typedArray.recycle()
        }
    }

    fun setImage(@DrawableRes resId: Int) {
        imageView.setImageResource(resId)
    }

    fun setText(@StringRes resId: Int) {
        textView.setText(resId)
    }

    fun setText(text: String) {
        textView.text = text
    }
}
