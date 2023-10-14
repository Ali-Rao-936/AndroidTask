@file:Suppress("unused")

package com.khaleejtimes.test.utils

import android.content.Context
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.DrawableRes
import com.khaleejtimes.test.presentation.test.TestFragmentStateModel
import com.bumptech.glide.Glide
import com.khaleejtimes.test.R
import kotlinx.coroutines.flow.*
import java.lang.Exception


fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun Context.showToast(message:String){
    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}



fun <T> Flow<T>.handleException(a:MutableStateFlow<Any> ): Flow<T> = flow {
    try {
        collect { value -> emit(value) }
    } catch (e: Throwable) {

        a.value = TestFragmentStateModel.FoundException(e as Exception)
    }
}

fun ImageView.loadImageFromUri(uri: Uri){
    Glide
        .with(this.context)
        .load(uri)
        .placeholder(R.drawable.placeholder)
        .into(this)
}

fun ImageView.loadImageFromPath(path: String?, @DrawableRes placeholder:Int = R.drawable.placeholder){
    Glide
        .with(this.context)
        .load(path ?: "")
        .placeholder(placeholder)
        .into(this)
}
