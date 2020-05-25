package squarerock.bites.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import squarerock.bites.data.WikiRepository
import squarerock.bites.network.WikiApi
import squarerock.bites.network.WikiApiService
import squarerock.bites.ui.home.HomeViewModel

val appModule = module {
    single {
        WikiApi.create()
    }

    single {
        WikiApiService(get())
    }

    single {
        WikiRepository(get())
    }
}


val viewModelModules = module {
    viewModel {
        HomeViewModel(get())
    }
}