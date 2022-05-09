package com.excelsior.codechallenge.infrastructure.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.excelsior.codechallenge.infrastructure.utils.Extensions.showToast
import timber.log.Timber

abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding> : Fragment() {

    // https://developer.android.com/topic/libraries/view-binding#fragments
    private var dataBinding: DB? = null

    protected val binding: DB
        get() = dataBinding!!

    protected abstract val layoutId: Int

    protected abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.setViewModel(viewModel)
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.direction.observe(viewLifecycleOwner) {
            withErrorHandling {
                findNavController().navigate(it)
            }
        }

        viewModel.showError.observe(viewLifecycleOwner) {
            viewModel.loader.postValue(false)
            requireContext().showToast(it)
        }
    }

    // data binding should be removed to avoid memory leaks
    override fun onDestroyView() {
        dataBinding = null
        super.onDestroyView()
    }

    private fun withErrorHandling(target: () -> Unit) {
        try {
            target.invoke()
        } catch (t: Throwable) {
            Timber.e(t)
        }
    }

    abstract fun ViewDataBinding.setViewModel(viewModel: VM)
}