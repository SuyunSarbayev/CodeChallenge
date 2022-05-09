package com.excelsior.codechallenge.eventsOverview.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.excelsior.codechallenge.R
import com.excelsior.codechallenge.databinding.EventsOverviewBinding
import com.excelsior.codechallenge.eventsOverview.ui.adapter.EventsAdapter
import com.excelsior.codechallenge.infrastructure.model.Event
import com.excelsior.codechallenge.infrastructure.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class EventsOverviewFragment : BaseFragment<EventsOverviewViewModel, EventsOverviewBinding>() {

    override val layoutId: Int = R.layout.events_overview

    override val viewModel: EventsOverviewViewModel by sharedViewModel<EventsOverviewAndroidViewModel>()

    var eventsAdapter: EventsAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eventsAdapter = EventsAdapter(viewModel)

        binding.recyclerEventsOverviewEvents.apply {
            setHasFixedSize(true)
            adapter = eventsAdapter
        }

        binding.swiperefreshEventsOverview.setOnRefreshListener {
            initializeAdapterItems(emptyList())
            viewModel.initiateRequestEvents()
        }

        viewModel.loader.observe(viewLifecycleOwner, {
            binding.swiperefreshEventsOverview.isRefreshing = it
        })

        viewModel.eventsLiveData.observe(viewLifecycleOwner, {
            initializeAdapterItems(it)
        })

        binding.imageviewEventsOverviewFilter.setOnClickListener {
            initiateOpenBottomSheetDialog()
        }

        viewModel.clickedEventLiveData.observe(viewLifecycleOwner, {
            initiateNavigateEventDetail(it)
        })

        viewModel.titleUpdateLiveData.observe(viewLifecycleOwner, {
            initializeTitle(it.first, it.second)
        })

        viewModel.initiateRequestEvents()
    }

    fun initializeAdapterItems(items: List<Event>) {
        eventsAdapter?.setItems(items)
    }

    fun initializeTitle(firstEventDate: Event, lastEventDate: Event) {
        binding.textviewEventsOverviewTitle.text = String.format(
            requireContext().getString(R.string.events_overview_title),
            firstEventDate.formattedDate,
            lastEventDate.formattedDate
        )
    }

    fun initiateNavigateEventDetail(event: Event) {
        viewModel.direction.value =
            EventsOverviewFragmentDirections.actionEventsOverviewToEventDetail(event)
    }

    fun initiateOpenBottomSheetDialog() {
        activity?.let {
            FilterBottomSheetFragment().show(it.supportFragmentManager, FILTER_BOTTOM_SHEET_DIALOG)
        }
    }

    override fun ViewDataBinding.setViewModel(viewModel: EventsOverviewViewModel) {
        binding.viewModel = viewModel
    }

    companion object {
        const val FILTER_BOTTOM_SHEET_DIALOG =
            " com.excelsior.codechallenge.eventsOverview.ui.FILTER_BOTTOM_SHEET_DIALOG"
    }
}