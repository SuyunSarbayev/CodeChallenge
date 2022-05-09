package com.excelsior.codechallenge.infrastructure.di

import com.excelsior.codechallenge.eventsOverview.di.EventsOverviewInjectionModule
import com.excelsior.codechallenge.infrastructure.model.di.ModelInjectionModule
import com.excelsior.codechallenge.infrastructure.network.di.NetworkInjectionModule

object InjectionModules {

    // add all feature injection modules here
    val modules = listOf(
        EventsOverviewInjectionModule.module,
        ModelInjectionModule.module,
        NetworkInjectionModule.module
    )
}