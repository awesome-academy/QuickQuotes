package com.sun.quickquotes.ui.category

import com.sun.quickquotes.R
import com.sun.quickquotes.base.BaseFragment
import com.sun.quickquotes.databinding.FragmentCategoryBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryFragment : BaseFragment<FragmentCategoryBinding>() {

    override val layoutResource get() = R.layout.fragment_category
    override val viewModel: CategoryViewModel by viewModel()

    override fun initViews() {

    }

    override fun initData() {
        with(viewBinding) {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@CategoryFragment.viewModel
        }
    }

    override fun initEvents() {
        observeLoading(viewBinding.progressBar)
    }
}
