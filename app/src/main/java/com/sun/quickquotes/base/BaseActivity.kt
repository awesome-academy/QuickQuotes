package com.sun.quickquotes.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.sun.quickquotes.R

abstract class BaseActivity<V : ViewDataBinding> : AppCompatActivity() {

    private var _viewBinding: V? = null
    protected val viewBinding: V
        get() = _viewBinding ?: throw Exception(getString(R.string.error_binding))

    @get:LayoutRes
    protected abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewBinding = DataBindingUtil.setContentView(this, layoutId)
        viewBinding.lifecycleOwner = this
        initViews()
    }

    protected abstract fun initViews()

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }
}
