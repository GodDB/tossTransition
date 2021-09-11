package com.godgod.tosstranstion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T : ViewDataBinding>(@LayoutRes private val layoutId: Int) :
    Fragment() {

    private var _binding: T? = null
    protected val binding: T
        get() = checkNotNull(_binding) {
            "not access before onViewCreated or after onDestroyView"
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    override fun onDestroy() {
        _binding?.unbind()
        _binding = null
        super.onDestroy()
    }

    abstract fun setupView()

    protected open fun setup() {
        // 자식 클래스에서 구체화 한다.
    }
}