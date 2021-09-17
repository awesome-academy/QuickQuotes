package com.sun.quickquotes.ui.getstarted

import android.view.View
import com.sun.quickquotes.R
import com.sun.quickquotes.base.BaseFragment
import com.sun.quickquotes.databinding.FragmentGetStartedBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class GetStartedFragment : BaseFragment<FragmentGetStartedBinding>(), View.OnClickListener {

    override val layoutResource get() = R.layout.fragment_get_started
    override val viewModel: GetStartedViewModel by viewModel()

    override fun initViews() {

    }

    override fun initData() {
        with(viewBinding) {
            lifecycleOwner = viewLifecycleOwner
            getStartedVM = viewModel
        }
    }

    override fun initEvents() {

    }

    override fun onClick(v: View?) {

    }
}
