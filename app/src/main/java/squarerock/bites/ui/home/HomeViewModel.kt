package squarerock.bites.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import squarerock.bites.data.WikiRepository


class HomeViewModel(private val wikiRepository: WikiRepository) : ViewModel() {

    fun getRandomArticles(limit: Int = 1) = liveData(Dispatchers.IO) {
        emit(
            wikiRepository.fetchRandomArticles(limit).query.random.map { it.title }
        )
    }

    fun getArticleExtracts(titles: List<String>) = liveData(Dispatchers.IO) {
        emit(
            wikiRepository.fetchArticleExtracts(titles).query.pages.map { it.extract }
        )
    }
}