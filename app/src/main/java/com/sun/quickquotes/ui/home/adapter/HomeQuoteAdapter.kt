package com.sun.quickquotes.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sun.quickquotes.R
import com.sun.quickquotes.base.BaseAdapter
import com.sun.quickquotes.base.BaseViewHolder
import com.sun.quickquotes.data.model.Quote
import com.sun.quickquotes.databinding.ItemHomeQuoteBinding
import com.sun.quickquotes.utils.loadImageFavorite

class HomeQuoteAdapter(
    private val onFavoriteClick: (Quote) -> Unit,
    private val onShareClick: (Quote) -> Unit,
) : BaseAdapter<Quote, HomeQuoteAdapter.HomeQuoteViewHolder>(Quote.diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HomeQuoteViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_home_quote,
                parent,
                false
            ), onFavoriteClick, onShareClick
        )

    class HomeQuoteViewHolder(
        private val viewBinding: ItemHomeQuoteBinding,
        private val onFavoriteClick: (Quote) -> Unit,
        private val onShareClick: (Quote) -> Unit,
    ) : BaseViewHolder<Quote>(viewBinding, {}) {

        override fun bindData(item: Quote) {
            super.bindData(item)
            with(viewBinding) {
                quote = item
                imageHomeShare.setOnClickListener { onShareClick(item) }
                imageHomeFavorite.setOnClickListener {
                    onFavoriteClick(item)
                    imageHomeFavorite.loadImageFavorite(item.isFavorite.not())
                }
            }
        }
    }
}
