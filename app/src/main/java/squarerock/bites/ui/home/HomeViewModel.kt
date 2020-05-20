package squarerock.bites.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import squarerock.bites.data.WikiRepository


class HomeViewModel(private val wikiRepository: WikiRepository) : ViewModel() {

    fun getTitles(limit: Int = 1) = liveData(Dispatchers.IO) {
        emit(
            wikiRepository.fetchArticles(limit).query.random.map { it.title }
        )
    }

    fun getExtracts(titles: List<String>) = liveData(Dispatchers.IO) {
        emit(
            wikiRepository.fetchExtracts(titles).query.pages.map { it.extract }
        )
    }
}