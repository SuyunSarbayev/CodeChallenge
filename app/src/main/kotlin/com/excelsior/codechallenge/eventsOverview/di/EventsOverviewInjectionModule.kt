package com.excelsior.codechallenge.eventsOverview.di

import com.excelsior.codechallenge.eventsOverview.repository.EventsRepository
import com.excelsior.codechallenge.eventsOverview.ui.EventsOverviewAndroidViewModel
import com.excelsior.codechallenge.eventsOverview.utils.EventsOverviewSorting
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object EventsOverviewInjectionModule {

    val module = module {

        viewModel {
            EventsOverviewAndroidViewModel(get(), get(), get())
        }

        factory {
            EventsOverviewSorting()
        }

        factory {
            EventsRepository(get(), get(), get())
        }
    }
}