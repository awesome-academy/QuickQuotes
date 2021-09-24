package com.sun.quickquotes.ui.home

import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.sun.quickquotes.R
import com.sun.quickquotes.base.BaseFragment
import com.sun.quickquotes.data.model.Quote
import com.sun.quickquotes.databinding.FragmentHomeBinding
import com.sun.quickquotes.ui.home.adapter.HomeQuoteAdapter
import com.sun.quickquotes.utils.NotificationHelper
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val layoutResource get() = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModel()
    private val homeQuoteAdapter = HomeQuoteAdapter(::onFavoriteClick, ::onShareClick)
    private val arg: HomeFragmentArgs by navArgs()
    private var tagName: String? = null

    override fun initViews() {
        viewBinding.recyclerViewHomeQuote.adapter = homeQuoteAdapter
    }

    override fun initData() {
        with(viewBinding) {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@HomeFragment.viewModel
            val snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(recyclerViewHomeQuote)
        }

        val quote = arguments?.getParcelable<Quote>(NotificationHelper.KEY_OPEN_HOME_FRAGMENT)
        tagName = arg.category
        when {
            tagName != null -> tagName?.let { viewModel.getDataByName(it) }
            quote != null -> viewModel.getDataFromNotify(quote)
            else -> viewModel.getData()
        }

    }

    override fun initEvents() {
        viewBinding.recyclerViewHomeQuote.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                viewModel.quotes.value?.let {
                    val position =
                        (viewBinding.recyclerViewHomeQuote.layoutManager as LinearLayoutManager)
                            .findLastCompletelyVisibleItemPosition()
                    if (position == it.size - 1 && tagName == null) {
                        viewModel.getData()
                    }
                }
            }

        })
        viewModel.quotes.observe(viewLifecycleOwner) {
            homeQuoteAdapter.updateData(it)
        }
        observeLoading(viewBinding.progressBar)
    }

    private fun onFavoriteClick(quote: Quote) = viewModel.changeFavoriteStatus(quote)

    private fun onShareClick(quote: Quote) {}

    override fun onDestroy() {
        viewBinding.recyclerViewHomeQuote.clearOnScrollListeners()
        super.onDestroy()
    }
}
