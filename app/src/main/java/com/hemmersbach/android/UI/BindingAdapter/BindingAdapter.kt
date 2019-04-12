package com.hemmersbach.android.UI.BindingAdapter

import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("showCategory")
    fun loadDate(view: TextView, category: List<String>) {
        if (!category.isEmpty()) {
            var stringOfCategory = ""
            for (i in category.indices) {
                stringOfCategory += " ${category[i]}"
            }
            view.text = stringOfCategory
        }
    }

    @JvmStatic
    @BindingAdapter("setIcon")
    fun setIcon(view: ImageView, resImg: Drawable) = view.setImageDrawable(resImg)
}