package com.trends.patientapplication.core

import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageSetter")
fun ImageView.imageSetter(url: String?) {
    try {
        Glide.with(this).load(url).centerCrop().into(this)
    }catch (e:Exception){
        Log.i("TAG:2", e.localizedMessage!!.toString())
        val url2 = "https://previews.123rf.com/images/urfandadashov/urfandadashov1806/urfandadashov180601827/150417827-photo-not-available-vector-icon-isolated-on-transparent-background-photo-not-available-logo-concept.jpg"
        Glide.with(this).load(url2).centerCrop().into(this)
    }
}
