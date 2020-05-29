package com.k0d4black.theforce.commons

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.k0d4black.theforce.R

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun Activity.showSnackbar(view: View, message: String, isError: Boolean = false) {
    val sb = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
    if (isError)
        sb.setBackgroundTint(loadColor(R.color.colorError))
            .setTextColor(loadColor(R.color.colorOnError))
            .show()
    else
        sb.setBackgroundTint(loadColor(R.color.colorSecondary))
            .setTextColor(loadColor(R.color.colorOnSecondary))
            .show()

}

fun RecyclerView.initRecyclerViewWithLineDecoration(context: Context) {
    val linearLayoutManager = LinearLayoutManager(context)
    val itemDecoration = DividerItemDecoration(context, linearLayoutManager.orientation).apply{
        setDrawable(context.getDrawable(R.drawable.view_divider)!!)
    }
    layoutManager = linearLayoutManager
    addItemDecoration(itemDecoration)
}

fun Context.provideHorizontalLayoutManager(): LinearLayoutManager {
    return LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
}


fun Context.loadColor(@ColorRes colorRes: Int): Int {
    return ContextCompat.getColor(this, colorRes)
}
