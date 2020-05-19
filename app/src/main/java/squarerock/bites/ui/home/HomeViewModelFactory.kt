package squarerock.bites.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import squarerock.bites.data.WikiRepository

class HomeViewModelFactory(private val wikiRepository: WikiRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(wikiRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}