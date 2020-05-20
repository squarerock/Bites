package squarerock.bites.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import squarerock.bites.data.WikiRepository
import squarerock.bites.network.WikiApiService

class HomeViewModelFactory(private val wikiApiService: WikiApiService): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(WikiRepository(wikiApiService)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}