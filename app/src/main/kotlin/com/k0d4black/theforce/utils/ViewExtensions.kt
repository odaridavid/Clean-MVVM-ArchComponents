package com.k0d4black.theforce.utils

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import java.math.BigDecimal
import java.math.RoundingMode

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun showSnackbar(view: View, message: String) {
    Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
}

fun RecyclerView.initRecyclerViewWithLineDecoration(context: Context) {
    val linearLayoutManager = LinearLayoutManager(context)
    val itemDecoration = DividerItemDecoration(context, linearLayoutManager.orientation)
    layoutManager = linearLayoutManager
    addItemDecoration(itemDecoration)
}

