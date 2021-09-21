package com.sun.quickquotes.ui.home

import com.sun.quickquotes.R
import com.sun.quickquotes.base.BaseFragment
import com.sun.quickquotes.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val layoutResource get() = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModel()

    override fun initViews() {

    }

    override fun initData() {
        with(viewBinding) {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@HomeFragment.viewModel
        }
    }

    override fun initEvents() {

    }
}
