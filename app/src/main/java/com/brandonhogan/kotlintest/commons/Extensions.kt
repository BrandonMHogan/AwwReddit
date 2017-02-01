package com.brandonhogan.kotlintest.commons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Brandon on 2/1/2017.
 * Description :
 */

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}