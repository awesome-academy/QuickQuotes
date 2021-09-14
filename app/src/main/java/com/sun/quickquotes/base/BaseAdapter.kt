package com.sun.quickquotes.base

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

abstract class BaseAdapter<D, VH : BaseViewHolder<D>>(
    private val items: MutableList<D>,
    diffUtil: DiffUtil.ItemCallback<D>
) : ListAdapter<D, VH>(diffUtil), UpdateData<D> {

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun updateData(data: List<D>) {
        submitList(data)
    }
}
