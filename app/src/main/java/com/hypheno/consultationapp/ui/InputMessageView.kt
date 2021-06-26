package com.hypheno.consultationapp.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.hypheno.consultationapp.databinding.InputMessageViewBinding

class InputMessageView : ConstraintLayout {

    private lateinit var binding: InputMessageViewBinding

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context)
    }

    fun init(context: Context) {
        val inflater = LayoutInflater.from(context)
        binding = InputMessageViewBinding.inflate(inflater, this, true)
    }
}