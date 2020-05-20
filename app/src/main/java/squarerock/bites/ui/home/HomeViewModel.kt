package squarerock.bites.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import squarerock.bites.data.WikiRepository
import squarerock.bites.network.utils.Resource


class HomeViewModel(private val wikiRepository: WikiRepository) : ViewModel() {

    fun getTitles(limit: Int = 1) = tryEmit {
        wikiRepository.fetchArticles(limit).query.random.map { it.title }
    }

    fun getExtracts(titles: List<String>) = tryEmit {
        wikiRepository.fetchExtracts(titles).query.pages.map { it.extract }
    }

    private fun <T> tryEmit(block: suspend () -> T) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = block()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}