package com.excelsior.codechallenge.infrastructure.model.di

import com.excelsior.codechallenge.infrastructure.model.EventConverter
import com.excelsior.codechallenge.infrastructure.utils.FormatUtils
import org.koin.dsl.module

object ModelInjectionModule {

    val module = module {

        factory {
            EventConverter(get())
        }

        factory {
            FormatUtils
        }
    }
}