package com.excelsior.codechallenge.eventsOverview.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.excelsior.codechallenge.databinding.EventListItemBinding
import com.excelsior.codechallenge.eventsOverview.ui.EventsOverviewViewModel
import com.excelsior.codechallenge.infrastructure.model.Event

class EventsAdapter(
    private val viewModel: EventsOverviewViewModel
) : RecyclerView.Adapter<EventViewHolder>() {

    private val items = mutableListOf<Event>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder =
        EventViewHolder(
            viewModel,
            EventListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            viewModel.clickedEventLiveData.postValue(items.get(position))
        }
    }

    override fun getItemCount(): Int =
        items.size

    fun setItems(newData: List<Event>) {
        items.clear()
        items.addAll(newData)
        notifyDataSetChanged()
    }
}