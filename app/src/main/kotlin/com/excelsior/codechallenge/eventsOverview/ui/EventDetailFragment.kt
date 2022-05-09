package com.excelsior.codechallenge.eventsOverview.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.excelsior.codechallenge.R
import com.excelsior.codechallenge.databinding.EventDetailBinding
import com.excelsior.codechallenge.infrastructure.model.Event
import com.excelsior.codechallenge.infrastructure.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class EventDetailFragment : BaseFragment<EventsOverviewViewModel, EventDetailBinding>() {

    override val layoutId: Int = R.layout.event_detail

    override val viewModel: EventsOverviewViewModel by sharedViewModel<EventsOverviewAndroidViewModel>()

    var currentEvent: Event? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeArguments()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeLiveData()
        initializeListener()
        initiateRequestEventDetail()
    }

    fun initializeListener() {
        binding.swiperefreshEventDetail.setOnRefreshListener {
            initiateClearData()
            initiateRequestEventDetail()
        }
    }

    fun initiateClearData() {
        binding.event = null
    }

    fun initiateRequestEventDetail() {
        currentEvent?.let { viewModel.initiateRequestEventDetails(it) }
    }

    fun initializeLiveData() {
        viewModel.eventDetailLiveData.observe(viewLifecycleOwner, {
            binding.apply { this.event = it }
        })
        viewModel.loader.observe(viewLifecycleOwner, {
            binding.swiperefreshEventDetail.isRefreshing = it
        })
    }

    fun initializeArguments() {
        arguments?.let {
            EventDetailFragmentArgs.fromBundle(it).let { currentEvent = it.event }
        }
    }

    override fun ViewDataBinding.setViewModel(viewModel: EventsOverviewViewModel) {
        binding.viewModel = viewModel
    }
}