package com.example.composeapplication.banya.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (imageUrl.isNullOrEmpty()) {
        return
    }
    Glide.with(view)
        .load(imageUrl)
        .placeholder(android.R.drawable.progress_indeterminate_horizontal)
        .into(view)
}