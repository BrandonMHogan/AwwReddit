package com.brandonhogan.kotlintest.commons.adapter

/**
 * Created by Brandon on 2/1/2017.
 * Description :
 */

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

interface ViewTypeDelegateAdapter {

    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType)
}