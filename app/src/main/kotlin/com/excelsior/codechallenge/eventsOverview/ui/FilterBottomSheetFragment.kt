package com.excelsior.codechallenge.eventsOverview.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.excelsior.codechallenge.R
import com.excelsior.codechallenge.databinding.FilterBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FilterBottomSheetFragment : BottomSheetDialogFragment() {

    lateinit var binding: FilterBottomSheetBinding

    val viewModel: EventsOverviewViewModel by sharedViewModel<EventsOverviewAndroidViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            FilterBottomSheetBinding.bind(inflater.inflate(R.layout.filter_bottom_sheet, container))
        initializeBindingData()
        return binding.root
    }

    fun initializeBindingData() {
        binding.apply {
            buttonFilterBottomSheetPriceAscending.setOnClickListener {
                viewModel.initiateFilterPriceAscending()
                dismiss()
            }

            buttonFilterBottomSheetPriceDescending.setOnClickListener {
                viewModel.initiateFilterPriceDescending()
                dismiss()
            }

            buttonFilterBottomSheetDateAscending.setOnClickListener {
                viewModel.initiateFilterDateAscending()
                dismiss()
            }

            buttonFilterBottomSheetDateDescending.setOnClickListener {
                viewModel.initiateFilterDateDescending()
                dismiss()
            }

            buttonFilterBottomSheetFilterOutdated.setOnClickListener {
                viewModel.initiateFilterOutdatedEvents()
                dismiss()
            }
        }
    }
}