package com.hypheno.consultationapp.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.hypheno.consultationapp.model.dataclass.RecommendedTest

@BindingAdapter("android:listTests")
fun loadTest(view: TextView, list: List<RecommendedTest>) {
    var str = ""
    for(test in list)
        str += test.name + "\n"
    view.setText(str.trim())
}