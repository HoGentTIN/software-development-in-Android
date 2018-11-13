package be.equality.metar.injection.component

import be.equality.metar.injection.module.NetworkModule
import be.equality.metar.ui.MetarViewModel
import dagger.Component
import javax.inject.Singleton


/**
 * Component providing the inject functions for presenters.
 *
 * What is a Component? : see [the documentation](https://google.github.io/dagger/api/2.14/dagger/Component.html)
 *
 */

@Singleton
@Component(modules = [NetworkModule::class])
interface ViewModelInjectorComponent {


    /**
     * Injects the dependencies into the specified MetarViewModel.
     * @param metarViewModel the [MetarViewModel] in which to inject the dependencies.
     */
    fun inject(metarViewModel: MetarViewModel)


    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjectorComponent

        fun networkModule(networkModule: NetworkModule): Builder
    }

}