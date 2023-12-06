package br.com.rodrigoamora.converters.di

import br.com.rodrigoamora.converters.ui.viewmodel.DistanceViewModel
import br.com.rodrigoamora.converters.ui.viewmodel.TemperatureViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext
import org.koin.dsl.module

fun injectFeature() = loadFeature

private val loadFeature by lazy {
    GlobalContext.loadKoinModules(
        listOf(
            viewModelModule
        )
    )
}

val viewModelModule = module {
    viewModel { DistanceViewModel() }
    viewModel { TemperatureViewModel() }
}