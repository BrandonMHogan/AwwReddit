package com.brandonhogan.kotlintest.features.aww.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.brandonhogan.kotlintest.R
import com.brandonhogan.kotlintest.commons.adapter.ViewType
import com.brandonhogan.kotlintest.commons.adapter.ViewTypeDelegateAdapter
import com.brandonhogan.kotlintest.commons.extensions.inflate

/**
 * Created by Brandon on 2/1/2017
 * Description :
 */

class LoadingDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup) = LoadingViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
    }

    class LoadingViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.aww_item_loading))
}