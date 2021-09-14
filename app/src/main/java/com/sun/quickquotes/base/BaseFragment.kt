package com.sun.quickquotes.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.sun.quickquotes.R
import com.sun.quickquotes.utils.setVisibility
import com.sun.quickquotes.utils.showToast

abstract class BaseFragment<V : ViewDataBinding> : Fragment() {

    @get:LayoutRes
    protected abstract val layoutResource: Int
    abstract val viewModel: BaseViewModel
    private var _viewBinding: V? = null
    protected val viewBinding: V
        get() = _viewBinding ?: throw Exception(getString(R.string.error_binding))

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? = DataBindingUtil
        .inflate<V>(inflater, layoutResource, container, false)
        .apply { _viewBinding = this }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        initViews()
        initData()
        initEvents()
    }

    protected abstract fun initViews()

    protected abstract fun initData()

    protected abstract fun initEvents()

    protected open fun observeViewModel() = with(viewModel) {
        message.observe(viewLifecycleOwner, {
            hideLoading()
            it.getContentIfNotHandled()?.let { value ->
                if (getString(value).isNotBlank()) {
                    context?.showToast(getString(value))
                }
            }
        })
    }

    protected fun observeLoading(view: View) {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { value ->
                view.setVisibility(value)
            }
        }
    }

    abstract fun showLoading()

    abstract fun hideLoading()

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }
}
